package com.morchul.card;

import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.abstractmodels.Creatures;

public class CardCreator {

    public static Card createCard(Anything a) {
        Card c = new Card(a);
        c.addCloseButton()
                .addName()
                .addImage()
                .addDescription();
        return c;
    }

    public static Card createCard(Creatures a) {
        Card c = new Card(a);
        c.addCloseButton()
                .addName()
                .addImage()
                .addDescription()
                .addCreaturesValues(a);
        return c;
    }
}
