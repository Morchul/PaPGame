package com.morchul.ui.components;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.morchul.PaPHelper;
import com.morchul.collection.Collection;
import com.morchul.collection.Collections;
import com.morchul.collection.item.CollectionItem;
import com.morchul.ui.dragdrop.interfaces.MainDragAndDropAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreatorView {

    private Skin skin;
    private Collections collections;
    private Tree creatorTree;
    private ScrollPane scrollPane;
    private Logger log = LoggerFactory.getLogger(PaPHelper.class);

    public CreatorView(Skin skin, Collections collections) {
        this.skin = skin;
        this.collections = collections;
        createTree();
        scrollPane = new ScrollPane(creatorTree);
    }

    public ScrollPane getCreatorTree(){return scrollPane;}

    private void createTree(){
        Tree tree = new Tree(skin);
        if(collections == null || collections.isEmpty()){
            log.error("No Collection found");
            this.creatorTree = tree;
            return;
        }
        for(Collection collection : collections.getCollections()){
            tree.add(getBranch(collection.root));
        }
        this.creatorTree = tree;
    }

    private Tree.Node getBranch(CollectionItem parentItem){
        Tree.Node parent = toNode(parentItem);
        for(CollectionItem item : parentItem.getChildren()){
            if(item.isBranch()){
                parent.add(getBranch(item));
            } else if(item.isLeaf()){
                parent.add(getLeaf(item));
            }
        }
        return parent;
    }

    private Tree.Node getLeaf(CollectionItem leafItem){
        Tree.Node leaf = toNode(leafItem);
        MainDragAndDropAdmin.addSource(leaf.getActor(), leafItem, leafItem.getName());
        return leaf;
    }

    private Tree.Node toNode(CollectionItem item){
        return new Tree.Node(new Label(item.getName(), skin));
    }
}
