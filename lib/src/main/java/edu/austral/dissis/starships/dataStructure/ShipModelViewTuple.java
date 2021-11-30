package edu.austral.dissis.starships.dataStructure;

import edu.austral.dissis.starships.controller.Visitor;
import edu.austral.dissis.starships.model.Ship;
import edu.austral.dissis.starships.view.ShipView;

public class ShipModelViewTuple extends ModelViewTuple{

    public ShipModelViewTuple(Ship model, ShipView view) {
        super(model, view);
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
