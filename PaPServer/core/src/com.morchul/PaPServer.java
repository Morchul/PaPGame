package com.morchul;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.morchul.connection.Client;
import com.morchul.connection.SimpleClient;
import com.morchul.database.DatabaseFactory;
import com.morchul.database.Database;
import com.morchul.settings.ServerSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.morchul.connection.Constants.*;

public class PaPServer extends ApplicationAdapter {

    private boolean stopped = false;
    public static Logger log = LoggerFactory.getLogger(PaPServer.class);
    public static Database database;
    private int ClientID = 100;

    @Override
    public void create () {
        database = DatabaseFactory.createDatabase();

        log.info("Server use Database: " + database.getClass().getSimpleName());
        log.info("Starting Server...");
        //new GameManager();
        new Thread(() -> {
            try {
                ServerSocketHints hints = new ServerSocketHints();
                hints.acceptTimeout = ACCEPT_TIMEOUT;
                ServerSocket serverSocket = Gdx.net.newServerSocket(PROTOCOL, ServerSettings.getPort(), hints);
                log.info("Server running on port: " + ServerSettings.getPort());
                SocketHints sHints = new SocketHints();
                sHints.connectTimeout = CONNECT_TIMEOUT;
                sHints.socketTimeout = SOCKET_TIMEOUT;
                while (!stopped) {
                    log.info("Waiting for connection...");
                    Socket clientSocket = serverSocket.accept(sHints);
                    log.info("Found connection!");

                    Client listener = new SimpleClient(clientSocket, ++ClientID);
                    new Thread(listener).start();
                }
                log.info("Server stopped");
            } catch (Exception e){
                log.error("Fatal error occurs! Server shutdown...");
            }
        }).start();
    }

    public void stopServer(){ stopped = true; }

    @Override
    public void render () { }

    @Override
    public void dispose () { }
}
