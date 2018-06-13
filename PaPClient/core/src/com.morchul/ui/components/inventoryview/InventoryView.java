package com.morchul.ui.components.inventoryview;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.morchul.inventory.Inventory;
import com.morchul.inventory.ClientInventoryItem;
import com.morchul.inventory.InventoryItem;
import com.morchul.inventory.InventoryListener;

public class InventoryView extends Actor {

    private VerticalGroup group;
    private ScrollPane scrollPane;
    private Inventory inventory;
    private Skin skin;

    public InventoryView(Inventory inventory, final Skin skin) {
        group = new VerticalGroup();
        this.inventory = inventory;
        this.skin = skin;
        inventory.addListener(new InventoryListener() {
            @Override
            public void newItem(InventoryItem item) { update(); }
            @Override
            public void deleteItem(InventoryItem item) { update(); }
        });
        update();
        scrollPane = new ScrollPane(group);
    }

    private void update(){
        group.clear();
        for(InventoryItem item : inventory.getInventoryList()){
            group.addActor(new InventoryViewItem((ClientInventoryItem) item, skin).getView());
        }
    }

    public ScrollPane getInventoryList() {
        return scrollPane;
    }

    public Inventory getInventory() { return inventory; }
}
