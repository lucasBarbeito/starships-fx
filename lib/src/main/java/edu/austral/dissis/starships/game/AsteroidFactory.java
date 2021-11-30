package edu.austral.dissis.starships.game;

import edu.austral.dissis.starships.model.Asteroid;
import edu.austral.dissis.starships.dataStructure.DimensionTuple;
import edu.austral.dissis.starships.dataStructure.Vector2;

import java.util.Optional;

public class AsteroidFactory {

    private double time = 0;

    public Optional<Asteroid> createAsteroid(double paneHeight, double paneWidth, double secoundsSinceLastFrame) {
        time += secoundsSinceLastFrame;
        if (time >= 0.75) {
            time = 0;
            int rand = (int) (Math.random() * 4);
            double width = Math.random()*(200-50)+50;
            double height = Math.random()*(200-50)+50;
            switch (rand) {
                case 0:
                    return Optional.of( new Asteroid(
                            new Vector2(Math.random()*paneWidth,0),
                            new Vector2(Math.random()*(-1 - 1)+1,Math.random()*1),
                            (float) Math.random()*(400-100)+100, new DimensionTuple(width,height)));
                case 1:
                    return Optional.of( new Asteroid(
                            new Vector2(0,Math.random()*paneHeight),
                            new Vector2(Math.random()*1,Math.random()*(-1 - 1)+1),
                            (float) Math.random()*(400-100)+100,new DimensionTuple(width,height)));
                case 2:
                    return Optional.of( new Asteroid(
                        new Vector2(paneWidth,Math.random()*paneHeight),
                        new Vector2( -Math.random(),Math.random()*(-1 - 1)+1),
                    (float) Math.random()*(400-100)+100,new DimensionTuple(width,height)));
                case 3:
                    return Optional.of( new Asteroid(
                        new Vector2(Math.random()*paneWidth,paneHeight),
                        new Vector2( Math.random()*(-1 - 1)+1,Math.random() - 1),
                        (float) Math.random()*(400-100)+100,new DimensionTuple(width,height)));
            }
        }
      return Optional.empty();
    }
}
