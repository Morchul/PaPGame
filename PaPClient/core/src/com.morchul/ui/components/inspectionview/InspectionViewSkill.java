package com.morchul.ui.components.inspectionview;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.morchul.model.abstractmodels.Creatures;
import com.morchul.model.models.Skill;
import javafx.collections.ListChangeListener;

public class InspectionViewSkill {

    private Table table;
    private Creatures creatures;
    private Skin skin;

    public InspectionViewSkill(Creatures creatures, Skin skin) {
        this.creatures = creatures;
        this.skin = skin;
        table = new Table(skin);
        table.setDebug(false);
        creatures.getSkills().addListener((ListChangeListener<Skill>) c -> update());
        update();
    }

    public void update(){
        table.clear();
        for(Skill s: creatures.getSkills()){
            table.add(new InspectionViewSkillItem(creatures, s, skin).getView());
            table.row();
        }
    }

    public Table getTable(){
        return table;
    }
}
