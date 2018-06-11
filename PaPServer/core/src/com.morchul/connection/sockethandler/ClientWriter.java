package com.morchul.connection.sockethandler;

import com.morchul.PaPServer;
import com.morchul.connection.Client;
import com.morchul.json.JSONConverter;
import com.morchul.message.MessageModel;
import org.json.JSONObject;

import java.io.PrintWriter;

public class ClientWriter {

  private PrintWriter writer;
  private Client client;
  private JSONConverter converter;

  public ClientWriter(PrintWriter writer, Client client, JSONConverter converter) {
    this.writer = writer;
    this.client = client;
    this.converter = converter;
  }

  public void sendMessage(MessageModel message){
    JSONObject JSONMessage = converter.toJSON(message);
    PaPServer.log.info("SEND message to: " + client.getID() + ":\n" + JSONMessage.toString());
    writer.println(JSONMessage);
    writer.flush();
  }
}
