package com.morchul.ui.dragdrop.interfaces;

import com.morchul.card.Card;
import com.morchul.model.abstractmodels.Anything;

public interface DragSource extends MainDragAndDrop {

    void dragAction();

    Anything getItem();
    Card getCard();

  /**
   *
   * @param object the Object on which you will drop
   * @return the Drop Type
   */
  DropTarget.DropActionType getDropType(DropTarget object);
}
