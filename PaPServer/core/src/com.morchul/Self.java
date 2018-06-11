package com.morchul;

import com.morchul.game.GameWrapper;
import com.morchul.model.player.User;

public class Self {

    private User user;
    private GameWrapper gameWrapper;

    public Self() { }

    public User getUser(){
        return user;
    }

    public void setUser(User user){ this.user = user;}

    public GameWrapper getGameWrapper() {
        return gameWrapper;
    }

    public void setGameWrapper(GameWrapper gameWrapper){
        this.gameWrapper = gameWrapper;
    }
}
