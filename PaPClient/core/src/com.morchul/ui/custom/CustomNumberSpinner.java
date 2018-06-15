package com.morchul.ui.custom;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import static com.morchul.ui.StaticUIValues.CUSTOM_NUMBER_SPINNER_BUTTON_WIDTH;
import static com.morchul.ui.StaticUIValues.CUSTOM_NUMBER_SPINNER_HEIGHT;

public class CustomNumberSpinner {

    private CustomNumberSpinnerGroup group;
    private Skin skin;
    private int number;
    private int min;
    private int max;
    private int amount;

    private TextButton plus;
    private TextButton minus;
    private Label numberL;
    private HorizontalGroup view;

    public CustomNumberSpinner(CustomNumberSpinnerGroup group, Skin skin, int initialNumber, int minimum, int maximum, int a) {
        this.group = group;
        this.skin = skin;
        this.number = initialNumber;
        this.min = minimum;
        this.max = maximum;
        this.amount = a;

        view = new HorizontalGroup();
        numberL = new Label(number+"", skin);

        minus = new TextButton("<", skin);
        minus.setSize(CUSTOM_NUMBER_SPINNER_BUTTON_WIDTH, CUSTOM_NUMBER_SPINNER_HEIGHT);
        minus.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(number == 0 || number == min)
                    return ;
                if(group.addPoint())
                    update(number - amount);
            }
        });

        plus = new TextButton(">", skin);
        plus.setSize(CUSTOM_NUMBER_SPINNER_BUTTON_WIDTH, CUSTOM_NUMBER_SPINNER_HEIGHT);
        plus.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(number == max)
                    return ;
                if(group.removePoint())
                    update(number + amount);
            }
        });

        view.space(5);
        view.addActor(minus);
        view.addActor(numberL);
        view.addActor(plus);
    }

    private void update(int number){
        this.number = number;
        numberL.setText(number+"");
    }

    public void setMin(int min){
        this.min = min;
    }

    public void setMax(int max){
        this.max = max;
    }

    public HorizontalGroup getView(){
        return view;
    }

    public int getValue(){return number;}

    public void setValue(int value){
        update(value);
    }
}
