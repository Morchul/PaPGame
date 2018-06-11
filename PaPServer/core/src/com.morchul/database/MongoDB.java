package com.morchul.database;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.morchul.settings.Settings;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public class MongoDB implements Database {

  private Logger log = LoggerFactory.getLogger(MongoDB.class);


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
    BasicDBObject newDocument = new BasicDBObject();
    newDocument.append("$set", new BasicDBObject().append("login", status));

    BasicDBObject searchQuery = new BasicDBObject().append("username", username);

    collection.updateOne(searchQuery, newDocument);
  }

  @Override
  public boolean login(String username, String password){
    log.info("Try to Login with USERNAME: " + username + " and PASSWORD: " + password);
    collection = database.getCollection(Settings.getCollectionName());
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
}
