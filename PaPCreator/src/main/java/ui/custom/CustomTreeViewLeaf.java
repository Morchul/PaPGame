package ui.custom;

import javafx.scene.control.TreeItem;
import javafx.scene.layout.GridPane;
import model.Model;

public class CustomTreeViewLeaf extends TreeItem<String> {

    private Model model;

    public CustomTreeViewLeaf(String name) {
        this(new Model(name));
    }

    public CustomTreeViewLeaf(Model model){
        this.model = model;
        setValue(model.name.get());
        this.model.name.addListener((observable, oldValue, newValue) -> setValue(newValue));
    }

    public GridPane load(){
        return model.loadEditing();
    }

    public Model getModel(){return model;}
}
