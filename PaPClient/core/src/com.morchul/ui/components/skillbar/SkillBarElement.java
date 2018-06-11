package com.morchul.ui.components.skillbar;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.morchul.card.Card;
import com.morchul.handler.CardUtils;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.models.Skill;
import com.morchul.ui.dragdrop.interfaces.DragSource;
import com.morchul.ui.dragdrop.interfaces.DropTarget;
import com.morchul.ui.dragdrop.interfaces.MainDragAndDropAdmin;

import static com.morchul.ui.StaticUIValues.SKILL_HEIGHT;
import static com.morchul.ui.StaticUIValues.SKILL_IMAGE_SIZE;
import static com.morchul.ui.StaticUIValues.SKILL_WIDTH;

public class SkillBarElement implements DragSource {

    private Table table;
    private Skill s;

    public SkillBarElement(Skill s, Skin skin) {
        this.s = s;

        table = new Table(skin);
        table.setDebug(false);
        table.setHeight(SKILL_HEIGHT);
        table.setWidth(SKILL_WIDTH);

        table.add(s.getName());
        table.row();
        table.add(new Image(new Texture(s.getImagePath()))).width(SKILL_IMAGE_SIZE).height(SKILL_IMAGE_SIZE);
        table.row();
        table.add(s.getDescription());

        MainDragAndDropAdmin.addSource(table, this, s.getName());
    }

    public Table getSkillElementView(){
        return table;
    }

    public Skill getSkill() {
        return s;
    }

    @Override
    public void dragAction() { }

    @Override
    public Anything getItem() {
        return s;
    }

    @Override
    public Card getCard() {
        return CardUtils.getCard(getItem());
    }

    @Override
    public DropTarget.DropActionType getDropType(DropTarget object) {
        return DropTarget.DropActionType.CARD;
    }
}
