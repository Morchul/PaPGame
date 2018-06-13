package com.morchul.inventory;

import com.morchul.model.abstractmodels.Objects;

public interface InventoryItem {

    void addOne();
    boolean removeOne();
    void remove();
    int getCount();
    Objects getItem();
    void addListener(InventoryItemListener listener);
}
