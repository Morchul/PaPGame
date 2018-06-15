package com.morchul.action;

import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.abstractmodels.Creatures;

public interface Action {
    boolean action(Anything source, Creatures target);
    boolean reverseAction(Anything source, Creatures target);
    String getActionText();
}
