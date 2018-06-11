package com.morchul.connections.sockethandler;

import com.morchul.PaPHelper;
import com.morchul.connections.Server;
import com.morchul.message.MessageModel;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;

import static com.morchul.helper.json.ClientJSONConverter.simpleStaticConverter;

public class ServerWriter {

  private PrintWriter writer;
  private Server server;
  private Logger log = LoggerFactory.getLogger(PaPHelper.class);


  public ServerWriter(PrintWriter writer, Server server) {
    this.writer = writer;
    this.server = server;
  }

  public void sendMessage(MessageModel message){
    try {
      JSONObject json = simpleStaticConverter.toJSON(message);
      log.info("SEND: " + json);
      writer.println(json);
      writer.flush();
    } catch (Exception e){
      log.error("Loos connection to server, close Game");
      System.exit(-1);
    }
  }
}
