package ui;

import collection.CollectionBuilder;
import collection.CollectionLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import ui.custom.CustomTreeView;
import ui.custom.CustomTreeViewLeaf;

public class MainScene {

    private BorderPane borderPane;
    private CustomTreeView collection;
    private ScrollPane scrollPane;
    private ScrollPane leftScrollPane;
    private CollectionBuilder builder;
    private CollectionLoader loader;
    private TypeList typeList;

    public MainScene() {
        builder = new CollectionBuilder();
        loader = new CollectionLoader();
        typeList = new TypeList();
    }

    public BorderPane getContent(){
        borderPane = new BorderPane();
        scrollPane = new ScrollPane();
        leftScrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        borderPane.setTop(getMenuBar());
        borderPane.setCenter(scrollPane);
        borderPane.setLeft(leftScrollPane);

        return borderPane;
    }

    private MenuBar getMenuBar(){
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("File");
        MenuItem newCollection = new MenuItem("New Collection");
        newCollection.setOnAction(event -> {
            collection = new CustomTreeView(StaticFunction.getInputText(), this);
            setCollection(collection);
        });
        MenuItem openCollection = new MenuItem("Open Collection");
        openCollection.setOnAction(event -> {
            CustomTreeView collection = loader.load(this);
            this.collection = collection;
            setCollection(collection);
        });
        MenuItem createCollection = new MenuItem("Create Collection");
        createCollection.setOnAction(event -> {
            if(collection != null)
                builder.createCollection(collection);
        });
        menu.getItems().addAll(newCollection, createCollection, openCollection);

        Menu help = new Menu("Help");
        MenuItem showList = new MenuItem("Show Type List");
        showList.setOnAction(event -> typeList.show());
        help.getItems().add(showList);


        menuBar.getMenus().addAll(menu, help);
        return menuBar;
    }

    private void setCollection(CustomTreeView collection){
        if(collection != null)
            leftScrollPane.setContent(collection);
    }

    public void changeContent(CustomTreeViewLeaf item){
        scrollPane.setContent(item.load());
    }
}
