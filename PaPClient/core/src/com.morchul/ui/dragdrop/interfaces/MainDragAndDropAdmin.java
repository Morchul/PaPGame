package com.morchul.ui.dragdrop.interfaces;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.morchul.card.Card;
import com.morchul.ui.StaticUIValues;
import com.morchul.ui.dragdrop.helper.DragAndDropPositionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.morchul.ui.StaticUIValues.CARD_WIDTH;
import static com.morchul.ui.StaticUIValues.DEFAULT_SKIN;
import static com.morchul.ui.StaticUIValues.getStageLocation;

public class MainDragAndDropAdmin implements MainDragAndDrop {

  private MainDragAndDropAdmin() { }
  private static Logger log = LoggerFactory.getLogger(MainDragAndDropAdmin.class);

  public static void addSource(Actor item, DragSource object){
    addSource(item, object, "");
  }

  public static void addSource(Actor item, DragSource object, String text){
      dragAndDrop.addSource(new DragAndDrop.Source(item) {
        @Override
        public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
          System.out.println("DRAG: " + x + "/" + y);
          DragAndDropPositionHelper.setDragPosition(new Vector2(event.getStageX(), event.getStageY()));
          DragAndDrop.Payload payload = new DragAndDrop.Payload();
          payload.setObject(object);

          if(!text.equals("")){
            payload.setDragActor(new Label(text, DEFAULT_SKIN));
            payload.setValidDragActor(new Label(text, DEFAULT_SKIN));
          }

          item.toFront();
          item.setTouchable(Touchable.disabled);

          Label invalidLabel = new Label("Can't drop here", DEFAULT_SKIN);
          invalidLabel.setColor(1, 0, 0, 1);
          payload.setInvalidDragActor(invalidLabel);
          object.dragAction();
          return payload;
        }

        @Override
        public void dragStop(InputEvent event, float x, float y, int pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
          super.dragStop(event, x, y, pointer, payload, target);
          item.setTouchable(Touchable.enabled);
        }
      });
  }

  public static void addTarget(Actor item, DropTarget object){
    dragAndDrop.addTarget(new DragAndDrop.Target(item) {
      @Override
      public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        if(payload.getObject() instanceof Card) {
          Vector2 pos = getStageLocation(item);
          ((Card) payload.getObject()).setPosition(x+pos.x - (CARD_WIDTH / 2), y+pos.y);
        }
        return true;
      }

      @Override
      public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        Vector2 pos = StaticUIValues.getStageLocation(item);
        DragAndDropPositionHelper.setDropPosition(new Vector2(x+pos.x,y+pos.y));
        log.info("DropAction on " + object.getClass().getSimpleName() +
                " with type: " + ((DragSource)payload.getObject()).getDropType(object) +
                " and object: " + payload.getObject().getClass().getSimpleName());
        object.dropAction(((DragSource)payload.getObject()).getDropType(object), (DragSource) payload.getObject());
      }
    });
  }
}
