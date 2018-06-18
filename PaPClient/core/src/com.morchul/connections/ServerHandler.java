package com.morchul.connections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.morchul.PaPHelper;
import com.morchul.settings.ClientSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.morchul.connections.ConnectionConstants.*;

public class ServerHandler {

  private static Logger log = LoggerFactory.getLogger(PaPHelper.class);

  private ServerHandler() { }

  public static Server connectToServer(){
    Socket socket;
    if((socket = connect()) != null)
      return new SimpleServer(socket);
    else
      return null;
  }

  private static Socket connect(){
    SocketHints hints;
    hints = new SocketHints();
    hints.connectTimeout = CONNECT_TIMEOUT;
    hints.socketTimeout = SOCKET_TIMEOUT;
    Socket socket = null;
    try {
      socket = Gdx.net.newClientSocket(PROTOCOL, ClientSettings.getHost(), ClientSettings.getPort(), hints);

    } catch (GdxRuntimeException exception){
      log.error("Can't connect to Server: " + exception);
    }
    return socket;
  }
}
