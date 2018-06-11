package com.morchul.ui.components;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.morchul.PaPHelper;
import com.morchul.ui.UICardCreator;
import com.morchul.ui.dragdrop.helper.DragAndDropPositionHelper;
import com.morchul.ui.dragdrop.interfaces.DragSource;
import com.morchul.ui.dragdrop.interfaces.DropTarget;
import com.morchul.ui.dragdrop.interfaces.MainDragAndDropAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class MainTable extends Actor implements DropTarget {

    private Logger log = LoggerFactory.getLogger(PaPHelper.class);
    private UICardCreator creator;

    public MainTable(UICardCreator creator, Skin skin) {
        this.creator = creator;
        MainDragAndDropAdmin.addTarget(this, this);
    }

    @Override
    public void dropAction(DropActionType type, DragSource object) {
        if (type != DropActionType.NONE) {
            if (type == DropActionType.MOVE_NEW)
                object.getItem().setGameUUID(UUID.randomUUID().toString());

            creator.createCard((object).getCard(), DragAndDropPositionHelper.getDropPositionX(), DragAndDropPositionHelper.getDropPositionY());
        }
    }
}
