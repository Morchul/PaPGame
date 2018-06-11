package ui;

import com.morchul.model.Type;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;

import java.io.File;

public class GridPaneHelper extends GridPane {

    private TextField uuidField;
    private TextField nameField;
    private TextField imagePathField;
    private TextField typeField;
    private TextArea descriptionArea;
    private TextArea masterDescriptionArea;
    private TextField hpField;
    private TextField maxHpField;
    private TextField mpField;
    private TextField maxMpField;
    private TextField reactionField;
    private TextField willField;
    private TextField strengthField;
    private TextField resistanceField;
    private CheckBox stackableBox;
    private CheckBox dropableBox;
    private CheckBox immortalBox;
    private TextArea actionArea;
    private TextField armorValueField;
    private TextField weaponValueField;
    private TextField needWeaponField;
    private TextField durationField;
    private Button addValueButton;
    private Button addInventoryItemButton;
    private Button addSkillButton;
    private Button addStatusButton;

    private int i = 0;

    public GridPaneHelper(Model model) {
        setVgap(5);
        setHgap(5);
        setPadding(new Insets(5,5,5,5));

        uuidField = new TextField(model.uuid);
        uuidField.setEditable(false);
        add(new Label("uuid: "),0,i);
        add(uuidField,1,i);
        ++i;
        nameField = newTextField("Name", model.name);
        imagePathField = addImage(model.imagePath);
        typeField = newNumberTextField("Type", model.type);
        typeField.textProperty().addListener((observable, oldValue, newValue) -> changeFieldDisable(newValue));
        descriptionArea = newTextArea("Description", model.description);
        masterDescriptionArea = newTextArea("Master description", model.masterDescription);
        hpField = newNumberTextField("Hp", model.hp);
        maxHpField = newNumberTextField("MaxHp", model.maxHp);
        mpField = newNumberTextField("Mp", model.mp);
        maxMpField = newNumberTextField("MaxMp", model.maxMp);
        reactionField = newNumberTextField("Reaction", model.reaction);
        willField = newNumberTextField("Will", model.will);
        strengthField = newNumberTextField("Strength", model.strength);
        resistanceField = newNumberTextField("Resistance", model.resistance);
        stackableBox = newCheckBox("Stackable", model.stackable);
        dropableBox = newCheckBox("Dropable", model.dropable);
        immortalBox = newCheckBox("Immortal", model.immortal);
        actionArea = newTextArea("Action", model.action);
        armorValueField = newNumberTextField("ArmorValue", model.armorValue);
        weaponValueField = newNumberTextField("WeaponValue", model.weaponValue);
        needWeaponField = newNumberTextField("NeedWeapon", model.needWeapon);
        durationField = newNumberTextField("Duration", model.duration);
        addValueButton = addValue(model);
        addInventoryItemButton = addInventoryItems(model);
        addSkillButton = addSkills(model);
        addStatusButton = addStatus(model);

        changeFieldDisable(model.type.get());
    }

    private void changeFieldDisable(String type){
        Type t = new Type(type);
        hpField.setDisable(!t.isType(Type.CREATURES));
        maxHpField.setDisable(!t.isType(Type.CREATURES));
        mpField.setDisable(!t.isType(Type.CREATURES));
        maxMpField.setDisable(!t.isType(Type.CREATURES));
        reactionField.setDisable(!t.isType(Type.CREATURES));
        willField.setDisable(!t.isType(Type.CREATURES));
        strengthField.setDisable(!t.isType(Type.CREATURES));
        resistanceField.setDisable(!t.isType(Type.CREATURES));
        stackableBox.setDisable(!t.isType(Type.OBJECTS));
        dropableBox.setDisable(!t.isType(Type.OBJECTS));
        immortalBox.setDisable(!t.isType(Type.NPC));
        actionArea.setDisable(!t.isType(Type.WEARABLE) && !t.isType(Type.STATUS));
        armorValueField.setDisable(!t.isType(Type.ARMOR));
        weaponValueField.setDisable(!t.isType(Type.WEAPON));
        needWeaponField.setDisable(!t.isType(Type.SKILLS));
        durationField.setDisable(!t.isType(Type.STATUS));
        addInventoryItemButton.setDisable(!t.isType(Type.CREATURES));
        addSkillButton.setDisable(!t.isType(Type.CREATURES));
        addStatusButton.setDisable(!t.isType(Type.CREATURES));
    }

    private TextField newTextField(String name, StringProperty property){
        TextField textField = new TextField(property.get());
        property.bind(textField.textProperty());
        add(new Label(name), 0,i);
        add(textField,1,i);
        ++i;
        return textField;
    }

    private TextField newNumberTextField(String name, StringProperty property){
        TextField numberField = new TextField(property.get());
        numberField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("\\d*"))
                numberField.setText(newValue.replaceAll("[^\\d]", ""));
        });
        property.bind(numberField.textProperty());
        add(new Label(name),0,i);
        add(numberField,1,i);
        ++i;
        return numberField;
    }

    private TextArea newTextArea(String name, StringProperty property){
        TextArea textArea = new TextArea(property.get());
        property.bind(textArea.textProperty());
        add(new Label(name),0,i);
        add(textArea,1,i);
        ++i;
        return textArea;
    }

    private CheckBox newCheckBox(String name, BooleanProperty property){
        CheckBox box = new CheckBox();
        box.setSelected(property.get());
        property.bind(box.selectedProperty());
        add(new Label(name),0,i);
        add(box,1,i);
        ++i;
        return box;
    }

    private TextField addImage(StringProperty imagePath){
        TextField image = new TextField(imagePath.get());
        final Button browse = new Button("Browse");
        browse.setOnAction(event -> {
            File f;
            if((f = imageChooser()) != null)
                image.setText(f.getPath());
        });
        imagePath.bind(image.textProperty());
        add(new Label("image:"),0,i);
        add(image, 1, i);
        add(browse, 2,i);
        ++i;
        return image;
    }

    private Button addValue(Model model){
        Button b = new Button("new Value");
        b.setOnAction(event -> new ValuePair(model.newValue(), this, ++i));
        add(b, 0, i);

        for(Value value: model.values){
            new ValuePair(value, this, ++i);
        }
        ++i;
        return b;
    }

    private Button addInventoryItems(Model model){
        Button b = new Button("new InventoryItem");
        b.setOnAction(event -> {
            TextField uuid = new TextField("uuid");
            typeField.textProperty().addListener((observable, oldValue, newValue) -> uuid.setDisable(!new Type(newValue).isType(Type.CREATURES)));
            model.newInventoryItem().itemUUID.bind(uuid.textProperty());
            add(new Label("InventoryItem uuid:"),0,i);
            add(uuid,1,i);
            ++i;
        });
        add(b, 0, i);

        for(InventoryItem value: model.inventory){
            TextField uuid = new TextField(value.itemUUID.get());
            typeField.textProperty().addListener((observable, oldValue, newValue) -> uuid.setDisable(!new Type(newValue).isType(Type.CREATURES)));
            value.itemUUID.bind(uuid.textProperty());
            add(new Label("InventoryItem uuid:"),0,i);
            add(uuid,1,i);
            ++i;
        }
        ++i;
        return b;
    }

    private Button addSkills(Model model){
        Button b = new Button("new Skill");
        b.setOnAction(event -> {
            TextField uuid = new TextField("uuid");
            typeField.textProperty().addListener((observable, oldValue, newValue) -> uuid.setDisable(!new Type(newValue).isType(Type.CREATURES)));
            model.newSkill().skillUUID.bind(uuid.textProperty());
            add(new Label("Skill uuid:"),0,i);
            add(uuid,1,i);
            ++i;
        });
        add(b, 0, i);

        for(SkillItem value: model.skills) {
            TextField uuid = new TextField(value.skillUUID.get());
            typeField.textProperty().addListener((observable, oldValue, newValue) -> uuid.setDisable(new Type(newValue).isType(Type.CREATURES)));
            value.skillUUID.bind(uuid.textProperty());
            add(new Label("Skill uuid:"), 0, i);
            add(uuid, 1, i);
            ++i;
        }
        ++i;
        return b;
    }

    private Button addStatus(Model model){
        Button b = new Button("new Status");
        b.setOnAction(event -> {
            TextField uuid = new TextField("uuid");
            typeField.textProperty().addListener((observable, oldValue, newValue) -> uuid.setDisable(new Type(newValue).isType(Type.CREATURES)));
            model.newStatus().statusUUID.bind(uuid.textProperty());
            add(new Label("Status uuid:"),0,i);
            add(uuid,1,i);
            ++i;
        });
        add(b, 0, i);

        for(StatusItem value: model.status){
            TextField uuid = new TextField(value.statusUUID.get());
            typeField.textProperty().addListener((observable, oldValue, newValue) -> uuid.setDisable(new Type(newValue).isType(Type.CREATURES)));
            value.statusUUID.bind(uuid.textProperty());
            add(new Label("Status uuid:"),0,i);
            add(uuid,1,i);
            ++i;
        }
        ++i;
        return b;
    }

    private File imageChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");
        return fileChooser.showOpenDialog(new Stage());
    }
}
