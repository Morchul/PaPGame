package com.morchul.ui.components;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.morchul.PaPHelper;
import com.morchul.connections.StaticServerInterface;
import com.morchul.connections.message.MessageModelCreator;
import com.morchul.model.abstractmodels.Creatures;
import com.morchul.ui.dragdrop.interfaces.DragSource;
import com.morchul.ui.dragdrop.interfaces.DropTarget;
import com.morchul.ui.dragdrop.interfaces.MainDragAndDropAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class AddCreatureComponent implements DropTarget {

    private Table table;
    private Logger log = LoggerFactory.getLogger(PaPHelper.class);

    public AddCreatureComponent(Skin skin) {
        table = new Table(skin);
        table.setTouchable(Touchable.enabled);

        table.add("Add Creature").center().fill();

        MainDragAndDropAdmin.addTarget(table, this);
    }

    @Override
    public void dropAction(DropActionType type, DragSource object) {
        if(type != DropActionType.NONE) {
            if ((object.getItem()) instanceof Creatures) {
                if (type == DropActionType.MOVE_NEW)
                    object.getItem().setGameUUID(UUID.randomUUID().toString());

                StaticServerInterface.sendMessage(MessageModelCreator.createAddCreatureMessage((Creatures) object.getItem()));
                object.getItem().destroyCard();
            } else {
                log.info("Object is not instance of Creatures " + object.getItem().getClass().getSimpleName());
            }
        }
    }

    public Table getField(){return table;}
}
