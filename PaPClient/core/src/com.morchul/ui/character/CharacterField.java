package com.morchul.ui.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.morchul.PaPHelper;
import com.morchul.card.Card;
import com.morchul.handler.CardUtils;
import com.morchul.inventory.ClientInventoryItem;
import com.morchul.model.ChangeListener;
import com.morchul.model.Type;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.abstractmodels.Wearable;
import com.morchul.model.models.Creatures;
import com.morchul.ui.dragdrop.interfaces.DragSource;
import com.morchul.ui.dragdrop.interfaces.DropTarget;
import com.morchul.ui.dragdrop.interfaces.MainDragAndDropAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.morchul.ui.StaticUIValues.CHARACTER_FIELD_SIZE;

public class CharacterField implements DropTarget, DragSource {

    private Logger log = LoggerFactory.getLogger(PaPHelper.class);
    private Type type;
    private Creatures creature;
    private Wearable item;
    private Image image;

    public CharacterField(Type type, Creatures creature) {
        this.type = type;
        this.creature = creature;
        image = new Image(new Texture(Gdx.files.internal("EmptyCharacterField.jpg")));
        image.setSize(CHARACTER_FIELD_SIZE, CHARACTER_FIELD_SIZE);
        MainDragAndDropAdmin.addTarget(image, this);
        MainDragAndDropAdmin.addSource(image, this);
    }

    public void pullOn(Wearable we){
        if(we.getType().isType(type)){
            pullOff();
            item = we;
            item.addListener(new ChangeListener() {
                @Override
                public void destroyEvent() { pullOff(); }
                @Override
                public void destroyCardEvent() { }
            });
            item.getPullOnAction().action(item, creature);
            image.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(item.getImagePath()))));
        }
    }

    public void pullOff(){
        if(item != null){
            item.getPullOnAction().reverseAction(item, creature);
            item = null;
        }
    }

    public Image getField(){
        return image;
    }

    @Override
    public void dropAction(DropActionType type, DragSource object) {
        if(type != DropActionType.NONE) {
            if (object.getItem() instanceof Wearable) {
                pullOn((Wearable) ((ClientInventoryItem) object).getItem());
            } else {
                log.info("object is not instance of Wearable: " + object.getClass().getSimpleName());
            }
        }
    }

    @Override
    public void dragAction() { }

    @Override
    public Anything getItem() {
        return item;
    }

    @Override
    public Card getCard() {
        return CardUtils.getCard(getItem());
    }

    @Override
    public DropActionType getDropType(DropTarget object) {
        return DropActionType.MOVE;
    }
}
