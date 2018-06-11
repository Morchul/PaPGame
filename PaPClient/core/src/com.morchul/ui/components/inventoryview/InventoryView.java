package com.morchul.ui.components.inventoryview;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.morchul.inventory.Inventory;
import com.morchul.inventory.ClientInventoryItem;
import com.morchul.inventory.InventoryItem;
import com.morchul.inventory.InventoryListener;

public class InventoryView extends Actor {

    private VerticalGroup group;
    private Inventory inventory;

    public InventoryView(Inventory inventory, final Skin skin) {
        group = new VerticalGroup();
        this.inventory = inventory;

        inventory.addListener(new InventoryListener() {
            private Actor a;

            @Override
            public void newItem(InventoryItem item) {
                if(item instanceof ClientInventoryItem) {
                    a = new InventoryViewItem((ClientInventoryItem) item, skin).getView();
                    group.addActor(a);
                    group.pack();
                }
            }

            @Override
            public void deleteItem(InventoryItem item) {
                group.removeActor(a);
            }
        });
    }

    public VerticalGroup getInventoryList() {
        return group;
    }

    public Inventory getInventory() { return inventory; }
}
