package com.morchul.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.morchul.settings.ClientSettings;
import com.morchul.ui.ScreenLoader;

import static com.morchul.ui.StaticUIValues.DEFAULT_SKIN;
import static com.morchul.ui.StaticUIValues.SETTINGS_TABLE_PAD;

public class SettingsScreen implements CustomScreen {

    private Stage stage;
    private Label status;

    TextField collectionPathField;
    TextField portField;
    TextField hostField;

    private SettingsScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);

        Skin skin = DEFAULT_SKIN;
        table.setSkin(skin);

        TextButton home = new TextButton("Save and Back", skin);
        home.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                save();
                ScreenLoader.changeScreen(ScreenLoader.Screens.LOGIN);
            }
        });

        collectionPathField = new TextField(ClientSettings.getCollectionsPath(), skin);
        portField = new TextField(ClientSettings.getPort()+"", skin);
        portField.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        hostField = new TextField(ClientSettings.getHost(), skin);

        status = new Label("", skin);
        table.add(status).colspan(2).pad(SETTINGS_TABLE_PAD);
        table.row().pad(SETTINGS_TABLE_PAD);
        table.add("Port: ");
        table.add(portField).fillX().uniformX();
        table.row().pad(SETTINGS_TABLE_PAD);
        table.add("Host: ");
        table.add(hostField).fillX().uniformX();
        table.row().pad(SETTINGS_TABLE_PAD);
        table.add("CollectionPath: ");
        table.add(collectionPathField).fillX().uniformX();
        table.row().pad(SETTINGS_TABLE_PAD);
        table.add(home).colspan(2);
    }

    private void save(){
        ClientSettings.setCollectionsPath(collectionPathField.getText());
        ClientSettings.setHost(hostField.getText());
        ClientSettings.setPort(Integer.parseInt(portField.getText()));
        ClientSettings.save();
    }

    public static SettingsScreen getInstance(){return new SettingsScreen();}

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
