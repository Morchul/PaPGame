package com.morchul.model.player;

import com.morchul.model.Type;
import com.morchul.model.abstractmodels.Creatures;
import com.morchul.model.models.SimpleCreatures;

public class GameMaster {
    public static final Creatures gameMasterCharacter = new SimpleCreatures("GameMaster","GameMaster","GameMaster","","","",new Type(Type.GAME_MASTER),1,1,0,0,0,0,0,0,true);
}
