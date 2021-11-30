package edu.austral.dissis.starships.collision;

public interface GameCollider extends Collider<GameCollider> {

    public void handleCollisionWith(ShipCollider shipCollider);

    public void handleCollisionWith(AsteroidCollider asteroidCollider);

    public void handleCollisionWith(BulletCollider bulletCollider);

}
