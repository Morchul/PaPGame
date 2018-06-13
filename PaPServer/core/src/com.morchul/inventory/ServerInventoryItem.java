package com.morchul.inventory;

import com.morchul.model.abstractmodels.Objects;

public class ServerInventoryItem implements InventoryItem {

    private Objects item;
    private int count;

    public ServerInventoryItem(Objects item) {
        this.item = item;
        count = 1;
    }

    public void addOne(){
        ++count;
    }

    public boolean removeOne(){
        return --count == 0;
    }

    @Override
    public void remove() {
        count = 0;
    }

    public int getCount(){
        return count;
    }

    public Objects getItem() {
        return item;
    }

    @Override
    public void addListener(InventoryItemListener listener) { }
}
