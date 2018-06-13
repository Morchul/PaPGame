package com.morchul.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.morchul.Self;
import com.morchul.ui.UICardCreator;
import com.morchul.ui.components.AllTableComponent;
import com.morchul.ui.components.CreatorView;
import com.morchul.ui.components.InfoView;
import com.morchul.ui.components.MainTable;
import com.morchul.ui.components.AddCreatureComponent;
import com.morchul.ui.components.creatureview.CreatureView;
import com.morchul.ui.components.inspectionview.InspectionView;
import com.morchul.ui.components.menubar.MenuBar;

import static com.morchul.ui.StaticUIValues.*;

public class GameMasterScreen implements CustomScreen {

    private Stage stage;
    private static GameMasterScreen screen;
    private UICardCreator creator;

    private MainTable mainTable;
    private CreatureView creatureView;
    private CreatorView creatorView;
    private MenuBar menuBar;
    private InfoView infoView;
    private AllTableComponent allTableComponent;
    private AddCreatureComponent addCreatureComponent;
    private InspectionView inspectionView;

    private GameMasterScreen() {
        stage = new Stage(new ScreenViewport());
        creator = new UICardCreator(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        Skin skin = DEFAULT_SKIN;

        mainTable = new MainTable(creator, skin);
        creatureView = new CreatureView(Self.game, skin);
        creatorView = new CreatorView(skin, Self.collections);
        menuBar = new MenuBar(skin);
        infoView = new InfoView(skin);
        inspectionView = new InspectionView(skin);
        allTableComponent = new AllTableComponent(skin);
        addCreatureComponent = new AddCreatureComponent(skin);

        menuBar.addLeafMenu();
        menuBar.addDiceMenu(6);
        menuBar.addDiceMenu(20);
        menuBar.addAllTableMenu();
        menuBar.addNextRoundMenu();

        Table container = new Table();
        container.add(allTableComponent.getField()).width(ALL_TABLE_COMPONENT_WIDTH).height(ALL_TABLE_COMPONENT_HEIGHT);
        container.row();
        container.add(addCreatureComponent.getField()).width(CREATURE_COMPONENT_WIDTH).height(CREATURE_COMPONENT_HEIGHT);
        container.row();
        container.add(creatureView.getGroup()).width(RIGHT_CREATURE_VIEW_WIDTH).top();
        ScrollPane scrollPane = new ScrollPane(container);

        table.row().height(TOP_BAR_HEIGHT);
        table.add(menuBar.getMenuBar()).left();
        table.add(infoView.getInfoList()).growX().colspan(2);
        table.row();
        table.add(creatorView.getCreatorTree()).width(LEFT_BAR_WIDTH).top().fill().expandY();
        table.add(mainTable).grow().colspan(1);
        table.add(scrollPane).expandY().top();
        table.row().height(BOTTOM_SKILL_BAR_HEIGHT);
        table.add(inspectionView.getTable()).colspan(3).growX();

    }

    public static GameMasterScreen getInstance(){
        if(screen == null)
            screen = new GameMasterScreen();
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
