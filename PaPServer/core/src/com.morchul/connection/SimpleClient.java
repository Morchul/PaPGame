package com.morchul.connection;

import com.badlogic.gdx.net.Socket;
import com.morchul.PaPServer;
import com.morchul.Self;
import com.morchul.connection.message.MessageModelCreator;
import com.morchul.connection.sockethandler.ClientReader;
import com.morchul.connection.sockethandler.ClientWriter;
import com.morchul.handler.MethodHandler;
import com.morchul.json.JSONConverter;
import com.morchul.json.ServerJSONArrayHelper;
import com.morchul.json.SimpleJSONConverter;
import com.morchul.message.MessageModel;
import com.morchul.model.player.User;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.SocketException;

public class SimpleClient implements Client {

  private ClientReader reader;
  private ClientWriter writer;
  private MethodHandler handler;
  private Self self;
  private int id;
  private Socket clientSocket;
  private JSONConverter converter;

  public SimpleClient(Socket socket, int id){
    PaPServer.log.info("New SimpleClient with Socket: " + socket + " and id: " + id);
    self = new Self();
    this.id = id;
    clientSocket = socket;
    converter = new SimpleJSONConverter(new ServerJSONArrayHelper());
    new MessageModelCreator(converter);
    writer = new ClientWriter(new PrintWriter(clientSocket.getOutputStream()), this, converter);
    reader = new ClientReader(new BufferedReader(new InputStreamReader(clientSocket.getInputStream())), this);
    handler = new MethodHandler(this, converter);
  }

  @Override
  public void receiveMessage(String message){
    JSONObject json = new JSONObject(message);
    handler.handle(converter.toMessageModel(json));
  }

  @Override
  public void sendMessage(MessageModel message) {
    writer.sendMessage(message);
  }

  @Override
  public void run() {

    try {
      reader.listen();
    } catch (SocketException socketException) {
      socketDisconnected();
    } catch (IOException ioException) {
      PaPServer.log.error(ioException.getMessage(), ioException);
    } finally {
      disconnect();
    }
  }

  private void socketDisconnected(){
    if(self.getGameWrapper() != null) {
      self.getGameWrapper().removeClientFromGame(this);
    }
    PaPServer.log.info("Stop listening on: " + id);
  }

  @Override
  public void disconnect(){
    PaPServer.log.info("Socket Disconnected: " + id);
    logout();
    clientSocket.dispose();
  }

  @Override
  public Self getSelf() {
    return self;
  }

  @Override
  public void login(User user) {
    self.setUser(user);
  }

  @Override
  public void logout() {
    if(self.getUser() != null)
      PaPServer.database.logout(self.getUser().getUserName());
    self.setUser(null);
  }

  @Override
  public Socket getSocket() {
    return clientSocket;
  }

  @Override
  public int getID() {
    return id;
  }

}
