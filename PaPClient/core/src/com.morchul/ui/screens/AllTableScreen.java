package com.morchul.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.morchul.card.Card;
import com.morchul.handler.CardUtils;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.ui.UICardCreator;
import com.morchul.ui.components.InfoView;
import com.morchul.ui.components.MainTable;
import com.morchul.ui.components.menubar.MenuBar;

import static com.morchul.ui.StaticUIValues.*;

public class AllTableScreen implements CustomScreen {

    private Stage stage;
    private static AllTableScreen screen;
    private UICardCreator creator;

    private MenuBar menuBar;
    private MainTable mainTable;
    private InfoView infoView;

    public AllTableScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        creator = new UICardCreator(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        Skin skin = DEFAULT_SKIN;

        mainTable = new MainTable(creator, skin);
        infoView = new InfoView(skin);
        menuBar = new MenuBar(skin);

        menuBar.addBackToGameScreen();

        table.row().height(TOP_BAR_HEIGHT);
        table.add(menuBar.getMenuBar()).expandX().left();
        table.add(infoView.getInfoList()).growX();
        table.row().expandY();
        table.add(mainTable).grow().colspan(2);
    }

    public void addAnything(Anything any){
        for(Actor a: stage.getActors()){
            if(a instanceof Card){
                if(((Card) a).getItem().getGameUUID().equals(any.getGameUUID()))
                    return ;
            }
        }
        creator.createCard(CardUtils.getCard(any), ALL_TABLE_SPAWN_POS_X,ALL_TABLE_SPAWN_POS_Y);
    }

    public static AllTableScreen getInstance(){
        if(screen == null)
            screen = new AllTableScreen();
        return screen;
    }

    public static void disposeScreen(){
        screen = null;
    }

    @Override
    public void setStatus(String status) {
        infoView.addInfo(status);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
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
