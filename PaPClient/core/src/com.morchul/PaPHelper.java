package com.morchul;

import com.badlogic.gdx.Game;
import com.morchul.connections.Server;
import com.morchul.connections.ServerHandler;
import com.morchul.ui.ScreenLoader;

public class PaPHelper extends Game {

	@Override
	public void create() {
	    ScreenLoader loader = new ScreenLoader(this);
        Server connection = ServerHandler.connectToServer();
        ScreenLoader.changeScreen(ScreenLoader.Screens.LOGIN);
        if(connection == null)
	        ScreenLoader.setStatus("Can't connect to Server, please try it again later");
    }
}
