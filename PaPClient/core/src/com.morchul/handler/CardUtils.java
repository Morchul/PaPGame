package com.morchul.handler;

import com.morchul.card.Card;
import com.morchul.card.CardCreator;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.abstractmodels.Creatures;

public class CardUtils {

    public static Card getCard(Anything any){
        Object o = any.getCard();
        if(o == null){
            if(any instanceof Creatures)
                any.setCard(CardCreator.createCard((Creatures)any));
            else
                any.setCard(CardCreator.createCard(any));
        }
        return (Card)any.getCard();
    }
}
