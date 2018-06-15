package com.morchul.connections;

import com.badlogic.gdx.net.Socket;
import com.morchul.PaPHelper;
import com.morchul.connections.message.MessageModelCreator;
import com.morchul.connections.sockethandler.ServerReader;
import com.morchul.connections.sockethandler.ServerWriter;
import com.morchul.handler.MethodHandler;
import com.morchul.message.MessageModel;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import static com.morchul.helper.json.ClientJSONConverter.simpleStaticConverter;

public class SimpleServer implements Server {

  private ServerReader reader;
  private ServerWriter writer;
  private MethodHandler handler;
  private Socket serverSocket;
  private Logger log = LoggerFactory.getLogger(PaPHelper.class);


  public SimpleServer(Socket socket) {
    serverSocket = socket;
    reader = new ServerReader(new BufferedReader(new InputStreamReader(serverSocket.getInputStream())), this);
    writer = new ServerWriter(new PrintWriter(serverSocket.getOutputStream()), this);
    handler = new MethodHandler(this);
    new StaticServerInterface(this);
  }

  @Override
  public void receiveMessage(String message) {
    JSONObject json = new JSONObject(message);
    log.info("RECEIVE: " + message);
    handler.handle(simpleStaticConverter.toMessageModel(json));
  }

  @Override
  public void sendMessage(MessageModel message) {
    writer.sendMessage(message);
  }

  @Override
  public void disconnect() {
    serverSocket.dispose();
  }

  @Override
  public String read(){
    try {
      return reader.read();
    }catch (IOException exception){
      exception.printStackTrace();
      return "";
    }
  }

  @Override
  public void listen() { reader.listen(); }

  @Override
  public void stopListen() {
    reader.stopListen();
    //sendMessage(MessageModelCreator.createCallBackMessage());
  }

}
