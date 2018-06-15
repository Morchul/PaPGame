package com.morchul.ui.character;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.morchul.model.abstractmodels.Creatures;

public class CharacterViewContainer {

    private CharacterView characterView;
    private CharacterValueView characterValueView;
    private Table table;
    private boolean visible = false;

    public CharacterViewContainer(Skin skin, Creatures creatures) {
        characterView = new CharacterView(creatures);
        characterValueView = new CharacterValueView(skin, creatures);
        table = new Table();
        table.setVisible(visible);

        table.add(characterView.getTable());
        table.row();
        table.add(characterValueView.getTable());
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
