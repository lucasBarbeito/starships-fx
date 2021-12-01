package edu.austral.dissis.starships.model;

import edu.austral.dissis.starships.collision.GameCollider;
import edu.austral.dissis.starships.dataStructure.DimensionTuple;
import edu.austral.dissis.starships.dataStructure.Vector2;

public abstract class GameObject{

    protected  Vector2 position, direction;
    protected DimensionTuple dimension;
    protected final float speed;
    protected boolean isAlive;

    protected GameObject(Vector2 position, Vector2 direction, float speed, DimensionTuple dimension) {
        this.position = position;
        this.direction = direction.asUnitary();
        this.speed = speed;
        this.dimension = dimension;
        isAlive = true;
    }

    public float getSpeed() {
        return speed;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setPosition(Vector2 to){
        position = to;
    }
    public void setDirection(Vector2 to){
        direction = to;
    }

    public DimensionTuple getDimension() {
        return dimension;
    }

    public boolean isAlive() {return isAlive;}

    public void setAlive(boolean alive) {isAlive = alive;}

    public abstract GameCollider getCollider();


}