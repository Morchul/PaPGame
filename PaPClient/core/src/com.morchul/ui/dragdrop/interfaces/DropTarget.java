package com.morchul.ui.dragdrop.interfaces;


public interface DropTarget extends MainDragAndDrop {

  /**
   *
   * @param type The Drop type
   * @param object the object which will drop
   */
  void dropAction(DropActionType type, DragSource object);

    enum DropActionType {
        MOVE, // Use for elements which can be move to creature or other player
        CARD, //Use for elements which can not be moved to creature or other player
        MOVE_NEW, //Use for CollectionLeafItem (when create a new GameUUID)
        NONE //use for cards
    }
}
