package com.morchul.ui.components.inspectionview;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.morchul.model.abstractmodels.Creatures;

import static com.morchul.ui.StaticUIValues.*;

public class InspectionViewMainInformation {

    private Table table;

    public InspectionViewMainInformation(Creatures creatures, Skin skin) {
        table = new Table(skin);
        table.setDebug(false);




        table.row().height(INSPECTION_VIEW_TOP_LINE_HEIGHT);
        table.add("Inventory").width(INSPECTION_VIEW_COMPONENT_WIDTH);
        table.add("Status").width(INSPECTION_VIEW_COMPONENT_WIDTH);
        table.add("Skill").width(INSPECTION_VIEW_COMPONENT_WIDTH);
        table.add("Name: " + creatures.getName()).width(LONG_INSPECTION_VIEW_COMPONENT_WIDTH);
    }

    public Table getTable(){
        return table;
    }
}
