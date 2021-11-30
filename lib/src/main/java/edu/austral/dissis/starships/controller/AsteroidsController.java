//package edu.austral.dissis.starships.controller;
//
//import edu.austral.dissis.starships.dataStructure.ModelViewTuple;
//import edu.austral.dissis.starships.model.Asteroid;
//import edu.austral.dissis.starships.dataStructure.Vector2;
//import edu.austral.dissis.starships.model.GameObject;
//import edu.austral.dissis.starships.view.AsteroidView;
//import javafx.scene.layout.Pane;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class AsteroidsController {
//
//    private ArrayList<ModelViewTuple> modelViewTuples;
//
//    public AsteroidsController(){
//        modelViewTuples = new ArrayList<>();
//    }
//
//    public void step(double seconds, Pane pane) {
//        ArrayList<ModelViewTuple> toRemove = new ArrayList<>();
//        for (int i = 0; i < modelViewTuples.size(); i++) {
//            Asteroid asteroid = (Asteroid) modelViewTuples.get(i).getModel();
//            Vector2 to = asteroid.getPosition().add(asteroid.getDirection().multiply(seconds * asteroid.getSpeed()));
//            if(isInBounds(pane, to, (AsteroidView) modelViewTuples.get(i).getView()) && asteroid.isAlive()){
//                asteroid.setPosition(to);
//                modelViewTuples.get(i).getView().move(to);
//            }else {
//                toRemove.add(modelViewTuples.get(i));
//            }
//        }
//        for (ModelViewTuple removable: toRemove) {
//            pane.getChildren().remove((removable.getView()).getImageView());
//            modelViewTuples.remove(removable);
//        }
//    }
//    public void addAsteroid(Asteroid asteroid, AsteroidView asteroidView) throws IOException {
////        ModelViewTuple modelViewTuple = new ModelViewTuple(asteroid, asteroidView);
////        modelViewTuples.add(modelViewTuple);
//    }
//
//
//    private boolean isInBounds(Pane pane, Vector2 to , AsteroidView asteroidView) {
//        return to.getX() > - 210 && to.getX() < pane.getWidth() + 210 - asteroidView.getWidth() &&
//        to.getY() > -210 && to.getY() < pane.getHeight() + 210 - asteroidView.getHeight();
//    }
//
//    public ArrayList<ModelViewTuple> getModelViewTuples() {
//        return modelViewTuples;
//    }
//
//    public ArrayList<GameObject> getModels(){
//        ArrayList<GameObject> models = new ArrayList<>();
//        for (ModelViewTuple modelViewTuple : modelViewTuples) {
//            models.add(modelViewTuple.getModel());
//        }
//        return models;
//    }
//}
