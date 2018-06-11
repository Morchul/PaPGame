package com.morchul.inventory;

import com.morchul.model.abstractmodels.Objects;

public interface InventoryItem {

    void addOne();
    boolean removeOne();
    int getCount();
    Objects getItem();
    void addListener(InventoryItemListener listener);
}
