package edu.austral.dissis.starships.collision;

import edu.austral.dissis.starships.dataStructure.Vector2;
import edu.austral.dissis.starships.model.GameObject;
import edu.austral.dissis.starships.model.Ship;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.jetbrains.annotations.NotNull;

public class ShipCollider implements GameCollider{

    private Ship ship;

    public ShipCollider(Ship ship) {
        this.ship = ship;
    }

    @Override
    public @NotNull Shape getShape() {
        return new Rectangle(ship.getPosition().getX(), ship.getPosition().getY(), ship.getDimension().getWidth(), ship.getDimension().getHeight());
    }

    @Override
    public void handleCollisionWith(@NotNull GameCollider collider) {
        collider.handleCollisionWith(this);
    }

    @Override
    public GameObject getModel() {
        return ship;
    }

    @Override
    public void handleCollisionWith(ShipCollider shipCollider) {

    }

    @Override
    public void handleCollisionWith(AsteroidCollider asteroidCollider) {
        ship.setAlive(false);
//        ship.setAlive(false);
    }

    @Override
    public void handleCollisionWith(BulletCollider bulletCollider) {

    }
}
