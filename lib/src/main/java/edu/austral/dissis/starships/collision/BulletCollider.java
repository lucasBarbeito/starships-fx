package edu.austral.dissis.starships.collision;

import edu.austral.dissis.starships.model.Bullet;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.jetbrains.annotations.NotNull;

public class BulletCollider implements GameCollider {

    private Bullet bullet;

    public BulletCollider(Bullet bullet) {
        this.bullet = bullet;
    }
    @Override
    public @NotNull Shape getShape() {
        return new Rectangle(bullet.getPosition().getX(),bullet.getPosition().getY(),bullet.getDimension().getWidth(),bullet.getDimension().getHeight());
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
        bullet.setAlive(false);
    }

    @Override
    public void handleCollisionWith(BulletCollider bulletCollider) {

    }

    @Override
    public Bullet getModel() {
        return bullet;
    }
}
