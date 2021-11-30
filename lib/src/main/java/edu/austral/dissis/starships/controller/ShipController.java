package edu.austral.dissis.starships.controller;

import edu.austral.dissis.starships.model.Ship;
import edu.austral.dissis.starships.dataStructure.Vector2;
import edu.austral.dissis.starships.view.ShipView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ShipController {

    Ship shipModel;
    ShipView shipView;

    public ShipController(Ship shipModel) throws IOException {
        this.shipModel = shipModel;
        shipView = new ShipView(shipModel.getPosition().getX(),shipModel.getPosition().getY(),shipModel.getDirection().getAngle()*180/Math.PI + 90,
                shipModel.getDimension().getWidth(),shipModel.getDimension().getHeight());
    }

    public void forward(Double secondsSinceLastFrame, Pane pane) {
        double movement = secondsSinceLastFrame * shipModel.getSpeed();
        Vector2 movementVector = Vector2.vectorFromModule(movement, (Math.toRadians(shipView.getRotate()) - Math.PI/2));
        Vector2 from = Vector2.vector((float) shipView.getLayoutX(), (float) shipView.getLayoutY());
        Vector2 to = from.add(movementVector);
        if(isInBounds(pane, to)) {
            moveShip(to);
        }
    }

    public void backward(Double secondsSinceLastFrame, Pane pane) {
        double movement = secondsSinceLastFrame * shipModel.getSpeed();
        Vector2 movementVector = Vector2.vectorFromModule(-movement, (Math.toRadians(shipView.getRotate()) - Math.PI/2));
        Vector2 from = Vector2.vector(shipView.getLayoutX(), shipView.getLayoutY());
        Vector2 to = from.add(movementVector);
        if(isInBounds(pane, to)) {
            moveShip(to);
        }
    }

    public void rotateLeft(Double secondsSinceLastFrame) {
        double movement = secondsSinceLastFrame * shipModel.getSpeed()/80;
        shipModel.setDirection(shipModel.getDirection().rotate(-movement));
        shipView.setRotate(shipModel.getDirection().getAngle()*180/Math.PI+90);
    }

    public void rotateRight(Double secondsSinceLastFrame) {
        double movement = secondsSinceLastFrame * shipModel.getSpeed()/80;
        shipModel.setDirection(shipModel.getDirection().rotate(movement));
        shipView.setRotate(shipModel.getDirection().getAngle()*180/Math.PI+90);
    }
    public void moveShip(Vector2 to) {
        shipView.move(to);
        shipModel.setPosition(to);
    }
    private boolean isInBounds(Pane pane, Vector2 to) {
        return to.getX() > 0 && to.getX() < pane.getWidth() - shipView.getWidth() && to.getY() > 0 && to.getY() < pane.getHeight() - shipView.getHeight();
    }

    public Ship getShipModel() {
        return shipModel;
    }

    public ShipView getShipView() {
        return shipView;
    }
   
}
