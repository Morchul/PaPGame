package com.morchul.model.player;

import com.morchul.model.abstractmodels.Creatures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User {

    private Logger log = LoggerFactory.getLogger(User.class);

    private String userName;
    private String userUUID;
    private Creatures character;

    public User(String userName, String userUUID) {
        this.userName = userName;
        this.userUUID = userUUID;
    }

    public void setCharacter(Creatures character){
        log.info("Set Character: " + character.getUUID() + " of user: " + userUUID);
        this.character = character;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public Creatures getCharacter() {
        return character;
    }
}
