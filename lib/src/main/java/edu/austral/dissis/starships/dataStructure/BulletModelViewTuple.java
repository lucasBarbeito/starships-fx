package edu.austral.dissis.starships.dataStructure;

import edu.austral.dissis.starships.controller.Visitor;
import edu.austral.dissis.starships.model.Bullet;
import edu.austral.dissis.starships.view.BulletView;

public class BulletModelViewTuple extends ModelViewTuple{

    public BulletModelViewTuple(Bullet bullet, BulletView bulletView) {
        super(bullet, bulletView);
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
