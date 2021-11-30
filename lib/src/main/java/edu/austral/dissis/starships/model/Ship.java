package edu.austral.dissis.starships.model;

import edu.austral.dissis.starships.collision.ShipCollider;
import edu.austral.dissis.starships.controller.Visitor;
import edu.austral.dissis.starships.dataStructure.DimensionTuple;
import edu.austral.dissis.starships.dataStructure.Vector2;

public class Ship extends GameObject{


    public Ship(Vector2 position, Vector2 direction, float speed, DimensionTuple dimension) {
        super(position, direction.asUnitary(), speed, dimension);
    }

    public Bullet shoot(){
        return new Bullet(this.position,this.direction,400, new DimensionTuple(15,15));
    }

    public ShipCollider getCollider(){
        return new ShipCollider(this);
    }

}
