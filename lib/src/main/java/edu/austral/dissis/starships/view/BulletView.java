package edu.austral.dissis.starships.view;

import edu.austral.dissis.starships.file.ImageLoader;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class BulletView extends GameObjectView{

    public BulletView(double x, double y, double angle, double width, double height) {
        super(x, y, angle, width, height);
        ImageLoader imageLoader = new ImageLoader();
        try {
            imageView = new ImageView(imageLoader.loadFromResources("bullet.png", width, height));
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        imageView.setRotate(angle);
    }



}
