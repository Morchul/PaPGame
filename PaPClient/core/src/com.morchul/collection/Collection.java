package com.morchul.collection;

import com.morchul.collection.item.CollectionBranchItem;
import com.morchul.collection.item.CollectionItem;
import com.morchul.model.abstractmodels.Anything;

import java.util.HashMap;
import java.util.Map;

public class Collection {

  public Map<String, Anything> items = new HashMap<>();
  public CollectionItem root;

  public Collection(String path) {
    root = new CollectionBranchItem(path, this);
    root.create();
    root.createLater();
  }

  public Anything getItem(String UUID){
    return items.get(UUID);
  }
}
