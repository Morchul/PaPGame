package com.morchul.ui.components.inspectionview;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.morchul.inventory.InventoryItem;
import com.morchul.inventory.InventoryListener;
import com.morchul.model.abstractmodels.Creatures;

public class InspectionViewInventory {

    private Table table;
    private Creatures creatures;
    private Skin skin;

    public InspectionViewInventory(Creatures creatures, Skin skin) {
        this.skin = skin;
        this.creatures = creatures;
        table = new Table(skin);
        table.setDebug(false);
        creatures.getInventory().addListener(new InventoryListener() {
            @Override
            public void newItem(InventoryItem item) { update(); }
            @Override
            public void deleteItem(InventoryItem item) { update(); }
        });
        update();
    }

    public void update(){
        table.clear();
        for(InventoryItem item : creatures.getInventory().getInventoryList()){
            table.add(new InspectionViewInventoryItem(creatures, item, skin).getView());
            table.row();
        }
    }

    public Table getTable() {
        return table;
    }
}
