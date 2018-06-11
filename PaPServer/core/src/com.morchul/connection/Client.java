package com.morchul.connection;

import com.badlogic.gdx.net.Socket;
import com.morchul.Self;
import com.morchul.message.MessageModel;
import com.morchul.model.player.User;

public interface Client extends Runnable{
  void receiveMessage(String message);
  void sendMessage(MessageModel message);
  void disconnect();
  Self getSelf();
  void login(User user);
  void logout();
  Socket getSocket();
  int getID();
}
