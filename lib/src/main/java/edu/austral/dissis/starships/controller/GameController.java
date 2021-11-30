package edu.austral.dissis.starships.controller;

import edu.austral.dissis.starships.collision.*;
import edu.austral.dissis.starships.dataStructure.AsteroidModelViewTuple;
import edu.austral.dissis.starships.dataStructure.BulletModelViewTuple;
import edu.austral.dissis.starships.dataStructure.ModelViewTuple;
import edu.austral.dissis.starships.game.AsteroidFactory;
import edu.austral.dissis.starships.game.KeyTracker;
import edu.austral.dissis.starships.model.Asteroid;
import edu.austral.dissis.starships.model.Bullet;
import edu.austral.dissis.starships.model.GameObject;
import edu.austral.dissis.starships.view.AsteroidView;
import edu.austral.dissis.starships.view.BulletView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class GameController {

    private ShipController shipController;
    private GameObjectController gameObjectController;
//    private BulletController bulletController;
//    private AsteroidsController asteroidsController;
    Pane pane;
    AsteroidFactory asteroidFactory;
    CollisionEngine collisionEngine;



    public GameController(Pane pane, ShipController shipController, GameObjectController gameObjectController){
        this.pane = pane;
        this.shipController = shipController;
        this.gameObjectController = gameObjectController;
        asteroidFactory = new AsteroidFactory();
        collisionEngine = new CollisionEngine();
    }

    public void update(double secondsSinceLastFrame, KeyTracker keyTracker){
        try {
            Optional<Asteroid> asteroid = asteroidFactory.createAsteroid(pane.getHeight(),pane.getWidth(),secondsSinceLastFrame);
            if(asteroid.isPresent()) {
                AsteroidView asteroidView = new AsteroidView(asteroid.get().getPosition().getX(),asteroid.get().getPosition().getY(),
                        asteroid.get().getDirection().getAngle()*180/Math.PI,
                        asteroid.get().getDimension().getWidth(), asteroid.get().getDimension().getHeight());
                gameObjectController.add(new AsteroidModelViewTuple(asteroid.get(),asteroidView));
                pane.getChildren().add(asteroidView.getImageView());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        keyTracker.getKeySet().forEach(keyCode -> {
            if (keyCode == KeyCode.UP){
                shipController.forward(secondsSinceLastFrame,pane);
            }
            if (keyCode == KeyCode.DOWN){
                shipController.backward(secondsSinceLastFrame,pane);
            }
            if (keyCode == KeyCode.RIGHT){
                shipController.rotateRight(secondsSinceLastFrame);
            }
            if (keyCode == KeyCode.LEFT){
                shipController.rotateLeft(secondsSinceLastFrame);
            }
            if (keyCode == KeyCode.SPACE){
                Bullet bullet = shipController.getShipModel().shoot();
                BulletView bulletView = new BulletView(bullet.getPosition().getX(),bullet.getPosition().getY(),bullet.getDirection().getAngle()*180/Math.PI,
                        bullet.getDimension().getWidth(), bullet.getDimension().getHeight());
                gameObjectController.add(new BulletModelViewTuple(bullet,bulletView));
                pane.getChildren().add(bulletView.getImageView());
            }
        });
        handleCollisions(getGameColliders());
        gameObjectController.update(secondsSinceLastFrame,pane);
    }
    private void handleCollisions(ArrayList<GameCollider> gameColliderArrayList){
        collisionEngine.checkCollisions(gameColliderArrayList);
    }
    public boolean isOver(){
        return !shipController.getShipModel().isAlive();
    }

    private ArrayList<GameCollider> getGameColliders()  {
        ArrayList<GameCollider> gameColliders = new ArrayList<>();
//        gameColliders.add(new ShipCollider(shipController.getShipModel()));
        for (ModelViewTuple gameObjectModelViewTuple : gameObjectController.getModelViewTuples()) {
            gameColliders.add(gameObjectModelViewTuple.getModel().getCollider());
        }
        System.out.println(gameObjectController.getModelViewTuples().size());
        return gameColliders;
    }
}
