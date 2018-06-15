package com.morchul.ui.character;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.morchul.connections.StaticServerInterface;
import com.morchul.connections.message.MessageModelCreator;
import com.morchul.model.abstractmodels.Creatures;
import com.morchul.ui.custom.CustomNumberSpinner;
import com.morchul.ui.custom.CustomNumberSpinnerGroup;

import static com.morchul.model.abstractmodels.Creatures.*;
import static com.morchul.ui.StaticUIValues.CHARACTER_VIEW_CONTAINER_WIDTH;

public class CharacterValueView {

    private Table table;
    private Creatures creatures;

    private static CustomNumberSpinnerGroup group;
    private CustomNumberSpinner maxHp;
    private CustomNumberSpinner maxMp;
    private CustomNumberSpinner reaction;
    private CustomNumberSpinner will;
    private CustomNumberSpinner strength;
    private CustomNumberSpinner resistance;

    public CharacterValueView(Skin skin, Creatures creatures) {
        this.creatures = creatures;
        table = new Table(skin);
        table.setWidth(CHARACTER_VIEW_CONTAINER_WIDTH);

        creatures.addListener(this::update);

        group = new CustomNumberSpinnerGroup(0, skin);
        maxHp = new CustomNumberSpinner(group,skin,creatures.getMaxHp(),creatures.getMaxHp(),100,1);
        maxMp = new CustomNumberSpinner(group, skin, creatures.getMaxMp(), creatures.getMaxMp(),100,1);
        reaction = new CustomNumberSpinner(group, skin, creatures.getReaction(), creatures.getReaction(), 100, 1);
        will = new CustomNumberSpinner(group, skin, creatures.getWill(), creatures.getWill(),100,1);
        strength = new CustomNumberSpinner(group, skin, creatures.getStrength(), creatures.getStrength(),100,1);
        resistance = new CustomNumberSpinner(group,skin,creatures.getResistance(), creatures.getReaction(), 100, 1);

        TextButton save = new TextButton("Save", skin);
        save.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                updateCharacterValues(
                        maxHp.getValue(),
                        maxMp.getValue(),
                        reaction.getValue(),
                        will.getValue(),
                        strength.getValue(),
                        resistance.getValue()
                );
            }
        });

        table.add("Hp:").colspan(2);table.add(maxHp.getView());
        table.row();
        table.add("Mp:").colspan(2);table.add(maxMp.getView());
        table.row();
        table.add("Reaction:").colspan(2);table.add(reaction.getView());
        table.row();
        table.add("Will:").colspan(2);table.add(will.getView());
        table.row();
        table.add("Strength:").colspan(2); table.add(strength.getView());
        table.row();
        table.add("Resistance:").colspan(2); table.add(resistance.getView());
        table.row();
        table.add(save); table.add("Points:");table.add(group.getView());
    }

    public static void addPoints(int points){
        group.addPoints(points);
    }

    private void update(){
        maxHp.setValue(creatures.getMaxHp());
        maxHp.setMin(creatures.getMaxHp());
        maxMp.setValue(creatures.getMaxMp());
        maxMp.setMin(creatures.getMaxMp());
        reaction.setValue(creatures.getReaction());
        reaction.setMin(creatures.getReaction());
        will.setValue(creatures.getWill());
        will.setMin(creatures.getWill());
        strength.setValue(creatures.getStrength());
        strength.setMin(creatures.getStrength());
        resistance.setValue(creatures.getResistance());
        resistance.setMin(creatures.getResistance());
    }

    private void updateCharacterValues(int hp, int mp, int reaction, int will, int strength, int resistance){
        if(creatures.getMaxHp() != hp) {
            StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(hp, creatures, MAX_HP));
            this.maxHp.setMin(hp);
        }
        if(creatures.getMaxMp() != mp) {
            StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(mp, creatures, MAX_MP));
            this.maxMp.setMin(mp);
        }
        if(creatures.getReaction() != reaction) {
            StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(reaction, creatures, REACTION));
            this.reaction.setMin(reaction);
        }
        if(creatures.getWill() != will) {
            StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(will, creatures, WILL));
            this.will.setMin(will);
        }
        if(creatures.getStrength() != strength) {
            StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(strength, creatures, STRENGTH));
            this.strength.setMin(strength);
        }
        if(creatures.getResistance() != resistance) {
            StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(resistance, creatures, RESISTANCE));
            this.resistance.setMin(resistance);
        }
    }

    public Table getTable() {
        return table;
    }
}
