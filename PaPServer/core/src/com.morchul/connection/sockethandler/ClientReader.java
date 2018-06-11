package com.morchul.connection.sockethandler;

import com.morchul.PaPServer;
import com.morchul.connection.Client;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientReader{

  private BufferedReader reader;
  private Client client;

  public ClientReader(BufferedReader reader, Client client) {
    this.client = client;
    this.reader = reader;
  }

  public void listen() throws IOException{
    PaPServer.log.info("Start listening on: " + client.getID() + "...");
    String message;
    while ((message = reader.readLine()) != null) {
      PaPServer.log.info("RECEIVE message from: " + client.getID() + ":\n" + message);
      client.receiveMessage(message);
    }
    PaPServer.log.info("Stop listening on: " + client.getID());
  }
}
