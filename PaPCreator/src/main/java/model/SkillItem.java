package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SkillItem {

    public StringProperty skillUUID;

    public SkillItem(String skillUUID){
        this.skillUUID = new SimpleStringProperty(skillUUID);
    }
}
