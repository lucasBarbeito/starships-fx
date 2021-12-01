package edu.austral.dissis.starships.model;

import edu.austral.dissis.starships.collision.ShipCollider;
import edu.austral.dissis.starships.dataStructure.DimensionTuple;
import edu.austral.dissis.starships.dataStructure.Vector2;
import edu.austral.dissis.starships.player.Player;

public class Ship extends GameObject{

    Player player;

    public Ship(Vector2 position, Vector2 direction, float speed, DimensionTuple dimension, Player player) {
        super(position, direction.asUnitary(), speed, dimension);
        this.player = player;
    }

    public Bullet shoot(){
        Bullet returningBullet = new Bullet(this.position,this.direction,400, new DimensionTuple(15,15),player);
        for (int i = 1; i < 5; i++) {
            if (!(player.getScore() > i * 5000)) {
                System.out.println("Score: " + player.getScore());
                 returningBullet =  new Bullet(this.position,this.direction,400, new DimensionTuple(i*10,i*10),player);
                 break;
            }else{
                returningBullet =  new Bullet(this.position,this.direction,400, new DimensionTuple(i*10,i*10),player);
            }
        }

//        if (player.getScore() > 2000){
//            return new Bullet(this.position,this.direction,400, new DimensionTuple(20,20),player);
//        }
//        if (player.getScore() > 4000){
//            return new Bullet(this.position,this.direction,400, new DimensionTuple(25,25),player);
//        }
//        if (player.getScore() > 6000){
//            return new Bullet(this.position,this.direction,400, new DimensionTuple(30,30),player);
//        }
        return returningBullet;

    }

    public ShipCollider getCollider(){
        return new ShipCollider(this);
    }

    public Player getPlayer() {
        return player;
    }
}
