package com.morchul.database;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.morchul.settings.Settings;
import org.bson.Document;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.morchul.json.JSONConverter.ANYTHING_NAME_KEY;

public class MongoDB implements Database {

  private Logger log = LoggerFactory.getLogger(MongoDB.class);

  private static final String PLAYER_COLLECTION = "player";
  private static final String CHARACTER_COLLECTION = "character";

  private MongoClient mongo;
  private MongoDatabase database;
  private MongoCollection<Document> collection;

  public MongoDB() {
    log.info("Open MongoDB Connection on: " + Settings.getDatabaseHost() + ":" + Settings.getDatabasePort());
    mongo = new MongoClient(Settings.getDatabaseHost(), Settings.getDatabasePort());
    database = mongo.getDatabase(Settings.getDatabaseName());
  }

  @Override
  public void logout(String username){
    updateLoginStatus(false, username);
  }

  private void updateLoginStatus(boolean status, String username){
    collection = database.getCollection(PLAYER_COLLECTION);
    BasicDBObject newDocument = new BasicDBObject();
    newDocument.append("$set", new BasicDBObject().append("login", status));

    BasicDBObject searchQuery = new BasicDBObject().append("username", username);

    collection.updateOne(searchQuery, newDocument);
  }

  @Override
  public boolean login(String username, String password){
    log.info("Try to Login with USERNAME: " + username + " and PASSWORD: " + password);
    collection = database.getCollection(PLAYER_COLLECTION);
    BasicDBObject query = new BasicDBObject();
    query.put("username", username);
    query.put("password", password);
    query.put("login", false);

    FindIterable<Document> iterDoc = collection.find(query);

    Iterator it = iterDoc.iterator();
    while(it.hasNext()){
      updateLoginStatus(true, username);
      return true;
    }
    return false;
  }

  @Override
  public void createCharacter(JSONObject creatures, String username) {
    log.info("Create character: " + creatures.getString(ANYTHING_NAME_KEY) + " / user: " + username);
    collection = database.getCollection(CHARACTER_COLLECTION);

    Document document = Document.parse(creatures.toString());
    document.append("username", username);
    collection.insertOne(document);
  }

  @Override
  public List<JSONObject> loadCharacters(String username) {
    collection = database.getCollection(CHARACTER_COLLECTION);
    BasicDBObject query = new BasicDBObject();
    query.put("username", username);

    List<JSONObject> characters = new ArrayList<>();
    MongoCursor<Document> cursor = collection.find(query).iterator();

    while(cursor.hasNext()){
      characters.add(new JSONObject(cursor.next().toJson()));
    }
    return characters;
  }

  @Override
  public void saveCharacter(JSONObject creatures, String username) {
    collection = database.getCollection(CHARACTER_COLLECTION);
    Document document = Document.parse(creatures.toString());
    Document newDocument = new Document().append("$set", document);
    Document searchQuery = new Document().append("username", username);
    collection.updateOne(searchQuery, newDocument);
  }
}
