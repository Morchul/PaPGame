package com.morchul.ui.components.inspectionview;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.morchul.PaPHelper;
import com.morchul.model.abstractmodels.Creatures;
import com.morchul.ui.dragdrop.interfaces.DragSource;
import com.morchul.ui.dragdrop.interfaces.DropTarget;
import com.morchul.ui.dragdrop.interfaces.MainDragAndDropAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InspectionView implements DropTarget {

    private Table table;
    private Creatures creatures;
    private Skin skin;
    private Logger log = LoggerFactory.getLogger(PaPHelper.class);


    public InspectionView(Skin skin) {
        this.skin = skin;
        table = new Table(skin);
        table.setDebug(true);
        MainDragAndDropAdmin.addTarget(table, this);
    }

    public void update(){
        table.clear();

        InspectionViewInventory inventoryView = new InspectionViewInventory(creatures, skin);
        InspectionViewStatus statusView = new InspectionViewStatus(creatures, skin);
        InspectionViewSkill skillView = new InspectionViewSkill(creatures, skin);
        InspectionViewValues valuesView = new InspectionViewValues(creatures, skin);

        table.add(inventoryView.getTable()).fillY().width(200);
        table.add(statusView.getTable()).fillY().width(200);
        table.add(skillView.getTable()).fillY().width(200);
        //TODO table.add(valuesView.getTable()).fillY().width(200);
    }

    public Table getTable() {
        return table;
    }

    @Override
    public void dropAction(DropActionType type, DragSource object) {
        if(type != DropActionType.NONE){
            if((object.getItem()) instanceof Creatures){
                creatures = (Creatures) object.getItem();
                creatures.addListener(this::update);
                update();

            } else {
                log.info("Can only inspect Object of Type Creatures");
            }
        }
    }
}
