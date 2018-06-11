package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StatusItem {

    public StringProperty statusUUID;

    public StatusItem(String statusUUID){
        this.statusUUID = new SimpleStringProperty(statusUUID);
    }
}
