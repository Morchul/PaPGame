package com.morchul.collection.item;

import com.morchul.PaPHelper;
import com.morchul.card.Card;
import com.morchul.collection.Collection;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.ui.dragdrop.interfaces.DropTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CollectionBranchItem implements CollectionItem {

  private List<CollectionItem> children;
  private String name;
  private Logger log = LoggerFactory.getLogger(PaPHelper.class);


  public CollectionBranchItem(String path, Collection collection) {
    children = new ArrayList<>();
    File thisFile = new File(path);
    name = thisFile.getName();
    File[] files = thisFile.listFiles();
    if (files != null) {
      for (File f : files) {
        if(f.isDirectory()){
          children.add(new CollectionBranchItem(f.getPath(), collection));
        } else if (f.getName().matches(".+.card$")){
          children.add(new CollectionLeafItem(f.getPath(), collection));
        }
      }
    }
  }

  @Override
  public List<CollectionItem> getChildren() {
    return children;
  }

  @Override
  public boolean isBranch() {
    return true;
  }

  @Override
  public boolean isLeaf() {
    return false;
  }

    @Override
    public Card getCard() {
        log.error("Branch has no Card Item");
        return null;
    }

    @Override
    public Anything getItem() {
      log.error("Branch has no Item");
      return null;
    }

  @Override
  public void create() {
    for(CollectionItem child: children){
      child.create();
    }
  }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void createLater() {
        for(CollectionItem child: children){
            child.createLater();
        }
    }

    @Override
    public void dragAction() { }

    @Override
    public DropTarget.DropActionType getDropType(DropTarget object) { return DropTarget.DropActionType.NONE; }
}
