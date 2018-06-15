package com.morchul.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.morchul.Self;
import com.morchul.ui.StaticUIValues;
import com.morchul.ui.UICardCreator;
import com.morchul.ui.character.CharacterView;
import com.morchul.ui.components.AllTableComponent;
import com.morchul.ui.components.InfoView;
import com.morchul.ui.components.MainTable;
import com.morchul.ui.components.creatureview.CreatureView;
import com.morchul.ui.components.inventoryview.InventoryView;
import com.morchul.ui.components.menubar.MenuBar;
import com.morchul.ui.components.skillbar.SkillBar;
import com.morchul.ui.components.statusview.StatusView;
import com.morchul.ui.character.CharacterViewContainer;

import static com.morchul.ui.StaticUIValues.*;

public class GameScreen implements CustomScreen {

    private Stage stage;
    private static GameScreen screen;
    private UICardCreator creator;

    private MainTable mainTable;
    private CreatureView creatureView;
    private InventoryView inventoryView;
    private MenuBar menuBar;
    private SkillBar skillBar;
    private InfoView infoView;
    private AllTableComponent allTableComponent;
    private CharacterViewContainer characterView;
    private StatusView statusView;

    public GameScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        creator = new UICardCreator(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        Skin skin = DEFAULT_SKIN;

        mainTable = new MainTable(creator, skin);
        creatureView = new CreatureView(Self.game, skin);
        inventoryView = new InventoryView(Self.user.getCharacter().getInventory(),skin);
        menuBar = new MenuBar(skin);
        skillBar = new SkillBar(skin, Self.user.getCharacter());
        infoView = new InfoView(skin);
        allTableComponent = new AllTableComponent(skin);
        statusView = new StatusView(skin, Self.user.getCharacter());

        characterView = new CharacterViewContainer(skin, Self.user.getCharacter());
        stage.addActor(characterView.getTable());

        menuBar.addLeafMenu();
        menuBar.addDiceMenu(6);
        menuBar.addDiceMenu(20);
        menuBar.addAllTableMenu();
        menuBar.addCharacterMenu(characterView);
        menuBar.addStatusMenu(statusView);

        Table container = new Table();
        container.add(allTableComponent.getField()).width(ALL_TABLE_COMPONENT_WIDTH).height(ALL_TABLE_COMPONENT_HEIGHT);
        container.row();
        container.add(creatureView.getGroup()).width(CREATURE_COMPONENT_WIDTH).top();
        ScrollPane scrollPane = new ScrollPane(container);

        table.row().height(TOP_BAR_HEIGHT);
        table.add(menuBar.getMenuBar()).left();
        table.add(infoView.getInfoList()).growX().colspan(3);
        table.row();
        table.add(inventoryView.getInventoryList()).width(LEFT_BAR_WIDTH).top().fill().expandY();
        table.add(mainTable).grow().colspan(1);
        table.add(statusView.getGroup()).expandY().top().width(RIGHT_STATUS_VIEW_WIDTH);
        table.add(scrollPane).expandY().top().width(RIGHT_CREATURE_VIEW_WIDTH);
        table.row().height(BOTTOM_SKILL_BAR_HEIGHT);
        table.add(skillBar.getSkillBar()).colspan(4).fillX();

        characterView.getTable().setPosition(LEFT_BAR_WIDTH, BOTTOM_SKILL_BAR_HEIGHT);
    }

    public static GameScreen getInstance(){
        if(screen == null)
            screen = new GameScreen();
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
