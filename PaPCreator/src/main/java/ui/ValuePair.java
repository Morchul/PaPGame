package ui;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Value;

public class ValuePair {

    private TextField nameField;
    private TextField valueField;

    public ValuePair(Value item, GridPane gridPane, int i) {
        nameField = new TextField(item.name.get());
        valueField = new TextField(item.value.get());
        valueField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("\\d*"))
                valueField.setText(newValue.replaceAll("[^\\d]", ""));
        });
        item.name.bind(nameField.textProperty());
        item.value.bind(valueField.textProperty());

        gridPane.add(nameField,0, i);
        gridPane.add(valueField, 1, i);

    }
}
