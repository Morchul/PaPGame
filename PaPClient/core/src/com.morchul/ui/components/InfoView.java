package com.morchul.ui.components;

import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class InfoView {

    private List<String> infoList;
    private static final int INFO_LIST_MAX = 3;

    public InfoView(Skin skin) {
        infoList = new List<>(skin);
    }

    public void addInfo(String s){
        infoList.getItems().add(s);
        if(infoList.getItems().size > INFO_LIST_MAX){
            infoList.getItems().removeIndex(0);
        }
    }

    public List<String> getInfoList(){return infoList;}
}
