package edu.austral.dissis.starships.controller;

import edu.austral.dissis.starships.dataStructure.BulletModelViewTuple;
import edu.austral.dissis.starships.dataStructure.ModelViewTuple;
import edu.austral.dissis.starships.game.KeyTracker;
import edu.austral.dissis.starships.model.GameObject;
import edu.austral.dissis.starships.visitor.ModelStepVisitor;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameObjectController {

    private ArrayList<ModelViewTuple> modelViewTuples;

    public GameObjectController() { modelViewTuples = new ArrayList<>(); }

    public void update(double seconds, Pane pane, KeyTracker keyTracker){
        ArrayList<ModelViewTuple> newModelViewTuples = new ArrayList<>();
        ArrayList<ModelViewTuple> toRemove = new ArrayList<>();
        ModelStepVisitor modelStepVisitor = new ModelStepVisitor();
        modelStepVisitor.setSeconds(seconds);
        modelStepVisitor.setPane(pane);
        modelStepVisitor.setKeyTracker(keyTracker);
        for (ModelViewTuple modelViewTuple : modelViewTuples) {
            modelViewTuple.accept(modelStepVisitor);
            if (modelStepVisitor.getUpdatedTuple().isEmpty()){
                pane.getChildren().remove(modelViewTuple.getView().getImageView());
                toRemove.add(modelViewTuple);
            } else {
                newModelViewTuples.add(modelStepVisitor.getUpdatedTuple().get());
            }
        }
        modelViewTuples.removeAll(toRemove);
        modelViewTuples = newModelViewTuples;
        List<Optional<BulletModelViewTuple>> returningBullets = modelStepVisitor.getReturningBullets();
        for (int i = 0; i < returningBullets.size(); i++){
            if (returningBullets.get(i).isPresent()){
                modelViewTuples.add(returningBullets.get(i).get());
            }
        }
    }

    public void add(ModelViewTuple modelViewTuple){
        modelViewTuples.add(modelViewTuple);
    }

    public ArrayList<ModelViewTuple> getModelViewTuples() {
        return modelViewTuples;
    }

    public ArrayList<GameObject> getModels(){
        ArrayList<GameObject> models = new ArrayList<>();
        for (ModelViewTuple modelViewTuple : modelViewTuples) {
            models.add(modelViewTuple.getModel());
        }
        return models;
    }
    public ModelViewTuple getFirst(){
        return modelViewTuples.get(0);
    }
    public ModelViewTuple getSecond(){
        return modelViewTuples.get(1);
    }

}
