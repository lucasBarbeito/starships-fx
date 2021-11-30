//package edu.austral.dissis.starships.controller;
//
//import edu.austral.dissis.starships.dataStructure.ModelViewTuple;
//import edu.austral.dissis.starships.model.Bullet;
//import edu.austral.dissis.starships.dataStructure.Vector2;
//import edu.austral.dissis.starships.model.GameObject;
//import edu.austral.dissis.starships.view.BulletView;
//import javafx.scene.layout.Pane;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//
//public class BulletController {
//
//    private ArrayList<ModelViewTuple> modelViewTuples;
//
//    public BulletController(){
//        modelViewTuples = new ArrayList<>();
//    }
//
//    public void step(double seconds, Pane pane) {
//        ArrayList<ModelViewTuple> toRemove = new ArrayList<>();
//        for (int i = 0; i < modelViewTuples.size(); i++) {
//            Bullet bullet = (Bullet) modelViewTuples.get(i).getModel();
//            Vector2 to = bullet.getPosition().add(bullet.getDirection().multiply(seconds * bullet.getSpeed()));
//            if(isInBounds(pane, to, (BulletView) modelViewTuples.get(i).getView()) && bullet.isAlive()){
//                bullet.setPosition(to);
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
//
//    public void addBullet(Bullet bullet, BulletView bulletView) {
////        ModelViewTuple modelViewTuple = new ModelViewTuple(bullet,bulletView);
////        modelViewTuples.add(modelViewTuple);
//    }
//
//
//    private boolean isInBounds(Pane pane, Vector2 to ,  BulletView bulletView) {
//        return to.getX() > 0 && to.getX() < pane.getWidth() - bulletView.getWidth() && to.getY() > 0 && to.getY() < pane.getHeight() - bulletView.getHeight();
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
