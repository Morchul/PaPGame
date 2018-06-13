package com.morchul.ui.components.inspectionview;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.morchul.card.Card;
import com.morchul.connections.StaticServerInterface;
import com.morchul.connections.message.MessageModelCreator;
import com.morchul.handler.CardUtils;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.abstractmodels.Creatures;
import com.morchul.model.models.Status;
import com.morchul.ui.dragdrop.interfaces.DragSource;
import com.morchul.ui.dragdrop.interfaces.DropTarget;
import com.morchul.ui.dragdrop.interfaces.MainDragAndDropAdmin;

public class InspectionViewStatusItem implements DragSource {

    private Status status;
    private HorizontalGroup group;
    private Creatures creatures;

    public InspectionViewStatusItem(Creatures creatures, Status status, Skin skin) {
        this.status = status;
        this.creatures = creatures;
        this.group = new HorizontalGroup();
        group.space(4);

        Label durationLabel = new Label(status.getDuration()+"", skin);
        status.addListener(duration -> durationLabel.setText(duration+""));

        final TextButton remove = new TextButton("-", skin);
        remove.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StaticServerInterface.sendMessage(MessageModelCreator.createRemoveStatus(status, creatures));
            }
        });
        group.addActor(new Label(status.getName(), skin));
        group.addActor(durationLabel);
        group.addActor(remove);

        MainDragAndDropAdmin.addSource(group, this, status.getName());
    }

    public HorizontalGroup getView() {
        return group;
    }

    public Creatures getCreatures() { return creatures; }

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
        return DropTarget.DropActionType.MOVE;
    }
}
