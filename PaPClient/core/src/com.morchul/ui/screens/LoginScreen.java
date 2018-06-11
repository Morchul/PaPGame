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
import com.morchul.message.MessageModel;
import com.morchul.ui.ScreenLoader;
import org.json.JSONObject;

import static com.morchul.helper.json.ClientJSONConverter.simpleStaticConverter;
import static com.morchul.ui.StaticUIValues.DEFAULT_SKIN;

public class LoginScreen implements CustomScreen {

    private Stage stage;
    private Label status;

    private LoginScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);

        Skin skin = DEFAULT_SKIN;

        final TextField userName = new TextField("",skin);
        userName.setMessageText("Username");
        final TextField password = new TextField("", skin);
        password.setMessageText("Password");
        password.setPasswordCharacter('*');
        password.setPasswordMode(true);
        Label user = new Label("UserName: ", skin);
        Label pass = new Label("Password: ", skin);
        TextButton login = new TextButton("Login", skin);
        TextButton exit = new TextButton("Exit", skin);
        TextButton settings = new TextButton("Settings", skin);
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StaticServerInterface.disconnect();
                Gdx.app.exit();
            }
        });
        login.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                loginEvent(userName.getText(), password.getText());
            }
        });
        settings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenLoader.changeScreen(ScreenLoader.Screens.SETTINGS);
            }
        });

        status = new Label("", skin);
        table.add(status).colspan(2);
        table.row();
        table.add(user);
        table.add(userName);
        table.row().pad(15,0,15,0);
        table.add(pass);
        table.add(password);
        table.row();
        table.add(login).fillX().uniformX().colspan(2);
        table.row().pad(15,0,15,0);
        table.add(settings).fillX().uniformX().colspan(2);
        table.row();
        table.add(exit).fillX().uniformX().colspan(2);
    }

    private void loginEvent(String userName, String password){
        StaticServerInterface.sendMessage(MessageModelCreator.createLoginMessage(userName, password));
        MessageModel receive = simpleStaticConverter.toMessageModel(new JSONObject(StaticServerInterface.read()));
        if(receive.type == MessageModel.MessageType.LOGIN) {
            if (receive.message.equals("NOK")) {
                ScreenLoader.setStatus("Wrong username or password");
            } else {
                ScreenLoader.setStatus("Successful Logged in");
                Self.user = simpleStaticConverter.toUser(new JSONObject(receive.message));
                ScreenLoader.changeScreen(ScreenLoader.Screens.HOME);
            }
        } else {
            log.error("Type is: " + receive.type);
            ScreenLoader.setStatus("Invalid received message.");
        }
    }

    public static LoginScreen getInstance(){
        return new LoginScreen();
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
