package edu.austral.dissis.starships.config;

import edu.austral.dissis.starships.file.FileLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Config {
    public static List<Map<String, String>> getGameConfiguration() {
        FileLoader fileLoader = new FileLoader();

        List<Map<String, String>> result = new ArrayList<>();

        try {
            JSONTokener tokener = new JSONTokener(fileLoader.loadFromResources("configuration.json"));
            JSONArray configuration = new JSONArray(tokener);

            for (int i = 0; i < configuration.length(); i++) {
                JSONObject jsonObject = configuration.getJSONObject(i);
                result.add(Stream.of(new String[][] {
                        { "moveForwardKey", jsonObject.getString("moveForwardKey") },
                        { "moveBackwardKey", jsonObject.getString("moveBackwardKey") },
                        { "rotateRightKey", jsonObject.getString("rotateRightKey") },
                        { "rotateLeftKey", jsonObject.getString("rotateLeftKey") },
                        { "shootKey", jsonObject.getString("shootKey") },
                        { "startingLives", String.valueOf(jsonObject.getInt("startingLives"))},
                        { "spaceshipNumber", String.valueOf(jsonObject.getInt("spaceshipNumber"))},
                        { "spaceShipImagePath", jsonObject.getString("spaceShipImagePath")}
                }).collect(Collectors.toMap(data -> data[0], data -> data[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
            result.add(Stream.of(new String[][] {
                    { "moveForwardKey", "UP" },
                    { "moveBackwardKey", "BACK" },
                    { "rotateRightKey", "RIGHT" },
                    { "rotateLeftKey", "LEFT" },
                    { "shootKey", "SPACE" },
                    { "lives", "3" },
                    { "spaceshipNumber", "1" }
            }).collect(Collectors.toMap(data -> data[0], data -> data[1])));
        }

        return result;
    }
}
