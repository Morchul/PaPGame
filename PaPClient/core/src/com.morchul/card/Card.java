package com.morchul.card;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.morchul.model.ChangeListener;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.models.Creatures;
import com.morchul.ui.dragdrop.interfaces.DragSource;
import com.morchul.ui.dragdrop.interfaces.DropTarget;
import com.morchul.ui.dragdrop.interfaces.MainDragAndDropAdmin;

import static com.morchul.ui.StaticUIValues.*;

public class Card extends Table implements DragSource, DropTarget {

    private Anything item;
    private Skin skin = DEFAULT_SKIN;
    private Image image;

    public Card(Anything item) {
        setDebug(true);
        setSkin(skin);
        setWidth(CARD_WIDTH);
        this.item = item;
        item.addListener(new ChangeListener() {
            @Override
            public void destroyEvent() { destroy(); }

            @Override
            public void destroyCardEvent() { destroy(); }
        });
        if(!item.getImagePath().equals(""))
            image = new Image(new Texture(item.getImagePath()));
        MainDragAndDropAdmin.addSource(this, this);
        MainDragAndDropAdmin.addTarget(this, this);
    }

    @Override
    public Anything getItem() {
        return item;
    }

    @Override
    public Card getCard() {
        return this;
    }

    public Card addCloseButton(){
        row().height(CARD_CLOSE_BUTTON_SIZE);
        add(getCloseButton()).right().colspan(2).width(CARD_CLOSE_BUTTON_SIZE);
        return this;
    }

    public Card addName(){
        row();
        add(item.getName()).colspan(2);
        return this;
    }

    public Card addImage(){
        row();
        if(image != null)
            add(image).colspan(2).width(CARD_IMAGE_SIZE).height(CARD_IMAGE_SIZE);
        return this;
    }

    public Card addDescription(){
        row();
        add(item.getDescription()).colspan(2).fill();
        return this;
    }

    public Card addMasterDescription(){
        row();
        add(item.getMasterDescription()).colspan(2);
        return this;
    }

    public Card addCreaturesValues(Creatures c){
        row();
        add("HP").width(CREATURE_VALUE_WIDTH);
        add(c.getHp()+"").width(CREATURE_VALUE_WIDTH);
        row();
        add("MP").width(CREATURE_VALUE_WIDTH);
        add(c.getMp()+"").width(CREATURE_VALUE_WIDTH);
        row();
        add("Reaction").width(CREATURE_VALUE_WIDTH);
        add(c.getReaction()+"").width(CREATURE_VALUE_WIDTH);
        row();
        add("Will").width(CREATURE_VALUE_WIDTH);
        add(c.getWill()+"").width(CREATURE_VALUE_WIDTH);
        row();
        add("Strength").width(CREATURE_VALUE_WIDTH);
        add(c.getStrength()+"").width(CREATURE_VALUE_WIDTH);
        row();
        add("Resistance").width(CREATURE_VALUE_WIDTH);
        add(c.getResistance()+"").width(CREATURE_VALUE_WIDTH);
        return this;
    }

    private void destroy(){
        item.setCard(null);
        remove();
    }

    private TextButton getCloseButton(){
        TextButton close = new TextButton("x", skin);
        close.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                destroy();
            }
        });
        return close;
    }

    @Override
    public void dragAction() {

    }

    @Override
    public DropTarget.DropActionType getDropType(DropTarget object) {
        return DropTarget.DropActionType.NONE;
    }

    @Override
    public void dropAction(DropActionType type, DragSource object) {

    }
}
