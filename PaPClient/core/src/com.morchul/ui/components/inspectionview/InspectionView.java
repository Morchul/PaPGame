package com.morchul.ui.components.inspectionview;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.morchul.PaPHelper;
import com.morchul.model.models.Creatures;
import com.morchul.ui.dragdrop.interfaces.DragSource;
import com.morchul.ui.dragdrop.interfaces.DropTarget;
import com.morchul.ui.dragdrop.interfaces.MainDragAndDropAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InspectionView implements DropTarget {

    private Table table;
    private Creatures creatures;
    private Logger log = LoggerFactory.getLogger(PaPHelper.class);


    public InspectionView(Skin skin) {
        table = new Table(skin);

        table.setDebug(true);
        table.setFillParent(true);

        MainDragAndDropAdmin.addTarget(table, this);
    }

    public void update(){
        table.clear();

    }

    public Table getTable() {
        return table;
    }

    @Override
    public void dropAction(DropActionType type, DragSource object) {
        if(type != DropActionType.NONE){
            if(object.getItem() instanceof Creatures){
                creatures = (Creatures) object.getItem();
                update();
                creatures.addListener(this::update);

            } else {
                log.info("Can only inspect Object of Type Creatures");
            }
        }
    }
}
