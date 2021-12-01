package edu.austral.dissis.starships.model;

import edu.austral.dissis.starships.collision.AsteroidCollider;
import edu.austral.dissis.starships.dataStructure.DimensionTuple;
import edu.austral.dissis.starships.dataStructure.Vector2;

public class Asteroid extends GameObject{

    private double life;

    public Asteroid(Vector2 position, Vector2 direction, float speed, DimensionTuple dimension) {
        super(position, direction, speed,dimension);
        life = (dimension.getWidth() + dimension.getHeight()) / 2;
    }

    public double getLife() {
        return life;
    }

    public void reduceLife(double lifeReduced) {
        life -= lifeReduced;
    }

    public AsteroidCollider getCollider(){
        return new AsteroidCollider(this);
    }


}
