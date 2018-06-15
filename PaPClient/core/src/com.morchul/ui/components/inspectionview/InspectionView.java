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

import static com.morchul.ui.StaticUIValues.*;

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

    public void clear(){
        table.clear();
    }

    public void update(){
        clear();

        InspectionViewMainInformation mainInformation = new InspectionViewMainInformation(creatures, skin);
        InspectionViewInventory inventoryView = new InspectionViewInventory(creatures, skin);
        InspectionViewStatus statusView = new InspectionViewStatus(creatures, skin);
        InspectionViewSkill skillView = new InspectionViewSkill(creatures, skin);
        InspectionViewValues valuesView = new InspectionViewValues(creatures, skin);
        InspectionViewActions actionsView = new InspectionViewActions(creatures, skin, this);

        table.row().height(INSPECTION_VIEW_TOP_LINE_HEIGHT);
        table.add(mainInformation.getTable()).colspan(4);
        table.row().height(BOTTOM_SKILL_BAR_HEIGHT - INSPECTION_VIEW_TOP_LINE_HEIGHT);
        table.add(inventoryView.getTable()).fillY().width(INSPECTION_VIEW_COMPONENT_WIDTH);
        table.add(statusView.getTable()).fillY().width(INSPECTION_VIEW_COMPONENT_WIDTH);
        table.add(skillView.getTable()).fillY().width(INSPECTION_VIEW_COMPONENT_WIDTH);
        table.add(valuesView.getTable()).fillY().width(LONG_INSPECTION_VIEW_COMPONENT_WIDTH);
        table.add(actionsView.getTable()).fillY().width(INSPECTION_VIEW_COMPONENT_WIDTH);
    }

    public Table getTable() {
        return table;
    }

    @Override
    public void dropAction(DropActionType type, DragSource object) {
        if(type != DropActionType.NONE){
            if((object.getItem()) instanceof Creatures){
                creatures = (Creatures) object.getItem();
                update();

            } else {
                log.info("Can only inspect Object of Type Creatures");
            }
        }
    }
}
