package com.morchul.card;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.morchul.model.ChangeListener;
import com.morchul.model.CreaturesListener;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.abstractmodels.Creatures;
import com.morchul.ui.dragdrop.interfaces.DragSource;
import com.morchul.ui.dragdrop.interfaces.DropTarget;
import com.morchul.ui.dragdrop.interfaces.MainDragAndDropAdmin;

import static com.morchul.ui.StaticUIValues.*;

public class Card extends Table implements DragSource, DropTarget {

    private Anything item;
    private Skin skin = DEFAULT_SKIN;
    private Image image;
    private Texture texture;

    private Label hpLabel;
    private Label mpLabel;
    private Label reactionLabel;
    private Label willLabel;
    private Label strengthLabel;
    private Label resistanceLabel;

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

        if(!item.getImagePath().equals("")) {
            texture = new Texture(item.getImagePath());
            image = new Image(texture);
        }


        if(item instanceof Creatures){
            hpLabel = new Label(((Creatures)item).getHp()+"", skin);
            mpLabel = new Label(((Creatures)item).getMp()+"", skin);
            reactionLabel = new Label(((Creatures)item).getReaction()+"", skin);
            willLabel = new Label(((Creatures)item).getWill()+"", skin);
            strengthLabel = new Label(((Creatures)item).getStrength()+"", skin);
            resistanceLabel = new Label(((Creatures)item).getResistance()+"", skin);
            ((Creatures) item).addListener(this::update);
        }

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

    private void update(){
        hpLabel.setText(((Creatures)item).getHp()+"");
        mpLabel.setText(((Creatures)item).getMp()+"");
        reactionLabel.setText(((Creatures)item).getReaction()+"");
        willLabel.setText(((Creatures)item).getWill()+"");
        strengthLabel.setText(((Creatures)item).getStrength()+"");
        resistanceLabel.setText(((Creatures)item).getResistance()+"");
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
        add("HP").width(CARD_VALUE_WIDTH);
        add(hpLabel).width(CARD_VALUE_WIDTH);
        row();
        add("MP").width(CARD_VALUE_WIDTH);
        add(mpLabel).width(CARD_VALUE_WIDTH);
        row();
        add("Reaction").width(CARD_VALUE_WIDTH);
        add(reactionLabel).width(CARD_VALUE_WIDTH);
        row();
        add("Will").width(CARD_VALUE_WIDTH);
        add(willLabel).width(CARD_VALUE_WIDTH);
        row();
        add("Strength").width(CARD_VALUE_WIDTH);
        add(strengthLabel).width(CARD_VALUE_WIDTH);
        row();
        add("Resistance").width(CARD_VALUE_WIDTH);
        add(resistanceLabel).width(CARD_VALUE_WIDTH);
        return this;
    }

    private void destroy(){
        item.setCard(null);
        texture.dispose();
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
