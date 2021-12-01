package edu.austral.dissis.starships.dataStructure;

import edu.austral.dissis.starships.model.GameObject;
import edu.austral.dissis.starships.view.GameObjectView;
import edu.austral.dissis.starships.visitor.Visitable;

public abstract class ModelViewTuple implements Visitable {

    private GameObject model;
    private GameObjectView view;

    public ModelViewTuple(GameObject model, GameObjectView view) {
        this.model = model;
        this.view = view;
    }

    public GameObject getModel() {
        return model;
    }

    public GameObjectView getView() {
        return view;
    }


}
