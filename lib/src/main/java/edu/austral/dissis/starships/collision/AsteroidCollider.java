package edu.austral.dissis.starships.collision;

import edu.austral.dissis.starships.model.Asteroid;
import edu.austral.dissis.starships.model.GameObject;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.jetbrains.annotations.NotNull;

public class AsteroidCollider implements GameCollider{

    private Asteroid asteroid;

    public AsteroidCollider(Asteroid asteroid){
        this.asteroid = asteroid;
    }
    @Override
    public @NotNull Shape getShape() {
        return new Rectangle(asteroid.getPosition().getX(),asteroid.getPosition().getY(),asteroid.getDimension().getWidth()-25,asteroid.getDimension().getHeight()-25);
    }

    @Override
    public void handleCollisionWith(@NotNull GameCollider collider) {
        collider.handleCollisionWith(this);
    }

    @Override
    public void handleCollisionWith(ShipCollider shipCollider) {

    }

    @Override
    public void handleCollisionWith(AsteroidCollider asteroidCollider) {

    }

    @Override
    public void handleCollisionWith(BulletCollider bulletCollider) {
        asteroid.reduceLife(bulletCollider.getModel().getDamage());
        asteroid.setAlive(asteroid.getLife() >= 0);
    }

    @Override
    public GameObject getModel() {
        return asteroid;
    }
}
