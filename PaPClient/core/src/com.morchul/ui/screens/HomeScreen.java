package com.morchul.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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

public class HomeScreen implements CustomScreen {

    private Stage stage;
    private Label status;

    private HomeScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);

        Skin skin = DEFAULT_SKIN;

        final Input.TextInputListener textInput = new Input.TextInputListener() {
            @Override
            public void input(String text) {
                StaticServerInterface.sendMessage(MessageModelCreator.createJoinGameMessage(text));
                MessageModel receive = simpleStaticConverter.toMessageModel(new JSONObject(StaticServerInterface.read()));
                if(receive.message.equals("NOK")){
                    ScreenLoader.setStatus("Can't connect to GameNumber: " + text);
                } else {
                    Self.game = new SimpleClientGame(simpleStaticConverter.toGame(new JSONObject(receive.message)),(boolean)receive.param.get(0));
                    Gdx.app.postRunnable(() -> ScreenLoader.changeScreen(ScreenLoader.Screens.LOBBY));
                }
            }

            @Override
            public void canceled() {}
        };


        TextButton joinGame = new TextButton("Join Game", skin);
        joinGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.getTextInput(textInput, "Dialog title","", "Blubber");
            }
        });

        TextButton createGame = new TextButton("Create Game", skin);
        createGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenLoader.changeScreen(ScreenLoader.Screens.CREATE_GAME);
            }
        });

        TextButton logout = new TextButton("Logout", skin);
        logout.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StaticServerInterface.sendMessage(MessageModelCreator.createLogoutMessage());
                Self.user = null;
                ScreenLoader.changeScreen(ScreenLoader.Screens.LOGIN);
            }
        });

        status = new Label("", skin);
        table.add(status).colspan(2);
        table.row();
        table.add(joinGame).fillX().uniformX();
        table.row().pad(10,0,10,0);
        table.add(createGame).fillX().uniformX();
        table.row();
        table.add(logout).fillX().uniformX();
    }

    public static HomeScreen getInstance(){
        return new HomeScreen();
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
        stage.act(delta);
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
