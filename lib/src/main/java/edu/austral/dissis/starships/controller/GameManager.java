package edu.austral.dissis.starships.controller;

import edu.austral.dissis.starships.collision.*;
import edu.austral.dissis.starships.dataStructure.AsteroidModelViewTuple;
import edu.austral.dissis.starships.dataStructure.ModelViewTuple;
import edu.austral.dissis.starships.game.AsteroidFactory;
import edu.austral.dissis.starships.game.KeyTracker;
import edu.austral.dissis.starships.model.Asteroid;
import edu.austral.dissis.starships.view.AsteroidView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class GameManager {

//    private ShipController shipController;
    private GameObjectController gameObjectController;
    Pane pane;
    AsteroidFactory asteroidFactory;
    CollisionEngine collisionEngine;

    public GameManager(Pane pane, GameObjectController gameObjectController){
        this.pane = pane;
//        this.shipController = shipController;
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

        handleCollisions(getGameColliders());
        gameObjectController.update(secondsSinceLastFrame,pane,keyTracker);
//        System.out.println("Player 1 life: " + ((Ship) gameObjectController.getFirst().getModel()).getPlayer().getCurrentLives());
//        System.out.println("Player 1 score: " + ((Ship) gameObjectController.getFirst().getModel()).getPlayer().getScore());
//        System.out.println("Player 2 life: " + ((Ship) gameObjectController.getSecond().getModel()).getPlayer().getCurrentLives());
//        System.out.println("Player 2 score: " + ((Ship) gameObjectController.getSecond().getModel()).getPlayer().getScore());

    }
    private void handleCollisions(ArrayList<GameCollider> gameColliderArrayList){
        collisionEngine.checkCollisions(gameColliderArrayList);
    }
//    public boolean isOver(){
//        System.out.println(gameObjectController.getFirst().getModel().isAlive());
//        System.out.println(gameObjectController.getSecond().getModel().isAlive());
//        return !gameObjectController.getFirst().getModel().isAlive() && !gameObjectController.getSecond().getModel().isAlive();
//    }

    private ArrayList<GameCollider> getGameColliders()  {
        ArrayList<GameCollider> gameColliders = new ArrayList<>();
        for (ModelViewTuple gameObjectModelViewTuple : gameObjectController.getModelViewTuples()) {
            gameColliders.add(gameObjectModelViewTuple.getModel().getCollider());
        }
        return gameColliders;
    }
}
