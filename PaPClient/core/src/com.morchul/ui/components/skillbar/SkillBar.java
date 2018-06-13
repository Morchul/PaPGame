package com.morchul.ui.components.skillbar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.morchul.model.abstractmodels.Creatures;
import com.morchul.model.models.Skill;
import javafx.collections.ListChangeListener;

public class SkillBar {

    private HorizontalGroup group;
    private ScrollPane scrollPane;
    private Creatures creature;
    private Skin skin;

    public SkillBar(Skin skin, Creatures creature) {
        this.creature = creature;
        this.skin = skin;
        group = new HorizontalGroup();
        scrollPane = new ScrollPane(group);
        scrollPane.setScrollingDisabled(false, true);

        creature.getSkills().addListener((ListChangeListener<Skill>) c -> update());
        update();
    }

    private void update(){
        Gdx.app.postRunnable(()->{
            group.clear();
            for(Skill s: creature.getSkills()){
               group.addActor(new SkillBarElement(s, skin).getSkillElementView());
            }
            scrollPane.setActor(group);
        });
    }

    public ScrollPane getSkillBar() {
        return scrollPane;
    }
}
