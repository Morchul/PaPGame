package com.morchul.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class StaticUIValues {

    public static final Skin DEFAULT_SKIN = new Skin(Gdx.files.internal("skin/uiskin.json"));

    public static Vector2 getStageLocation(Actor a){
        return a.localToStageCoordinates(new Vector2(0,0));
    }


    public static final int TOP_BAR_HEIGHT = 75;
    public static final int LEFT_BAR_WIDTH = 160;
    public static final int RIGHT_CREATURE_VIEW_WIDTH = 160;
    public static final int RIGHT_STATUS_VIEW_WIDTH = 150;
    public static final int BOTTOM_SKILL_BAR_HEIGHT = 180;

    public static final int CARD_WIDTH = 150;
    public static final int CARD_CLOSE_BUTTON_SIZE = 1;

    public static final int INSPECTION_VIEW_TEXTFIELD_WIDTH = 50;
    public static final int INSPECTION_VIEW_TEXTFIELD_HEIGHT = 25;
    public static final int INSPECTION_VIEW_COMPONENT_WIDTH = 150;
    public static final int LONG_INSPECTION_VIEW_COMPONENT_WIDTH = 200;

    public static final int CARD_IMAGE_SIZE = CARD_WIDTH;
    public static final int CARD_VALUE_WIDTH = CARD_WIDTH / 2;

    public static final int STATUS_IMAGE_SIZE = 70;
    public static final int STATUS_WIDTH = 100;
    public static final int STATUS_HEIGHT = 400;

    public static final int INVENTORY_IMAGE_SIZE = 25;

    public static final int CREATURE_VALUE_WIDTH = RIGHT_CREATURE_VIEW_WIDTH / 4;

    public static final int CHARACTER_FIELD_SIZE = 40;
    public static final int CHARACTER_VIEW_CONTAINER_WIDTH = 300;
    public static final int CHARACTER_VIEW_HEIGHT = 300;
    public static final int CHARACTER_VIEW_POS_X = 0;
    public static final int CHARACTER_VIEW_POS_Y = 0;
    public static final int CHARACTER_VALUE_VIEW_HEIGHT = 300;

    public static final int SKILL_IMAGE_SIZE = 70;
    public static final int SKILL_WIDTH = 100;
    public static final int SKILL_HEIGHT = BOTTOM_SKILL_BAR_HEIGHT;

    public static final int ALL_TABLE_SPAWN_POS_X = 200;
    public static final int ALL_TABLE_SPAWN_POS_Y = 200;
    public static final int ALL_TABLE_COMPONENT_WIDTH = RIGHT_CREATURE_VIEW_WIDTH;
    public static final int ALL_TABLE_COMPONENT_HEIGHT = 50;

    public static final int CREATURE_COMPONENT_WIDTH = RIGHT_CREATURE_VIEW_WIDTH;
    public static final int CREATURE_COMPONENT_HEIGHT = 50;

    public static final int LOBBY_PLAYER_LIST_WIDTH = 150;
    public static final int LOBBY_PLAYER_LIST_HEIGHT = 200;

    public static final int LOBBY_CHOOSE_CHARACTER_WIDTH = 150;

    public static final int SETTINGS_TABLE_PAD = 10;

    public static final int INSPECTION_VIEW_TOP_LINE_HEIGHT = 25;

    public static final int CUSTOM_NUMBER_SPINNER_BUTTON_WIDTH = 25;
    public static final int CUSTOM_NUMBER_SPINNER_HEIGHT = 25;

}
