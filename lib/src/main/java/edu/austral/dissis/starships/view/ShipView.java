package edu.austral.dissis.starships.view;

import edu.austral.dissis.starships.file.ImageLoader;

import javafx.scene.image.ImageView;
import java.io.IOException;

public class ShipView extends GameObjectView{


    public ShipView(double x, double y, double angle,double width, double height, String imagePath) throws IOException {
        super(x, y, angle, width, height);
        ImageLoader imageLoader = new ImageLoader();
        imageView = new ImageView(imageLoader.loadFromResources(imagePath, width, height));
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        imageView.setRotate(angle);
    }
}
