package com.morchul.ui.custom;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class CustomNumberSpinnerGroup {

    private int points;
    private Label pointsL;

    public CustomNumberSpinnerGroup(int points, Skin skin) {
        this.points = points;
        pointsL = new Label(points+"", skin);
    }

    public boolean removePoint(){
        if(points > 0) {
            update(points - 1);
            return true;
        }
        return false;
    }

    public boolean addPoint(){
        update(points + 1);
        return true;
    }

    public void addPoints(int i){update(points + i);}

    private void update(int points){
        this.points = points;
        pointsL.setText(points+"");
    }

    public int getPoints() {
        return points;
    }

    public Label getView() {
        return pointsL;
    }
}
