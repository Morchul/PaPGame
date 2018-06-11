package com.morchul.inventory;

public interface InventoryListener {
    void newItem(InventoryItem item);
    void deleteItem(InventoryItem item);
}
