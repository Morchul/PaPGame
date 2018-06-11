package com.morchul.connections.sockethandler;

import com.morchul.PaPHelper;
import com.morchul.connections.Server;
import com.morchul.connections.message.MessageModelCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;

import static com.morchul.helper.json.ClientJSONConverter.simpleStaticConverter;

public class ServerReader {

  private BufferedReader reader;
  private Server server;
  private boolean listen = false;
  private Logger log = LoggerFactory.getLogger(PaPHelper.class);


  public ServerReader(BufferedReader reader, Server server) {
    this.reader = reader;
    this.server = server;
  }

  public String read() throws IOException {
    try {
      String debug = reader.readLine();
      log.info("RECEIVE: " + debug);
      return debug;
    } catch (SocketException e){
      log.error("Loos connection to Server, close Game...");
    } catch (Exception e){
      log.error("Error by read from Server, close Game...");
    }
    System.exit(-1);
    return null;
  }

  public void stopListen(){
    listen = false;
  }

  public void listen() {
    listen = true;
    new Thread(() -> {
      try {
        String message;
        log.info("START LISTEN");
        while (listen && (message = reader.readLine()) != null) {
          server.receiveMessage(message);
        }
      } catch (SocketException socketException){
        server.receiveMessage(simpleStaticConverter.toJSON(MessageModelCreator.createErrorMessage("Loos Connection to Server")).toString());
      } catch (IOException ioException){
        ioException.printStackTrace();
      }
      log.info("STOP LISTEN");
    }).start();
  }
}
