package com.morchul.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.morchul.PaPHelper;
import com.morchul.card.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UICardCreator {

    private Stage stage;
    private Logger log = LoggerFactory.getLogger(PaPHelper.class);

    public UICardCreator(Stage stage) {
        this.stage = stage;
    }

    public void createCard(Card card, float x, float y){
        log.info("Create card: " + card.getItem().getUUID());
        Gdx.app.postRunnable(() -> stage.addActor(card));
        card.setPosition(x,y);
    }
}
