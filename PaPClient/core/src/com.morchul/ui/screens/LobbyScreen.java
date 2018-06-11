package com.morchul.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.morchul.connections.StaticServerInterface;
import com.morchul.connections.message.MessageModelCreator;
import com.morchul.game.Game;
import com.morchul.model.models.Creatures;
import com.morchul.model.player.GameMaster;
import com.morchul.Self;
import com.morchul.model.player.User;
import javafx.collections.ListChangeListener;

import static com.morchul.ui.StaticUIValues.*;

public class LobbyScreen implements CustomScreen{

    private Stage stage;
    private Label status;
    private Game game;

    private List<String> players;

    private LobbyScreen(final Game game) {
        this.game = game;
        StaticServerInterface.listen();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = DEFAULT_SKIN;

        players = new List<>(skin);
        TextButton start = new TextButton("Start game", skin);

        game.getPlayers().addListener((ListChangeListener<User>) c -> {
            players.clearItems();
            updatePlayerList();
            start.setDisabled(game.getPlayers().size() <= 1);
        });

        updatePlayerList();

        Table table = new Table();
        table.setDebug(false);
        table.setFillParent(true);
        stage.addActor(table);

        final SelectBox<String> chooseCharacter = new SelectBox<>(skin);
        Array<String> items = new Array<>();
        for(Creatures c : Self.characters){
            items.add(c.getName());
        }
        chooseCharacter.setItems(items);
        if(Self.game.IamTheGameMaster())
            Self.user.setCharacter(GameMaster.gameMasterCharacter);
        else
            Self.user.setCharacter(Self.getCharacter(chooseCharacter.getSelected()));

        chooseCharacter.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Self.user.setCharacter(Self.getCharacter(chooseCharacter.getSelected()));
            }
        });

        TextButton leaf = new TextButton("Leaf", skin);
        leaf.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StaticServerInterface.sendMessage(MessageModelCreator.createLeafGameMessage());
                Self.leafGame();
            }
        });

        status = new Label("",skin);

        start.setDisabled(true);
        start.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StaticServerInterface.sendMessage(MessageModelCreator.createStartGameMessage());
            }
        });

        Label gameName = new Label(game.getGameName(), skin);
        Label gameNumber = new Label(game.getGameNumber(), skin);

        table.add(gameName).fillX().uniformX();
        table.add(gameNumber).fillX().uniformX();
        table.row();
        table.row().pad(10,0,10,0);
        table.add(players).height(LOBBY_PLAYER_LIST_HEIGHT).width(LOBBY_PLAYER_LIST_WIDTH).colspan(2);
        table.row().pad(10,0,10,0);
        if(!game.IamTheGameMaster()){
            table.add(chooseCharacter).width(LOBBY_CHOOSE_CHARACTER_WIDTH).colspan(2);
            table.row().pad(10,0,10,0);
        }
        table.add(status).fillX().uniformX().colspan(2);
        table.row().pad(10,0,10,0);
        if(game.IamTheGameMaster()){
            table.add(start).colspan(2);
            table.row().pad(10,0,10,0);
        }
        table.add(leaf).colspan(2);
    }

    private void updatePlayerList(){
        for(User u : game.getPlayers()){
            if(game.isGameMaster(u)){
                players.getItems().add("GM: " + u.getUserName());
            } else {
                players.getItems().add(u.getUserName());
            }
        }
    }

    public static LobbyScreen getInstance(){
        return new LobbyScreen(Self.game);
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
