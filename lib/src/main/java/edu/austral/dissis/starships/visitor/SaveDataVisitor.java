package edu.austral.dissis.starships.visitor;

import edu.austral.dissis.starships.dataStructure.AsteroidModelViewTuple;
import edu.austral.dissis.starships.dataStructure.BulletModelViewTuple;
import edu.austral.dissis.starships.dataStructure.ShipModelViewTuple;
import edu.austral.dissis.starships.model.Asteroid;
import edu.austral.dissis.starships.model.Bullet;
import edu.austral.dissis.starships.model.Ship;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;


public class SaveDataVisitor implements Visitor{

    private JSONArray data = new JSONArray();

    @Override
    public void visit(ShipModelViewTuple shipModelViewTuple) {
        JSONObject shipJsonObject = new JSONObject();
        shipJsonObject.put("modelType", shipModelViewTuple.getModel().getClass().getSimpleName());
        shipJsonObject.put("positionX", shipModelViewTuple.getModel().getPosition().getX());
        shipJsonObject.put("positionY", shipModelViewTuple.getModel().getPosition().getY());
        shipJsonObject.put("directionX", shipModelViewTuple.getModel().getDirection().getX());
        shipJsonObject.put("directionY", shipModelViewTuple.getModel().getDirection().getY());
        shipJsonObject.put("speed", shipModelViewTuple.getModel().getSpeed());
        shipJsonObject.put("width", shipModelViewTuple.getModel().getDimension().getWidth());
        shipJsonObject.put("height", shipModelViewTuple.getModel().getDimension().getHeight());
        shipJsonObject.put("playerId", ((Ship) shipModelViewTuple.getModel()).getPlayer().getId());


        JSONObject playerJsonObject = new JSONObject();
        playerJsonObject.put("modelType", ((Ship) shipModelViewTuple.getModel()).getPlayer().getClass().getSimpleName());
        playerJsonObject.put("moveForwardKey", ((Ship) shipModelViewTuple.getModel()).getPlayer().getMoveForwardKey());
        playerJsonObject.put("moveBackwardKey", ((Ship) shipModelViewTuple.getModel()).getPlayer().getMoveBackwardKey());
        playerJsonObject.put("rotateRightKey", ((Ship) shipModelViewTuple.getModel()).getPlayer().getRotateRightKey());
        playerJsonObject.put("rotateLeftKey", ((Ship) shipModelViewTuple.getModel()).getPlayer().getRotateLeftKey());
        playerJsonObject.put("shootKey", ((Ship) shipModelViewTuple.getModel()).getPlayer().getShootKey());
        playerJsonObject.put("playerCurrentLives", ((Ship) shipModelViewTuple.getModel()).getPlayer().getCurrentLives());
        playerJsonObject.put("playerScore", ((Ship) shipModelViewTuple.getModel()).getPlayer().getScore());
        playerJsonObject.put("spaceShipImagePath", ((Ship) shipModelViewTuple.getModel()).getPlayer().getSpaceShipImagePath());
        playerJsonObject.put("playerId", ((Ship) shipModelViewTuple.getModel()).getPlayer().getId());

        data.put(shipJsonObject);
        data.put(playerJsonObject);
    }

    @Override
    public void visit(AsteroidModelViewTuple asteroidModelViewTuple) {
        JSONObject asteroidJsonObject = new JSONObject();
        asteroidJsonObject.put("modelType", asteroidModelViewTuple.getModel().getClass().getSimpleName());
        asteroidJsonObject.put("positionX", asteroidModelViewTuple.getModel().getPosition().getX());
        asteroidJsonObject.put("positionY", asteroidModelViewTuple.getModel().getPosition().getY());
        asteroidJsonObject.put("directionX", asteroidModelViewTuple.getModel().getDirection().getX());
        asteroidJsonObject.put("directionY", asteroidModelViewTuple.getModel().getDirection().getY());
        asteroidJsonObject.put("speed", asteroidModelViewTuple.getModel().getSpeed());
        asteroidJsonObject.put("width", asteroidModelViewTuple.getModel().getDimension().getWidth());
        asteroidJsonObject.put("height", asteroidModelViewTuple.getModel().getDimension().getHeight());
        asteroidJsonObject.put("asteroidLife", ((Asteroid) asteroidModelViewTuple.getModel()).getLife());

        data.put(asteroidJsonObject);
    }

    @Override
    public void visit(BulletModelViewTuple bulletModelViewTuple) {
        JSONObject bulletJsonObject = new JSONObject();
        bulletJsonObject.put("modelType", bulletModelViewTuple.getModel().getClass().getSimpleName());
        bulletJsonObject.put("positionX", bulletModelViewTuple.getModel().getPosition().getX());
        bulletJsonObject.put("positionY", bulletModelViewTuple.getModel().getPosition().getY());
        bulletJsonObject.put("directionX", bulletModelViewTuple.getModel().getDirection().getX());
        bulletJsonObject.put("directionY", bulletModelViewTuple.getModel().getDirection().getY());
        bulletJsonObject.put("speed", bulletModelViewTuple.getModel().getSpeed());
        bulletJsonObject.put("width", bulletModelViewTuple.getModel().getDimension().getWidth());
        bulletJsonObject.put("height", bulletModelViewTuple.getModel().getDimension().getHeight());
        bulletJsonObject.put("playerId", ((Bullet) bulletModelViewTuple.getModel()).getPlayer().getId());

        data.put(bulletJsonObject);
    }

    public void save() throws IOException {
        FileWriter writer = new FileWriter("lib/src/main/resources/savedGame.json");
        writer.write(data.toString());
        writer.close();
    }
}
