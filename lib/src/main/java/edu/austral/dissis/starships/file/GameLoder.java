package edu.austral.dissis.starships.file;

import edu.austral.dissis.starships.config.Config;
import edu.austral.dissis.starships.controller.GameObjectController;
import edu.austral.dissis.starships.dataStructure.*;
import edu.austral.dissis.starships.model.Asteroid;
import edu.austral.dissis.starships.model.Bullet;
import edu.austral.dissis.starships.model.Ship;
import edu.austral.dissis.starships.player.Player;
import edu.austral.dissis.starships.view.AsteroidView;
import edu.austral.dissis.starships.view.BulletView;
import edu.austral.dissis.starships.view.ShipView;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameLoder {

    ArrayList<Player> players;

    public GameLoder(){
        players = new ArrayList<>();
    }

    public void loadNewGame(GameObjectController gameObjectController) throws IOException {
        List<Map<String, String>> gameConfiguration = Config.getGameConfiguration();
        for (int i = 0; i < gameConfiguration.size(); i++) {
            Map<String, String> playerConfiguration = gameConfiguration.get(i);
            Player player = new Player(playerConfiguration);
            players.add(player);
            Ship shipModel = new Ship(new Vector2(900 - i*200,500),new Vector2(0,-1),200,new DimensionTuple(85,85),player);
            ShipView shipView = new ShipView(shipModel.getPosition().getX(),shipModel.getPosition().getY(),shipModel.getDirection().getAngle()*180/Math.PI + 90,
                    shipModel.getDimension().getWidth(),shipModel.getDimension().getHeight(),gameConfiguration.get(i).get("spaceShipImagePath"));
            ShipModelViewTuple shipModelViewTuple = new ShipModelViewTuple(shipModel,shipView);
            gameObjectController.add(shipModelViewTuple);
        }
    }

    public void loadSavedGame(GameObjectController gameObjectController) throws IOException {
        FileLoader fileLoader = new FileLoader();
        JSONTokener jsonTokener = new JSONTokener(fileLoader.loadFromResources("savedGame.json"));
        JSONArray jsonArray = new JSONArray(jsonTokener);
        List<JSONObject> playersList = new ArrayList<>();
        List<JSONObject> gameObjectsList = new ArrayList<>();
        jsonArray.forEach(jsonObject -> {
            if( ((JSONObject) jsonObject).get("modelType").equals("Player")){
                playersList.add((JSONObject) jsonObject);
            }else {
                gameObjectsList.add((JSONObject) jsonObject);
            }
        });

        for (int i = 0; i < playersList.size(); i++) {
            Map<String, String> playerConfiguration = new HashMap<>();
            playerConfiguration.put("moveForwardKey", playersList.get(i).get("moveForwardKey").toString());
            playerConfiguration.put("moveBackwardKey", playersList.get(i).get("moveBackwardKey").toString());
            playerConfiguration.put("rotateRightKey", playersList.get(i).get("rotateRightKey").toString());
            playerConfiguration.put("rotateLeftKey", playersList.get(i).get("rotateLeftKey").toString());
            playerConfiguration.put("shootKey", playersList.get(i).get("shootKey").toString());
            Player player = new Player(playerConfiguration,
                    playersList.get(i).getInt("playerCurrentLives"),
                    playersList.get(i).getInt("playerScore"),
                    playersList.get(i).getString("spaceShipImagePath"),
                    playersList.get(i).getInt("playerId"));
            players.add(player);
        }

        for (int i = 0; i < gameObjectsList.size(); i++) {
            if (gameObjectsList.get(i).get("modelType").equals("Ship")) {
                Ship ship = new Ship(
                        new Vector2(gameObjectsList.get(i).getDouble("positionX"), gameObjectsList.get(i).getDouble("positionY")),
                        new Vector2(gameObjectsList.get(i).getDouble("directionX"), gameObjectsList.get(i).getDouble("directionY")),
                        gameObjectsList.get(i).getFloat("speed"),
                        new DimensionTuple(gameObjectsList.get(i).getInt("width"), gameObjectsList.get(i).getInt("height")),
                        players.get(gameObjectsList.get(i).getInt("playerId") - 1)
                );
                ShipView shipView = new ShipView(ship.getPosition().getX(),ship.getPosition().getY(),
                        ship.getDirection().getAngle(),ship.getDimension().getWidth(),ship.getDimension().getHeight(),ship.getPlayer().getSpaceShipImagePath());
                ShipModelViewTuple shipModelViewTuple = new ShipModelViewTuple(ship,shipView);
                gameObjectController.add(shipModelViewTuple);
            }
            if (gameObjectsList.get(i).get("modelType").equals("Bullet")){
                Bullet bullet = new Bullet(
                        new Vector2(gameObjectsList.get(i).getDouble("positionX"), gameObjectsList.get(i).getDouble("positionY")),
                        new Vector2(gameObjectsList.get(i).getDouble("directionX"), gameObjectsList.get(i).getDouble("directionY")),
                        gameObjectsList.get(i).getFloat("speed"),
                        new DimensionTuple(gameObjectsList.get(i).getInt("width"), gameObjectsList.get(i).getInt("height")),
                        players.get(gameObjectsList.get(i).getInt("playerId") - 1));

                BulletView bulletView = new BulletView(bullet.getPosition().getX(),bullet.getPosition().getY(),
                        bullet.getDirection().getAngle(),bullet.getDimension().getWidth(),bullet.getDimension().getHeight());

                BulletModelViewTuple bulletModelViewTuple = new BulletModelViewTuple(bullet,bulletView);
                gameObjectController.add(bulletModelViewTuple);
            }
            if (gameObjectsList.get(i).get("modelType").equals("Asteroid")){
                Asteroid asteroid = new Asteroid(
                        new Vector2(gameObjectsList.get(i).getDouble("positionX"), gameObjectsList.get(i).getDouble("positionY")),
                        new Vector2(gameObjectsList.get(i).getDouble("directionX"), gameObjectsList.get(i).getDouble("directionY")),
                        gameObjectsList.get(i).getFloat("speed"),
                        new DimensionTuple(gameObjectsList.get(i).getInt("width"), gameObjectsList.get(i).getInt("height")));
                AsteroidView asteroidView = new AsteroidView(asteroid.getPosition().getX(),asteroid.getPosition().getY(),
                        asteroid.getDirection().getAngle(),asteroid.getDimension().getWidth(),asteroid.getDimension().getHeight());
                AsteroidModelViewTuple asteroidModelViewTuple = new AsteroidModelViewTuple(asteroid,asteroidView);
                gameObjectController.add(asteroidModelViewTuple);
            }
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}

