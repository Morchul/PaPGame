package com.morchul.ui.components;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.morchul.connections.StaticServerInterface;
import com.morchul.connections.message.MessageModelCreator;
import com.morchul.ui.dragdrop.interfaces.DragSource;
import com.morchul.ui.dragdrop.interfaces.DropTarget;
import com.morchul.ui.dragdrop.interfaces.MainDragAndDropAdmin;

import java.util.UUID;

public class AllTableComponent implements DropTarget {

    private Table table;

    public AllTableComponent(Skin skin) {
        table = new Table(skin);
        table.setTouchable(Touchable.enabled);

        table.add("All Table").center().fill();

        MainDragAndDropAdmin.addTarget(table, this);
    }

    @Override
    public void dropAction(DropActionType type, DragSource object) {
        if (type != DropActionType.NONE) {
            if (type == DropActionType.MOVE_NEW)
                object.getItem().setGameUUID(UUID.randomUUID().toString());
            StaticServerInterface.sendMessage(MessageModelCreator.createAllTableMessage(object.getItem()));
        }
    }

    public Table getField(){
        return table;
    }
}
