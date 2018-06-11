package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Value {
    public StringProperty name;
    public StringProperty value;

    public Value(String name, String value){
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleStringProperty(value);
    }
}