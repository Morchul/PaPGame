package com.morchul.ui.components.menubar;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.morchul.connections.StaticServerInterface;
import com.morchul.connections.message.MessageModelCreator;
import com.morchul.Self;
import com.morchul.ui.ScreenLoader;
import com.morchul.ui.character.CharacterView;
import com.morchul.ui.character.CharacterViewContainer;
import com.morchul.ui.components.statusview.StatusView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MenuBar {

    private List<MenuBarItem> items;
    private HorizontalGroup group;
    private Skin skin;
    private Random random;


    public MenuBar(Skin skin) {
        random = new Random();
        this.skin = skin;
        items = new ArrayList<>();
    }

    public void addFinishGameMenu(){
        MenuBarItem item = new MenuBarItem("F", skin);
        item.setColor(0,0,0,1);
        item.addAction(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StaticServerInterface.sendMessage(MessageModelCreator.createFinishGameMessage());
            }
        });
        items.add(item);
    }

    public void addBackToGameScreen(){
        MenuBarItem item = new MenuBarItem("Back", skin);
        item.setColor(75,0,130,1);
        item.addAction(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(Self.game.IamTheGameMaster())
                    ScreenLoader.changeScreen(ScreenLoader.Screens.GAME_MASTER);
                else
                    ScreenLoader.changeScreen(ScreenLoader.Screens.GAME);
            }
        });
        items.add(item);
    }

    public void addCharacterMenu(CharacterViewContainer view){
        MenuBarItem item  = new MenuBarItem("C", skin);
        item.setColor(255,215,0,1);
        item.addAction(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                view.changeVisible();
            }
        });
        items.add(item);
    }

    public void addStatusMenu(StatusView view){
        MenuBarItem item = new MenuBarItem("S", skin);
        item.setColor(130,130,130,1);
        item.addAction(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                view.changeVisible();
            }
        });
        items.add(item);
    }

    public void addNextRoundMenu(){
        MenuBarItem item = new MenuBarItem("N", skin);
        item.setColor(0, 200,200,1);
        item.addAction(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StaticServerInterface.sendMessage(MessageModelCreator.createNextRoundMessage());
            }
        });
        items.add(item);
    }

    public void addAllTableMenu(){
        MenuBarItem item = new MenuBarItem("All Table", skin);
        item.setColor(75,0,130,1);
        item.addAction(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ScreenLoader.changeScreen(ScreenLoader.Screens.ALL_TABLE);
            }
        });
        items.add(item);
    }

    public void addDiceMenu(final int dicePages){
        MenuBarItem item = new MenuBarItem("D" + dicePages, skin);
        item.setColor(0f,0.5f,1f,1);
        item.addAction(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                int diceNumber = random.nextInt(dicePages) + 1;
                StaticServerInterface.sendMessage(MessageModelCreator.createDiceMessage(diceNumber, dicePages, Self.user.getCharacter()));
            }
        });
        items.add(item);
    }

    public void addLeafMenu(){
        MenuBarItem item = new MenuBarItem("X",skin);
        item.setColor(1f,0f,0.5f,1);
        item.addAction(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StaticServerInterface.sendMessage(MessageModelCreator.createLeafGameMessage());
                Self.leafGame();
            }
        });
        items.add(item);
    }

    private void drawItems(){
        group = new HorizontalGroup();
        group.space(5);
        for(MenuBarItem item: items){
            group.addActor(item.getButton());
        }
    }

    public HorizontalGroup getMenuBar(){
        drawItems();
        return group;
    }

    public List<MenuBarItem> getItems(){return items;}
}
