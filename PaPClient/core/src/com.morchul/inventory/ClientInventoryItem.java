package com.morchul.inventory;

import com.morchul.card.Card;
import com.morchul.handler.CardUtils;
import com.morchul.model.abstractmodels.Objects;
import com.morchul.ui.dragdrop.interfaces.DragSource;
import com.morchul.ui.dragdrop.interfaces.DropTarget;

import java.util.ArrayList;
import java.util.List;

public class ClientInventoryItem implements InventoryItem, DragSource {

    private Objects item;
    private int count;
    private List<InventoryItemListener> listeners;

    public ClientInventoryItem(Objects item) {
        this.item = item;
        count = 1;
        listeners = new ArrayList<>();
    }

    @Override
    public Card getCard() {
        return CardUtils.getCard(getItem());
    }

    public void addOne(){
        countChangedEvent(++count);
    }

    public boolean removeOne(){
        countChangedEvent(--count);
        return count == 0;
    }

    public void remove(){
        count = 0;
        countChangedEvent(count);
    }

    public int getCount(){
        return count;
    }

    @Override
    public Objects getItem() {
        return item;
    }

    @Override
    public void dragAction() { }

    @Override
    public DropTarget.DropActionType getDropType(DropTarget object) {
        return DropTarget.DropActionType.MOVE;
    }

    @Override
    public void addListener(InventoryItemListener listener){
        listeners.add(listener);
    }
    private void countChangedEvent(int count){
        for(InventoryItemListener l: listeners){
            l.countChangedEvent(count);
        }
    }
}
