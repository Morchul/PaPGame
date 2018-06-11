package com.morchul.connections;

import com.morchul.PaPHelper;
import com.morchul.connections.message.MessageModelCreator;
import com.morchul.message.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StaticServerInterface {

  private static Logger log = LoggerFactory.getLogger(PaPHelper.class);

  private static Server connection;
  public StaticServerInterface(Server con) {
    connection = con;
  }

  public static void stopListen() {
    if(isConnectionNotNull())
      connection.stopListen();
  }

  public static String read(){
    if(isConnectionNotNull())
      return connection.read();
    return "";
  }

  public static void listen(){
    if (isConnectionNotNull())
      connection.listen();
  }

  public static void sendMessage(MessageModel message){
    if(isConnectionNotNull())
      connection.sendMessage(message);
  }

  public static void disconnect(){
    if(isConnectionNotNull())
      connection.disconnect();
  }

  private static boolean isConnectionNotNull(){
    if(connection != null)
      return true;
    else
      log.error("Connection is not set in StaticServerInterface");
    return false;
  }
}
