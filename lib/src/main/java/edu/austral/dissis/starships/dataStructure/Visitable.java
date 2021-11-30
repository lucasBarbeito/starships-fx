package edu.austral.dissis.starships.dataStructure;

import edu.austral.dissis.starships.controller.Visitor;

public interface Visitable {
    public void accept(Visitor visitor);
}
