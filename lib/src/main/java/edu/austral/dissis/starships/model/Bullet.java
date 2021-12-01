package edu.austral.dissis.starships.model;

import edu.austral.dissis.starships.collision.BulletCollider;
import edu.austral.dissis.starships.dataStructure.DimensionTuple;
import edu.austral.dissis.starships.dataStructure.Vector2;
import edu.austral.dissis.starships.player.Player;

public class Bullet extends GameObject{

    public double damage;
    private Player player;
    public Bullet(Vector2 position, Vector2 direction, float speed, DimensionTuple dimension, Player player) {
        super(position, direction, speed, dimension);
        damage = (dimension.getHeight()+dimension.getWidth());
        this.player = player;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public Player getPlayer() {
        return player;
    }

    public BulletCollider getCollider(){
        return new BulletCollider(this);
    }
}
