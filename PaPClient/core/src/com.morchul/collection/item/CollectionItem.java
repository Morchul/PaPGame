package com.morchul.collection.item;

import com.morchul.model.abstractmodels.Anything;
import com.morchul.ui.dragdrop.interfaces.DragSource;

import java.util.List;

public interface CollectionItem extends DragSource {
  List<CollectionItem> getChildren();
  boolean isBranch();
  boolean isLeaf();
  Anything getItem();
  void create();
  String getName();
  void createLater();
}
