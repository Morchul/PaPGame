package ui.custom;

import javafx.scene.control.TreeView;
import ui.MainScene;

public class CustomTreeView extends TreeView<String> {

    private CustomTreeViewBranch rootItem;

    public CustomTreeView(String name, final MainScene parent) {
        rootItem = new CustomTreeViewBranch(name);
        setRoot(rootItem);

        this.setCellFactory(param -> new CustomTreeCellFactory());

        getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue instanceof CustomTreeViewLeaf){
                parent.changeContent((CustomTreeViewLeaf)newValue);
            }
        });
    }
}
