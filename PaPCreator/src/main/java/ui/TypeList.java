package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.TypeMap;

import java.util.Map;

public class TypeList {

    Stage stage;

    public TypeList() {

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5,5,5,5));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        int i = 0;
        for(Map.Entry<String, String> entry: TypeMap.typeMap.entrySet()){
            gridPane.add(new Label(entry.getKey()), 0, i);
            gridPane.add(new Label(entry.getValue()), 1, i);
            ++i;
        }

        stage = new Stage();
        stage.setTitle("Type List");
        stage.setScene(new Scene(gridPane));
    }

    public void show(){
        stage.show();
    }
}
