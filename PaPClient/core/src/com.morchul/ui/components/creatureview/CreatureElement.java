package com.morchul.ui.components.creatureview;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.morchul.PaPHelper;
import com.morchul.Self;
import com.morchul.card.Card;
import com.morchul.connections.StaticServerInterface;
import com.morchul.connections.message.MessageModelCreator;
import com.morchul.handler.CardUtils;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.abstractmodels.Creatures;
import com.morchul.model.abstractmodels.Objects;
import com.morchul.model.models.Skill;
import com.morchul.model.models.Status;
import com.morchul.ui.components.inspectionview.InspectionViewInventoryItem;
import com.morchul.ui.dragdrop.interfaces.DragSource;
import com.morchul.ui.dragdrop.interfaces.DropTarget;
import com.morchul.ui.dragdrop.interfaces.MainDragAndDropAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static com.morchul.ui.StaticUIValues.CREATURE_VALUE_WIDTH;

public class CreatureElement implements DropTarget, DragSource {

    private Creatures creature;
    private Table table;
    private Logger log = LoggerFactory.getLogger(PaPHelper.class);

    private Label hpLabel;
    private Label maxHpLabel;
    private Label mpLabel;
    private Label maxMpLabel;

    public CreatureElement(Creatures creature, Skin skin) {
        this.creature = creature;
        creature.addListener(this::update);

        table = new Table(skin);
        table.setDebug(false);
        table.setTouchable(Touchable.enabled);

        hpLabel = new Label(creature.getHp()+"", skin);
        maxHpLabel = new Label(creature.getMaxHp()+"", skin);
        mpLabel = new Label(creature.getMp()+"", skin);
        maxMpLabel = new Label(creature.getMaxMp()+"", skin);

        table.add(creature.getName()).colspan(8);
        table.row();
        table.add("HP").width(CREATURE_VALUE_WIDTH-10);
        table.add(hpLabel).width(CREATURE_VALUE_WIDTH / 2);
        table.add("/");
        table.add(maxHpLabel).width(CREATURE_VALUE_WIDTH / 2);
        table.add("MP").width(CREATURE_VALUE_WIDTH-10);
        table.add(mpLabel).width(CREATURE_VALUE_WIDTH / 2);
        table.add("/");
        table.add(maxMpLabel).width(CREATURE_VALUE_WIDTH / 2);

        MainDragAndDropAdmin.addTarget(table, this);
        MainDragAndDropAdmin.addSource(table, this, creature.getName());
    }

    private void update(){
        hpLabel.setText(creature.getHp()+"");
        maxHpLabel.setText(creature.getMaxHp()+"");
        mpLabel.setText(creature.getMp()+"");
        maxMpLabel.setText(creature.getMaxMp()+"");
    }

    public Table getCreatureElement(){
        return table;
    }

    public Creatures getCreature() {
        return creature;
    }

    @Override
    public void dropAction(DropActionType type, DragSource object) {

        if (type != DropActionType.NONE && type != DropActionType.CARD) {
            if (type == DropActionType.MOVE_NEW)
                object.getItem().setGameUUID(UUID.randomUUID().toString());
            if(Self.user.getCharacter().getUUID().equals(getCreature().getUUID())) {
                return;
            }
            if(object.getItem() instanceof Objects) {
                if(object instanceof InspectionViewInventoryItem){
                    StaticServerInterface.sendMessage(MessageModelCreator.createMoveItemToCreatureMessage((Objects) object.getItem(), ((InspectionViewInventoryItem) object).getCreatures(), getCreature()));
                } else {
                    StaticServerInterface.sendMessage(MessageModelCreator.createMoveItemToCreatureMessage((Objects) object.getItem(), Self.user.getCharacter(), getCreature()));
                }
            } else if(object.getItem() instanceof Status && Self.game.IamTheGameMaster()){
                StaticServerInterface.sendMessage(MessageModelCreator.createAddStatusMessage((Status) object.getItem(), getCreature()));
            } else if(object.getItem() instanceof Skill && Self.game.IamTheGameMaster()){
                StaticServerInterface.sendMessage(MessageModelCreator.createAddSkillMessage((Skill) object.getItem(), getCreature()));
            } else if(object.getItem() instanceof Creatures && Self.game.IamTheGameMaster()){
                if(((Creatures)object.getItem()).isDead())
                    StaticServerInterface.sendMessage(MessageModelCreator.createLootMessage((Creatures)object.getItem(),getCreature()));

            } else {
                log.info("can't move Item which is not instance of Objects, Status, Skill");
            }

        } else {
            log.info("No action with type: " + type);
        }
    }

    @Override
    public void dragAction() { }

    @Override
    public Anything getItem() {
        return creature;
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
