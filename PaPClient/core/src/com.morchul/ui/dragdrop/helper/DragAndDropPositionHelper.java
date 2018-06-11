package com.morchul.ui.dragdrop.helper;

import com.badlogic.gdx.math.Vector2;
import com.morchul.card.Card;


public class DragAndDropPositionHelper {

  private DragAndDropPositionHelper(){}

  private static float dragPositionX = 0;
  private static float dragPositionY = 0;
  private static float dropPositionX = 0;
  private static float dropPositionY = 0;

  public static void setDragPositionX(float x){
    dragPositionX = x;
  }
  public static void setDragPositionY(float y){
    dragPositionY = y;
  }
  public static void setDropPositionX(float x){
    dropPositionX = x;
  }
  public static void setDropPositionY(float y){
    dropPositionY = y;
  }
  public static float getDragPositionX(){ return dragPositionX; }
  public static float getDragPositionY(){
    return dragPositionY;
  }
  public static float getDropPositionX(){
    return dropPositionX;
  }
  public static float getDropPositionY(){
    return dropPositionY;
  }

  public static void setDragPosition(Vector2 pos){
        setDragPositionX(pos.x);
        setDragPositionY(pos.y);
  }
  public static void setDropPosition(Vector2 pos){
        setDropPositionX(pos.x);
        setDropPositionY(pos.y);
  }

  public static void setCardBackToDragPosition(Card c) {
        c.setPosition(getDragPositionX(), getDragPositionY());
  }
}
