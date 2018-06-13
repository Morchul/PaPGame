package com.morchul.ui.components.inspectionview;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.morchul.model.abstractmodels.Creatures;
import com.morchul.model.models.Status;
import javafx.collections.ListChangeListener;

public class InspectionViewStatus {

    private Table table;
    private Creatures creatures;
    private Skin skin;

    public InspectionViewStatus(Creatures creatures, Skin skin) {
        this.creatures = creatures;
        this.skin = skin;
        table = new Table(skin);
        table.setDebug(false);
        creatures.getStatus().addListener((ListChangeListener<Status>) c -> update());
        update();
    }

    public void update(){
        table.clear();
        for(Status s: creatures.getStatus()){
            table.add(new InspectionViewStatusItem(creatures, s, skin).getView());
            table.row();
        }
    }

    public Table getTable(){
        return table;
    }
}
