package com.morchul.ui.components.inspectionview;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.morchul.card.Card;
import com.morchul.connections.StaticServerInterface;
import com.morchul.connections.message.MessageModelCreator;
import com.morchul.handler.CardUtils;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.abstractmodels.Creatures;
import com.morchul.model.models.Skill;
import com.morchul.ui.dragdrop.interfaces.DragSource;
import com.morchul.ui.dragdrop.interfaces.DropTarget;
import com.morchul.ui.dragdrop.interfaces.MainDragAndDropAdmin;

public class InspectionViewSkillItem implements DragSource {

    private Skill skill;
    private HorizontalGroup group;
    private Creatures creatures;

    public InspectionViewSkillItem(Creatures creatures, Skill skill, Skin skin) {
        this.skill = skill;
        this.creatures = creatures;
        this.group = new HorizontalGroup();
        group.space(4);

        final TextButton remove = new TextButton("-", skin);
        remove.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                StaticServerInterface.sendMessage(MessageModelCreator.createRemoveSkill(skill, creatures));
            }
        });
        group.addActor(new Label(skill.getName(), skin));
        group.addActor(remove);

        MainDragAndDropAdmin.addSource(group, this, skill.getName());
    }

    public HorizontalGroup getView() {
        return group;
    }

    @Override
    public void dragAction() { }

    @Override
    public Anything getItem() {
        return skill;
    }

    @Override
    public Card getCard() {
        return CardUtils.getCard(skill);
    }

    @Override
    public DropTarget.DropActionType getDropType(DropTarget object) {
        return DropTarget.DropActionType.MOVE;
    }
}
