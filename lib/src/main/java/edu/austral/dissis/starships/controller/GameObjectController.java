package edu.austral.dissis.starships.controller;

import edu.austral.dissis.starships.dataStructure.ModelViewTuple;
import edu.austral.dissis.starships.model.GameObject;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class GameObjectController {

    private ArrayList<ModelViewTuple> modelViewTuples;

    public GameObjectController() { modelViewTuples = new ArrayList<>(); }

    public void update(double seconds, Pane pane){
        ArrayList<ModelViewTuple> newModelViewTuples = new ArrayList<>();
        ArrayList<ModelViewTuple> toRemove = new ArrayList<>();
        ModelStepVisitor modelStepVisitor = new ModelStepVisitor();
        modelStepVisitor.setSeconds(seconds);
        modelStepVisitor.setPane(pane);
        for (ModelViewTuple modelViewTuple : modelViewTuples) {
            modelViewTuple.accept(modelStepVisitor);
//            System.out.println(modelStepVisitor.getUpdatedTuple());
            if (modelStepVisitor.getUpdatedTuple().isEmpty()){
//                System.out.println("Entro al empty");
                pane.getChildren().remove(modelViewTuple.getView().getImageView());
                toRemove.add(modelViewTuple);
            }else {
                newModelViewTuples.add(modelStepVisitor.getUpdatedTuple().get());
            }
        }
        modelViewTuples.removeAll(toRemove);
        modelViewTuples = newModelViewTuples;
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

}
