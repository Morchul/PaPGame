package ui.custom;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ui.StaticFunction;

import java.io.IOException;
import java.io.InputStream;

public class CustomTreeCellFactory extends TreeCell<String> {

    private ContextMenu addMenu = new ContextMenu();
    private ImageView image;

    public CustomTreeCellFactory() {

        try (InputStream in = getClass().getResourceAsStream("/folder-icon.png")) {
            image = new ImageView(new Image(in));
            image.setFitHeight(20);
            image.setFitWidth(20);
        } catch (IOException e){
            System.err.println("Can't load icon");
        }

        MenuItem addSubFolder = new MenuItem("Add Folder");
        addSubFolder.setOnAction(event -> {
            String input = StaticFunction.getInputText();
            if(!input.equals("")){
                CustomTreeViewBranch subFolder = new CustomTreeViewBranch(input);
                getTreeItem().getChildren().add(subFolder);
                getTreeItem().setExpanded(true);
            }
        });

        MenuItem addCard = new MenuItem("Add Card");
        addCard.setOnAction(event -> {
            String input = StaticFunction.getInputText();
            if(input != null){
                CustomTreeViewLeaf item = new CustomTreeViewLeaf(input);
                getTreeItem().getChildren().add(item);
                getTreeItem().setExpanded(true);
            }
        });

        addMenu.getItems().addAll(addCard, addSubFolder);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        } else {
            setText(item);
            setGraphic(null);
            if(getTreeItem() instanceof CustomTreeViewBranch){
                setGraphic(image);
                setContextMenu(addMenu);
            }
        }
    }
}
