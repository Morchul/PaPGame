package com.morchul.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.morchul.Self;
import com.morchul.connections.StaticServerInterface;
import com.morchul.connections.message.MessageModelCreator;
import com.morchul.json.SimpleJSONConverter;
import com.morchul.message.MessageModel;
import com.morchul.model.Type;
import com.morchul.model.abstractmodels.Creatures;
import com.morchul.model.models.SimpleCreatures;
import com.morchul.ui.ScreenLoader;
import com.morchul.ui.custom.CustomNumberSpinner;
import com.morchul.ui.custom.CustomNumberSpinnerGroup;

import java.util.UUID;

import static com.morchul.ui.StaticUIValues.DEFAULT_SKIN;

public class CreateCharacterScreen implements CustomScreen {

    private final Stage stage;
    private Label status;

    public CreateCharacterScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        final Skin skin = DEFAULT_SKIN;

        final Table table = new Table(skin);
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);

        CustomNumberSpinnerGroup HP_MP = new CustomNumberSpinnerGroup(15, skin);
        CustomNumberSpinner hp = new CustomNumberSpinner(HP_MP, skin,20,20,45,1);
        CustomNumberSpinner mp = new CustomNumberSpinner(HP_MP, skin,0,0,30,2);

        CustomNumberSpinnerGroup characteristics = new CustomNumberSpinnerGroup(15, skin);
        CustomNumberSpinner reaction = new CustomNumberSpinner(characteristics, skin, 1, 1, 10,1);
        CustomNumberSpinner will = new CustomNumberSpinner(characteristics, skin, 1, 1, 10,1);
        CustomNumberSpinner strength = new CustomNumberSpinner(characteristics, skin, 1, 1, 10,1);
        CustomNumberSpinner resistance = new CustomNumberSpinner(characteristics, skin, 1, 1, 10,1);
        TextField nameTf = new TextField("", skin);

        TextButton back = new TextButton("Back", skin);
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenLoader.changeScreen(ScreenLoader.Screens.HOME);
            }
        });
        TextButton create = new TextButton("Create", skin);
        create.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Creatures c = new SimpleCreatures(
                        nameTf.getText(),
                        UUID.randomUUID().toString(),
                        "", "", "", "",
                        new Type(Type.PLAYER),
                        hp.getValue(),
                        hp.getValue(),
                        mp.getValue(),
                        mp.getValue(),
                        reaction.getValue(),
                        will.getValue(),
                        strength.getValue(),
                        resistance.getValue(),
                        false);
                createCharacter(c);
            }
        });

        status = new Label("", skin);
        table.add("Name: ");table.add(nameTf);
        table.row();
        table.add("Hp: ");table.add(hp.getView());table.add("points: ");table.add(HP_MP.getView());
        table.row();
        table.add("Mp: ");table.add(mp.getView());
        table.row();
        table.add("Reaction: ");table.add(reaction.getView());table.add("points: ");table.add(characteristics.getView());
        table.row();
        table.add("Will: ");table.add(will.getView());
        table.row();
        table.add("Strength: ");table.add(strength.getView());
        table.row();
        table.add("Resistance: ");table.add(resistance.getView());
        table.row();
        table.add(create).colspan(2);table.add(back).colspan(2);
    }

    private void createCharacter(Creatures c){
        StaticServerInterface.sendMessage(MessageModelCreator.createCreateCharacterMessage(c));
        Self.characterList.add(c);
    }

    public static CreateCharacterScreen getInstance() {return new CreateCharacterScreen();}

    @Override
    public void setStatus(String status) {
        this.status.setText(status);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(delta, 1/ 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
