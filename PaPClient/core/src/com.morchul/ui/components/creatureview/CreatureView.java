package com.morchul.ui.components.creatureview;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.morchul.Self;
import com.morchul.game.Game;
import com.morchul.model.abstractmodels.Creatures;
import com.morchul.model.player.User;
import javafx.collections.ListChangeListener;

public class CreatureView  {

    private final VerticalGroup group;
    private Game game;
    private Skin skin;

    public CreatureView(Game game, Skin skin) {
        this.game = game;
        this.skin = skin;
        group = new VerticalGroup();
        game.getPlayers().addListener((ListChangeListener<User>) c -> update());
        game.getNPCs().addListener((ListChangeListener<Creatures>) c -> update());
        update();
    }

    private void update(){
        group.clear();
        for(User u: game.getPlayers()){
            if(!Self.game.isGameMaster(u))
                group.addActor(new CreatureElement(u.getCharacter(), skin).getCreatureElement());
        }

        for(Creatures c : game.getNPCs()){
            group.addActor(new CreatureElement(c, skin).getCreatureElement());
        }
    }

    public VerticalGroup getGroup() {
        return group;
    }
}
