package com.morchul.ui.components.menubar;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class MenuBarItem {

    private TextButton button;

    public MenuBarItem(String text, Skin skin) {
        button = new TextButton(text, skin);
        //button.setWidth(50); TODO
        //button.setHeight(50);
    }

    public void setImage(Drawable drawable){ button.setBackground(drawable); }

    public void setColor(float r, float g, float b, float a){ button.setColor(r, g, b, a); }

    public void addAction(ChangeListener listener){ button.addListener(listener); }

    public TextButton getButton() { return button; }
}
