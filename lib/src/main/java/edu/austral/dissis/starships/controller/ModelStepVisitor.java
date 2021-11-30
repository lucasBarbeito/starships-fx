package edu.austral.dissis.starships.controller;

import edu.austral.dissis.starships.dataStructure.*;
import edu.austral.dissis.starships.model.GameObject;
import javafx.scene.layout.Pane;

import java.util.Optional;

public class ModelStepVisitor implements Visitor {
    private Pane pane;
    private double seconds;
    private Optional<ModelViewTuple> updatedTuple;

    @Override
    public void visit(ShipModelViewTuple shipModelViewTuple) {

    }

    @Override
    public void visit(AsteroidModelViewTuple asteroidModelViewTuple) {
        step(seconds, pane, 210,asteroidModelViewTuple);

    }

    @Override
    public void visit(BulletModelViewTuple bulletModelViewTuple) {
        step(seconds, pane,1 ,bulletModelViewTuple);

    }

    private void step(double seconds, Pane pane, double plus, ModelViewTuple modelViewTuple){
        GameObject gameObject = modelViewTuple.getModel();
        Vector2 to = gameObject.getPosition().add(gameObject.getDirection().multiply(seconds * gameObject.getSpeed()));
//        System.out.println(isInBounds(pane, to, gameObject,plus) && gameObject.isAlive());
        if(isInBounds(pane, to, gameObject,plus) && gameObject.isAlive()){
            gameObject.setPosition(to);
            modelViewTuple.getView().move(to);
             setUpdatedTuple(Optional.of(modelViewTuple));
        } else {
            setUpdatedTuple(Optional.empty());
//            setUpdatedTuple(Optional.empty());
        }
//        setUpdatedTuple(Optional.of(modelViewTuple));
    }

    private boolean isInBounds(Pane pane, Vector2 to , GameObject gameObject, double plus) {
        return to.getX() > - 210 && to.getX() < pane.getWidth() + 210 - gameObject.getDimension().getWidth() &&
                to.getY() > - 210 && to.getY() < pane.getHeight() + 210 - gameObject.getDimension().getHeight();
    }
    public void setPane(Pane pane){
        this.pane = pane;
    }
    public void setSeconds(double seconds){
        this.seconds = seconds;
    }

    private void setUpdatedTuple(Optional<ModelViewTuple> updatedTuple){
        this.updatedTuple = updatedTuple;
    }

    public Optional<ModelViewTuple> getUpdatedTuple() {
        return updatedTuple;
    }
}
