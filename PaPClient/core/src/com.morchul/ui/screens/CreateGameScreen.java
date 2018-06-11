package com.morchul.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.morchul.connections.StaticServerInterface;
import com.morchul.connections.message.MessageModelCreator;
import com.morchul.Self;
import com.morchul.game.SimpleClientGame;
import com.morchul.message.MessageModel;
import com.morchul.ui.ScreenLoader;
import org.json.JSONObject;

import static com.morchul.helper.json.ClientJSONConverter.simpleStaticConverter;
import static com.morchul.ui.StaticUIValues.DEFAULT_SKIN;

public class CreateGameScreen implements CustomScreen {

    private final Stage stage;
    private Label status;

    private CreateGameScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        final Skin skin = DEFAULT_SKIN;

        final Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);


        final Label gameName = new Label("Name:", skin);
        final TextField gameNameField = new TextField("",skin);

        final TextButton create = new TextButton("Create", skin);
        create.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StaticServerInterface.sendMessage(MessageModelCreator.createCreateGameMessage(gameNameField.getText()));
                MessageModel message = simpleStaticConverter.toMessageModel(new JSONObject(StaticServerInterface.read()));
                Self.game = new SimpleClientGame(simpleStaticConverter.toGame(new JSONObject(message.message)), (boolean)message.param.get(0));
                ScreenLoader.changeScreen(ScreenLoader.Screens.LOBBY);
            }
        });
        final TextButton cancel = new TextButton("Cancel", skin);
        cancel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenLoader.changeScreen(ScreenLoader.Screens.HOME);
            }
        });

        status = new Label("", skin);

        table.add(status).colspan(2);
        table.row();
        table.add(gameName);
        table.add(gameNameField);
        table.row().pad(15,0,15,0);
        table.add(create).fillX().uniformX().colspan(2);
        table.row();
        table.add(cancel).fillX().uniformX().colspan(2);
    }

    public static CreateGameScreen getInstance(){
        return new CreateGameScreen();
    }

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
