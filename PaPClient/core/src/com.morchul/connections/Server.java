package com.morchul.connections;

import com.morchul.message.MessageModel;

public interface Server {
  void receiveMessage(String message);
  void sendMessage(MessageModel message);
  void disconnect();
  String read();
  void listen();
  void stopListen();
}
