package com.morchul.ui.components.inspectionview;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.morchul.connections.StaticServerInterface;
import com.morchul.connections.message.MessageModelCreator;
import com.morchul.model.abstractmodels.Creatures;

public class InspectionViewActions {

    private Table table;

    public InspectionViewActions(Creatures creatures, Skin skin, InspectionView parent) {

        TextButton remove = new TextButton("Kill", skin);
        remove.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StaticServerInterface.sendMessage(MessageModelCreator.createKillMessage(creatures));
                parent.clear();
            }
        });

        TextButton addPoint = new TextButton("addPoint", skin);
        addPoint.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StaticServerInterface.sendMessage(MessageModelCreator.createAddPointMessage(creatures, 1));
            }
        });

        table = new Table();
        table.setDebug(false);
        table.add(remove);
        table.row();
        table.add(addPoint);

    }

    public Table getTable() {
        return table;
    }
}
