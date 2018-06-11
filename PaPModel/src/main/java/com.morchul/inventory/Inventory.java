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

    public void addItemToInventory(InventoryItem item) {
        InventoryItem i = getInventoryItem(item.getItem().getUUID());
        if(i != null && i.getItem().isStackable()) {
            i.addOne();
        } else {
            inventory.add(item);
            for(InventoryListener l: listeners){
                l.newItem(item);
            }
        }
    }

    private InventoryItem getInventoryItem(String uuid) {
        for(InventoryItem i : inventory){
            if(i.getItem().getUUID().equals(uuid)){
                return i;
            }
        }
        return null;
    }

    public void removeItemFromInventory(InventoryItem item) {
        InventoryItem i = getInventoryItem(item.getItem().getUUID());
        if(i != null){
            if(i.removeOne()){
                inventory.remove(item);
            }
        }
        for(InventoryListener l: listeners){
            l.deleteItem(item);
        }
    }

    public ObservableList<InventoryItem> getInventoryList() {
        return inventory;
    }

    public void addListener(InventoryListener listener) {
        listeners.add(listener);
    }
}
