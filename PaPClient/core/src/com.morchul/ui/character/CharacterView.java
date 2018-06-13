package com.morchul.ui.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.morchul.model.Type;
import com.morchul.model.abstractmodels.Creatures;

import static com.morchul.ui.StaticUIValues.CHARACTER_FIELD_SIZE;
import static com.morchul.ui.StaticUIValues.CHARACTER_VIEW_HEIGHT;
import static com.morchul.ui.StaticUIValues.CHARACTER_VIEW_WIDTH;

public class CharacterView {

    private Table table;
    private boolean visible = false;

    public CharacterView(Skin skin, Creatures character) {
        table = new Table();
        table.setVisible(visible);
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("CharacterPortrait.jpg")))));
        table.setSize(CHARACTER_VIEW_WIDTH,CHARACTER_VIEW_HEIGHT);


        CharacterField weapon = new CharacterField(new Type(Type.WEAPON), character);
        CharacterField headArmor = new CharacterField(new Type(Type.HEAD_ARMOR), character);
        CharacterField shield = new CharacterField(new Type(Type.SHIELD), character);
        CharacterField chestArmor = new CharacterField(new Type(Type.CHEST_ARMOR), character);
        CharacterField legArmor = new CharacterField(new Type(Type.LEG_ARMOR), character);

        weapon.getField().setPosition(20,130);
        shield.getField().setPosition(240,130);
        headArmor.getField().setPosition(130,240);
        chestArmor.getField().setPosition(130,130);
        legArmor.getField().setPosition(130,50);

        weapon.getField().setSize(CHARACTER_FIELD_SIZE,CHARACTER_FIELD_SIZE);
        shield.getField().setSize(CHARACTER_FIELD_SIZE,CHARACTER_FIELD_SIZE);
        headArmor.getField().setSize(CHARACTER_FIELD_SIZE,CHARACTER_FIELD_SIZE);
        chestArmor.getField().setSize(CHARACTER_FIELD_SIZE,CHARACTER_FIELD_SIZE);
        legArmor.getField().setSize(CHARACTER_FIELD_SIZE,CHARACTER_FIELD_SIZE);

        table.addActor(weapon.getField());
        table.addActor(shield.getField());
        table.addActor(headArmor.getField());
        table.addActor(chestArmor.getField());
        table.addActor(legArmor.getField());
    }

    public Table getTable() {
        return table;
    }

    public void show(){
        visible = true;
        table.setVisible(true);
    }
    public void hide(){
        visible = false;
        table.setVisible(false);
    }
    public void changeVisible(){
        if(visible)
            hide();
        else
            show();
    }
}
