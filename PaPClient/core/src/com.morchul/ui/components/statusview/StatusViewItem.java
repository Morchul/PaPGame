package com.morchul.ui.components.statusview;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.morchul.card.Card;
import com.morchul.handler.CardUtils;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.models.Status;
import com.morchul.ui.dragdrop.interfaces.DragSource;
import com.morchul.ui.dragdrop.interfaces.DropTarget;
import com.morchul.ui.dragdrop.interfaces.MainDragAndDropAdmin;

import static com.morchul.ui.StaticUIValues.INVENTORY_IMAGE_SIZE;

public class StatusViewItem implements DragSource {

    private HorizontalGroup group;
    private Label durationLabel;
    private Status status;

    public StatusViewItem(Skin skin, Status status) {
        group = new HorizontalGroup();
        group.space(5);
        this.status = status;
        durationLabel = new Label(status.getDuration()+"", skin);
        status.addListener(duration -> durationLabel.setText(duration+""));

        group.addActor(new Label(status.getName(), skin));
        group.addActor(durationLabel);

        MainDragAndDropAdmin.addSource(group, this, status.getName());
    }

    public HorizontalGroup getGroup(){ return group; }

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
