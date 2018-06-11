package com.morchul.collection.item;

import com.morchul.PaPHelper;
import com.morchul.card.Card;
import com.morchul.collection.Collection;
import com.morchul.collection.FileToModelConverter;
import com.morchul.handler.CardUtils;
import com.morchul.inventory.ClientInventoryItem;
import com.morchul.model.Type;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.abstractmodels.Objects;
import com.morchul.model.models.Creatures;
import com.morchul.model.models.Skill;
import com.morchul.model.models.Status;
import com.morchul.ui.dragdrop.interfaces.DropTarget;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class CollectionLeafItem implements CollectionItem {

  private String path;
  private Anything item;
  private JSONObject json;
  private Collection collection;
  private Logger log = LoggerFactory.getLogger(PaPHelper.class);


  public CollectionLeafItem(String path, Collection collection) {
    this.collection = collection;
    this.path = path;
    json = loadCard();
  }

  private JSONObject loadCard(){
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
      return new JSONObject((String) in.readObject());
    }catch (IOException | ClassNotFoundException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<CollectionItem> getChildren() {
    log.error("Item has no children!!");
    return null;
  }

  @Override
  public boolean isBranch() {
    return false;
  }

  @Override
  public boolean isLeaf() {
    return true;
  }


    @Override
    public Card getCard() {
        return CardUtils.getCard(item);
    }

    @Override
  public Anything getItem() {
    return item;
  }

  @Override
  public void create() {
    Anything item = FileToModelConverter.create(json, path.substring(0,path.lastIndexOf('\\')+1));
    if(item != null){
      collection.items.put(item.getUUID(), item);
      this.item = item;
    }
  }

    @Override
    public String getName() {
        return item.getName();
    }

    @Override
  public void createLater() {
    if(item.getType().isType(Type.CREATURES) && item instanceof Creatures){
        for(String uuid : FileToModelConverter.getInventoryUUIDList(json)){
            Anything any = collection.getItem(uuid);
            if(any instanceof Objects)
                ((Creatures)item).getInventory().addItemToInventory(new ClientInventoryItem((Objects) any));
        }
        for(String uuid : FileToModelConverter.getSkillUUIDList(json)){
            Anything skill = collection.getItem(uuid);
            if(skill != null && skill instanceof Skill){
                ((Creatures)item).getSkills().add((Skill)skill);
            } else{
                log.error("ERROR skill is not instance of SKILL!!");
            }
        }

        for(String uuid : FileToModelConverter.getStatusUUIDList(json)){
            Anything status = collection.getItem(uuid);
            if(status != null && status instanceof Status){
                ((Creatures)item).getStatus().add((Status)status);
            } else{
                log.error("ERROR status is not instance of STATUS!!");
            }
        }
    }
  }

    @Override
    public void dragAction() { }

    @Override
    public DropTarget.DropActionType getDropType(DropTarget object) {
        return DropTarget.DropActionType.MOVE_NEW;
    }
}
