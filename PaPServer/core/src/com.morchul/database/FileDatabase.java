package com.morchul.database;

import com.morchul.PaPServer;
import com.morchul.settings.ServerSettings;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.morchul.json.JSONConverter.ANYTHING_UUID_KEY;

public class FileDatabase implements Database {

    private File database;
    private List<FileEntry> columns;

    public FileDatabase(){
        database = new File(ServerSettings.getDatabaseFilePath() + ServerSettings.getDatabaseFileName());
        columns = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(database))) {
            String s;
            while((s =  reader.readLine()) != null){
                String[] split = s.split("::");
                columns.add(new FileEntry(split[1], split[0]));
            }
        } catch (IOException e){
            PaPServer.log.info("File "+database.getName()+" is Empty");
        }
    }

    @Override
    public void logout(String username) { }

    @Override
    public boolean login(String username, String password) { return true; }

    @Override
    public void createCharacter(JSONObject creatures, String username) {
        columns.add(new FileEntry(creatures, username));
        writeFile();
    }

    @Override
    public List<JSONObject> loadCharacters(String username) {
        List<JSONObject> characters = new ArrayList<>();
        for(FileEntry entry: columns){
            if(entry.username.equals(username))
                characters.add(entry.character);
        }
        return characters;
    }

    @Override
    public void saveCharacter(JSONObject creatures, String username) {
        for(FileEntry entry : columns){
            if(entry.username.equals(username) &&
                    entry.character.getString(ANYTHING_UUID_KEY).equals(creatures.getString(ANYTHING_UUID_KEY))){
                entry.character = creatures;
                break;
            }
        }
        writeFile();
    }

    private void writeFile(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(database))){
            for(FileEntry entry : columns){
                writer.write(entry.username + "::" + entry.character);
                writer.newLine();
            }
            writer.flush();
            PaPServer.log.info("Write in File: " + database.getAbsolutePath());
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    private class FileEntry{
        String username;
        JSONObject character;

        public FileEntry(JSONObject json, String username){
            this.username = username;
            this.character = json;
        }

        public FileEntry(String creatures, String username){
            this(new JSONObject(creatures), username);
        }
    }
}
