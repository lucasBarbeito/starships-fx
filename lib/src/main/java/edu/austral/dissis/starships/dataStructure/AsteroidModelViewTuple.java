package edu.austral.dissis.starships.dataStructure;

import edu.austral.dissis.starships.visitor.Visitor;
import edu.austral.dissis.starships.model.Asteroid;
import edu.austral.dissis.starships.view.AsteroidView;

public class AsteroidModelViewTuple extends ModelViewTuple{
    public AsteroidModelViewTuple(Asteroid asteroid, AsteroidView asteroidView) {
        super(asteroid, asteroidView);
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
