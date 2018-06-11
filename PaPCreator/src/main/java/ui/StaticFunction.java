package ui;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class StaticFunction {

    public static String getInputText(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Input Name");
        dialog.setHeaderText(null);
        dialog.setContentText("Please enter name:");

        Optional<String> result = dialog.showAndWait();
        return result.orElse("");
    }
}
