package edu.austral.dissis.starships.player;

import edu.austral.dissis.starships.controller.ShipController;
import edu.austral.dissis.starships.model.Ship;

import java.util.Map;

public class Player{
    private final Map<String, String> playerConfiguration;
    private int currentLives;
    private int score;
    private final int spaceshipNumber;

    public Player(Map<String, String> playerConfiguration, int currentLives, int score) {
        this.playerConfiguration = playerConfiguration;
        this.currentLives = currentLives;
        this.score = score;
        this.spaceshipNumber = Integer.parseInt(playerConfiguration.get("spaceshipNumber"));
    }

    public Player(Map<String, String> playerConfiguration) {
        this(playerConfiguration, Integer.parseInt(playerConfiguration.get("startingLives")), 0);
    }


    public String getMoveForwardKey() {
        return playerConfiguration.get("moveForwardKey");
    }

    public String getMoveBackwardKey() {return playerConfiguration.get("moveBackwardKey");}

    public String getRotateRightKey() {
        return playerConfiguration.get("rotateRightKey");
    }

    public String getRotateLeftKey() {
        return playerConfiguration.get("rotateLeftKey");
    }

    public String getShootKey() {
        return playerConfiguration.get("shootKey");
    }

}
