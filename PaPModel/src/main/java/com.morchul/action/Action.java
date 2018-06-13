package com.morchul.action;

import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.abstractmodels.Creatures;

public interface Action {
    void action(Anything source, Creatures target);
    void reverseAction(Anything source, Creatures target);
    String getActionText();
}
