package com.morchul.inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private ObservableList<InventoryItem> inventory;
    private List<InventoryListener> listeners;

    public Inventory() {
        inventory = FXCollections.observableArrayList();
        listeners = new ArrayList<>();
    }

    public void clear(){
        inventory.clear();
    }

    public void addItemToInventory(InventoryItem item) {
        InventoryItem i = getInventoryItemWrapper(item);
        if(i != null && i.getItem().isStackable()) {
            i.addOne();
        } else {
            inventory.add(item);
            for(InventoryListener l: listeners){
                l.newItem(item);
            }
        }
    }

    private InventoryItem getInventoryItemByUUID(String uuid) {
        for(InventoryItem i : inventory){
            if(i.getItem().getUUID().equals(uuid)){
                return i;
            }
        }
        return null;
    }

    private InventoryItem getInventoryItemByGameUUID(String gameUUID){
        for(InventoryItem i : inventory){
            if(i.getItem().getGameUUID().equals(gameUUID)){
                return i;
            }
        }
        return null;
    }

    private InventoryItem getInventoryItemWrapper(InventoryItem item){
        if(item.getItem().isStackable()) {
            return getInventoryItemByUUID(item.getItem().getUUID());
        } else {
            return getInventoryItemByGameUUID(item.getItem().getGameUUID());
        }
    }

    public void removeItemTotalFromInventory(InventoryItem item){
        InventoryItem i = getInventoryItemWrapper(item);
        if(i != null) {
            i.remove();
            destroyItem(i);
        }
    }

    public void removeItemFromInventory(InventoryItem item) {
        InventoryItem i = getInventoryItemWrapper(item);
        if(i != null){
            if(i.removeOne()){
                destroyItem(i);
            }
        }
    }

    private void destroyItem(InventoryItem i){
        inventory.remove(i);
        for(InventoryListener l: listeners){
            l.deleteItem(i);
        }
        i.getItem().destroy();
    }

    public ObservableList<InventoryItem> getInventoryList() {
        return inventory;
    }

    public void addListener(InventoryListener listener) {
        listeners.add(listener);
    }
}
