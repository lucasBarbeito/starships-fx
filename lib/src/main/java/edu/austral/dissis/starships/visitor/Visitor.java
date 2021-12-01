package edu.austral.dissis.starships.visitor;

import edu.austral.dissis.starships.dataStructure.AsteroidModelViewTuple;
import edu.austral.dissis.starships.dataStructure.BulletModelViewTuple;
import edu.austral.dissis.starships.dataStructure.ShipModelViewTuple;

public interface Visitor {

    void visit(ShipModelViewTuple shipModelViewTuple);
    void visit(AsteroidModelViewTuple asteroidModelViewTuple);
    void visit(BulletModelViewTuple bulletModelViewTuple);
}
