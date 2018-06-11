package collection;

import javafx.scene.control.TreeItem;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import ui.MainScene;
import ui.custom.CustomTreeView;
import ui.custom.CustomTreeViewBranch;
import ui.custom.CustomTreeViewLeaf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static helper.JSONConverter.toModel;

public class CollectionLoader {

    public CollectionLoader() {
        //TODO Standard Constructor
    }

    public CustomTreeView load(MainScene parent){
        DirectoryChooser fileChooser = new DirectoryChooser();
        fileChooser.setTitle("Choose Collection");
        File f = fileChooser.showDialog(new Stage());
        if(f != null && f.isDirectory()){
            CustomTreeView customTreeView = new CustomTreeView(f.getName(), parent);
            loadChildren(f, customTreeView.getRoot());
            return customTreeView;
        } else {
            System.err.println("You haven't choose a valid Directory");
        }
        return null;
    }

    private void loadChildren(File item, TreeItem<String> parent){
        File[] children = item.listFiles();
        if(children != null) {
            for (File f : children) {
                if (f.isDirectory()) {
                    CustomTreeViewBranch cti = new CustomTreeViewBranch(f.getName());
                    parent.getChildren().add(cti);
                    loadChildren(f, cti);
                } else {
                    loadItem(f, parent);
                }
            }
        }
    }

    private void loadItem(File f, TreeItem<String> parent){
        if(f.getName().endsWith(".card")){
            try(FileInputStream input = new FileInputStream(f)) {
                CustomTreeViewLeaf item = readCard(input);
                parent.getChildren().add(item);
            }catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        } else if(f.getName().endsWith(".jpg") || f.getName().endsWith(".png")){

        } else {
            System.err.println("Invalid Card");
        }
    }

    private CustomTreeViewLeaf readCard(FileInputStream input) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(input);
        CustomTreeViewLeaf item = new CustomTreeViewLeaf(toModel((String) in.readObject()));
        in.close();
        return item;
    }
}
