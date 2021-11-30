package edu.austral.dissis.starships.controller;

import edu.austral.dissis.starships.dataStructure.AsteroidModelViewTuple;
import edu.austral.dissis.starships.dataStructure.BulletModelViewTuple;
import edu.austral.dissis.starships.dataStructure.ShipModelViewTuple;
import edu.austral.dissis.starships.model.Asteroid;
import edu.austral.dissis.starships.model.Bullet;
import edu.austral.dissis.starships.model.Ship;

public interface Visitor {

    public void visit(ShipModelViewTuple shipModelViewTuple);
    public void visit(AsteroidModelViewTuple asteroidModelViewTuple);
    public void visit(BulletModelViewTuple bulletModelViewTuple);
}
