package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.GridPane;
import ui.GridPaneHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Model {

    public String uuid;
    public StringProperty name;
    public StringProperty imagePath;
    public StringProperty type;
    public List<Value> values;
    public StringProperty description;
    public StringProperty masterDescription;
    public StringProperty hp;
    public StringProperty maxHp;
    public StringProperty mp;
    public StringProperty maxMp;
    public StringProperty reaction;
    public StringProperty will;
    public StringProperty strength;
    public StringProperty resistance;
    public List<InventoryItem> inventory;
    public List<SkillItem> skills;
    public List<StatusItem> status;
    public BooleanProperty stackable;
    public BooleanProperty dropable;
    public StringProperty action;
    public StringProperty armorValue;
    public BooleanProperty immortal;
    public StringProperty needWeapon;
    public StringProperty weaponValue;
    public StringProperty duration;


    private GridPaneHelper helper = null;

    public Model(String name) {
        uuid = UUID.randomUUID().toString();
        this.name = new SimpleStringProperty(name);
        imagePath = new SimpleStringProperty("");
        type = new SimpleStringProperty("1");
        values = new ArrayList<>();
        description = new SimpleStringProperty("");
        masterDescription = new SimpleStringProperty("");
        hp = new SimpleStringProperty("");
        maxHp = new SimpleStringProperty("");
        mp = new SimpleStringProperty("");
        maxMp = new SimpleStringProperty("");
        reaction = new SimpleStringProperty("");
        will = new SimpleStringProperty("");
        strength = new SimpleStringProperty("");
        resistance = new SimpleStringProperty("");
        inventory = new ArrayList<>();
        skills = new ArrayList<>();
        status = new ArrayList<>();
        stackable = new SimpleBooleanProperty(false);
        dropable = new SimpleBooleanProperty(true);
        action = new SimpleStringProperty("");
        armorValue = new SimpleStringProperty("");
        immortal = new SimpleBooleanProperty(false);
        needWeapon = new SimpleStringProperty("");
        weaponValue = new SimpleStringProperty("");
        duration = new SimpleStringProperty("");

    }

    public GridPane loadEditing(){
        if(helper == null)
            helper = new GridPaneHelper(this);
        return helper;
    }

    public Value newValue(){
        Value v = new Value("name", "0");
        values.add(v);
        return v;
    }
    public InventoryItem newInventoryItem(){
        InventoryItem item = new InventoryItem("");
        inventory.add(item);
        return item;
    }
    public SkillItem newSkill(){
        SkillItem s = new SkillItem("");
        skills.add(s);
        return s;
    }
    public StatusItem newStatus(){
        StatusItem si = new StatusItem("");
        status.add(si);
        return si;
    }
}
