package com.morchul.ui.components.inventoryview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.morchul.inventory.ClientInventoryItem;
import com.morchul.ui.dragdrop.interfaces.MainDragAndDropAdmin;

import static com.morchul.ui.StaticUIValues.INVENTORY_IMAGE_SIZE;

public class InventoryViewItem {

    private Table container;
    private ClientInventoryItem item;
    private Label nameLabel;
    private Label countLabel;
    private Image image;

    public InventoryViewItem(ClientInventoryItem item, Skin skin) {
        container = new Table();
        this.item = item;
        nameLabel = new Label(item.getItem().getName(), skin);
        countLabel = new Label(item.getCount()+"", skin);
        Gdx.app.postRunnable(() -> {
            image = new Image(new Texture(item.getItem().getImagePath()));
            container.row().height(INVENTORY_IMAGE_SIZE);
            container.add(nameLabel);
            if(item.getItem().isStackable())
                container.add(countLabel);
            container.add(image).width(INVENTORY_IMAGE_SIZE);
            System.out.println(image.getWidth());
            System.out.println(image.getHeight());
        });
        item.addListener(count -> countLabel.setText(count+""));

        MainDragAndDropAdmin.addSource(nameLabel, item, item.getItem().getName());
    }

    public Table getView() {
        return container;
    }

    public ClientInventoryItem getItem() {
        return item;
    }
}
