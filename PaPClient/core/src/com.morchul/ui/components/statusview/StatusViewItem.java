package com.morchul.ui.components.statusview;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.morchul.card.Card;
import com.morchul.handler.CardUtils;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.models.Status;
import com.morchul.ui.dragdrop.interfaces.DragSource;
import com.morchul.ui.dragdrop.interfaces.DropTarget;
import com.morchul.ui.dragdrop.interfaces.MainDragAndDrop;
import com.morchul.ui.dragdrop.interfaces.MainDragAndDropAdmin;

import static com.morchul.ui.StaticUIValues.STATUS_IMAGE_SIZE;

public class StatusViewItem implements DragSource {

    private Table table;
    private Image image;
    private Label durationLabel;
    private Status status;

    public StatusViewItem(Skin skin, Status status) {
        table = new Table(skin);
        this.status = status;
        image = new Image(new Texture(status.getImagePath()));
        durationLabel = new Label(status.getDuration()+"", skin);
        status.addListener(duration -> durationLabel.setText(duration+""));

        table.add(status.getName());
        table.row();
        if(image != null)
            table.add(image).width(STATUS_IMAGE_SIZE).height(STATUS_IMAGE_SIZE);
        table.row();
        table.add(status.getDescription());
        table.row();
        table.add(durationLabel);

        MainDragAndDropAdmin.addSource(table, this, status.getName());
    }

    public Table getTable(){ return table; }

    @Override
    public void dragAction() { }

    @Override
    public Anything getItem() {
        return status;
    }

    @Override
    public Card getCard() {
        return CardUtils.getCard(getItem());
    }

    @Override
    public DropTarget.DropActionType getDropType(DropTarget object) {
        return DropTarget.DropActionType.CARD;
    }
}
