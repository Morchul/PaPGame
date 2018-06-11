package collection;

import javafx.scene.control.TreeItem;
import ui.custom.CustomTreeView;
import ui.custom.CustomTreeViewBranch;
import ui.custom.CustomTreeViewLeaf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.*;

import static helper.JSONConverter.toJSON;

public class CollectionBuilder {

    public void createCollection(CustomTreeView collection){
        System.out.println("Create collection...");
        createBranch(collection.getRoot(), Paths.get("").toString());
        System.out.println("Finish create collection in: " + Paths.get("").toAbsolutePath().toString());
    }

    private void createLeaf(CustomTreeViewLeaf item, String parent){
        File card = new File(parent + "/" + item.getModel().name.get() + ".card");
            String path = item.getModel().imagePath.get();
            Path from = Paths.get(path);
            Path to = Paths.get(parent + "/" + item.getModel().name.get() + path.substring(path.length() - 4, path.length()));
            CopyOption[] options = new CopyOption[]{
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES
            };
            if(from != to && !path.startsWith(item.getModel().name.get()))
                try {
                    Files.copy(from, to, options);
                } catch (IOException e) { e.printStackTrace(); }
        try(FileOutputStream writer = new FileOutputStream(card)) {
            createItem(item, writer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void createItem(CustomTreeViewLeaf item, FileOutputStream output) throws IOException {
        String text = toJSON(item.getModel()).toString();
        System.out.println("OUT: " + text);
        ObjectOutputStream out = new ObjectOutputStream(output);
        out.writeObject(text);
        out.close();
        System.out.println("Item created");
    }

    private void createBranch(TreeItem<String> it, String parent){
        File dir = new File(parent + "/" + it.getValue());
        System.out.println("Create branch: " + dir.getAbsolutePath());
        System.out.println(dir.mkdir());
        for(TreeItem<String> item : it.getChildren()){
            if(item instanceof CustomTreeViewBranch){
                createBranch(item, dir.getPath());
            } else if(item instanceof CustomTreeViewLeaf){
                createLeaf((CustomTreeViewLeaf) item, dir.getPath());
            }
        }
    }
}
