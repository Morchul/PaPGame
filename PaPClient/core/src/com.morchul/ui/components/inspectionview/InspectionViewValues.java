package com.morchul.ui.components.inspectionview;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.morchul.model.abstractmodels.Creatures;

public class InspectionViewValues {

    private Table table;
    private Creatures creatures;
    private Skin skin;

    TextField hp;
    TextField maxHp;
    TextField mp;
    TextField maxMp;
    TextField reaction;
    TextField will;
    TextField strength;
    TextField resistance;

    public InspectionViewValues(Creatures creatures, Skin skin) {
        this.creatures = creatures;
        this.skin = skin;
        this.table = new Table(skin);
        table.setDebug(false);
        creatures.addListener(this::update);

        hp = new TextField(creatures.getHp()+"", skin);
        hp.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        maxHp = new TextField(creatures.getMaxHp()+"", skin);
        maxHp.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        mp = new TextField(creatures.getMp()+"", skin);
        mp.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        maxMp = new TextField(creatures.getMaxMp()+"", skin);
        maxMp.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        reaction = new TextField(creatures.getReaction()+"", skin);
        reaction.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        will = new TextField(creatures.getWill()+"", skin);
        will.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        strength = new TextField(creatures.getStrength()+"", skin);
        strength.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        resistance = new TextField(creatures.getResistance()+"", skin);
        resistance.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());

        hp.addListener(new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                System.out.println("Focus: " + focused);
            }
        });

        table.add("Hp:");
        table.add(hp);
        table.add(maxHp);
        table.row();
        table.add("Mp:");
        table.add(mp);
        table.add(maxMp);
        table.row();
        table.add("Reaction:");
        table.add(reaction);
        table.row();
        table.add("Will:");
        table.add(will);
        table.row();
        table.add("Strength:");
        table.add(strength);
        table.row();
        table.add("Resistance");
        table.add(resistance);

    }

    public Table getTable(){
        return table;
    }

    public void update(){
       hp.setText(creatures.getHp()+"");
       maxHp.setText(creatures.getMaxHp()+"");
       mp.setText(creatures.getMp()+"");
       maxMp.setText(creatures.getMaxMp()+"");
       reaction.setText(creatures.getReaction()+"");
       will.setText(creatures.getWill()+"");
       strength.setText(creatures.getStrength()+"");
       resistance.setText(creatures.getResistance()+"");
    }
}
