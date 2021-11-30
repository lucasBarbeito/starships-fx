package edu.austral.dissis.starships.model;

import edu.austral.dissis.starships.collision.BulletCollider;
import edu.austral.dissis.starships.dataStructure.DimensionTuple;
import edu.austral.dissis.starships.dataStructure.Vector2;

public class Bullet extends GameObject{

    public double damage;
    public Bullet(Vector2 position, Vector2 direction, float speed, DimensionTuple dimension) {
        super(position, direction, speed, dimension);
        damage = (dimension.getHeight()+dimension.getWidth());
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public BulletCollider getCollider(){
        return new BulletCollider(this);
    }
}
