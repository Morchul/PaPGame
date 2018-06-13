package com.morchul.ui.components.inspectionview;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.morchul.card.Card;
import com.morchul.connections.StaticServerInterface;
import com.morchul.connections.message.MessageModelCreator;
import com.morchul.handler.CardUtils;
import com.morchul.inventory.InventoryItem;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.abstractmodels.Creatures;
import com.morchul.ui.dragdrop.interfaces.DragSource;
import com.morchul.ui.dragdrop.interfaces.DropTarget;
import com.morchul.ui.dragdrop.interfaces.MainDragAndDropAdmin;

public class InspectionViewInventoryItem implements DragSource {

    private InventoryItem item;
    private HorizontalGroup group;
    private Creatures creatures;

    public InspectionViewInventoryItem(Creatures creatures, InventoryItem item, Skin skin) {
        this.item = item;
        this.creatures = creatures;
        group = new HorizontalGroup();
        group.space(4);

        final Label countLabel = new Label(item.getCount()+"", skin);
        final TextButton remove = new TextButton("-", skin);
        remove.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StaticServerInterface.sendMessage(MessageModelCreator.createRemoveItemTotalFromInventory(item,creatures));
            }});
        item.addListener(count -> countLabel.setText(count+""));
        group.addActor(new Label(item.getItem().getName(), skin));
        group.addActor(countLabel);
        group.addActor(remove);

        MainDragAndDropAdmin.addSource(group, this, item.getItem().getName());
    }

    public HorizontalGroup getView() {
        return group;
    }

    public Creatures getCreatures() { return creatures; }

    @Override
    public void dragAction() { }

    @Override
    public Anything getItem() {
        return item.getItem();
    }

    @Override
    public Card getCard() {
        return CardUtils.getCard(getItem());
    }

    @Override
    public DropTarget.DropActionType getDropType(DropTarget object) {
        return DropTarget.DropActionType.MOVE;
    }
}
