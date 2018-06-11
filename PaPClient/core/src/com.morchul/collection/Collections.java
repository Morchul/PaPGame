package com.morchul.collection;

import com.morchul.PaPHelper;
import com.morchul.model.abstractmodels.Anything;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Collections {

  private List<Collection> collections = new ArrayList<>();
  private Logger log = LoggerFactory.getLogger(PaPHelper.class);


  public Collections(String path) {
    load(path);
  }

  private void load(String path){
    log.info("Load Collections from " + path);
    File file = new File(path);
    File[] files = file.listFiles();
    if(files != null){
      log.info("Found " + files.length + " collections");
      for(File f: files){
        collections.add(new Collection(f.getPath()));
      }
    }
  }

  public Anything getItem(String uuid){
      for(Collection c: collections){
          Anything a = c.getItem(uuid);
          if(a != null)
              return a;
      }
      return null;
  }

  public boolean isEmpty(){
    return collections.isEmpty();
  }

  public List<Collection> getCollections(){
    return collections;
  }
}
