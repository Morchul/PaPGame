package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InventoryItem {
    public StringProperty itemUUID;

    public InventoryItem(String itemUUID){
        this.itemUUID = new SimpleStringProperty(itemUUID);
    }
}
