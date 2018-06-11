package com.morchul.ui.components.statusview;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.morchul.model.models.Creatures;
import com.morchul.model.models.Status;
import javafx.collections.ListChangeListener;

import static com.morchul.ui.StaticUIValues.STATUS_HEIGHT;
import static com.morchul.ui.StaticUIValues.STATUS_WIDTH;

public class StatusView {

    private VerticalGroup group;
    private boolean visible = false;
    private Creatures creature;
    private Skin skin;

    public StatusView(Skin skin, Creatures creatures) {
        this.skin = skin;
        this.creature = creatures;
        group = new VerticalGroup();
        group.setVisible(visible);
        group.space(3);
        group.setWidth(STATUS_WIDTH);
        group.setHeight(STATUS_HEIGHT);
        update();
        creatures.getStatus().addListener((ListChangeListener<Status>) c -> update());
    }

    private void update(){
        Gdx.app.postRunnable(()-> {
            for (Status s : creature.getStatus()) {
                group.addActor(new StatusViewItem(skin, s).getTable());
            }
        });
    }

    public Creatures getCreature() {
        return creature;
    }

    public VerticalGroup getGroup() {
        return group;
    }

    public void show(){
        visible = true;
        group.setVisible(true);
    }
    public void hide(){
        visible = false;
        group.setVisible(false);
    }
    public void changeVisible(){
        if(visible)
            hide();
        else
            show();
    }
}
