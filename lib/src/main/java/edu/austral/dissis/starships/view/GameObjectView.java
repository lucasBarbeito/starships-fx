package edu.austral.dissis.starships.view;

import edu.austral.dissis.starships.dataStructure.Vector2;
import javafx.scene.image.ImageView;


public abstract class GameObjectView {

    protected ImageView imageView;

    protected GameObjectView(double x, double y, double angle, double width, double height) {

    }


    public double getLayoutX() {
        return imageView.getLayoutX();
    }

    public double getLayoutY() {
        return imageView.getLayoutY();
    }

    public ImageView getImageView() {
        return imageView;
    }
    public double getRotate() {
        return imageView.getRotate();
    }

    public void setRotate(double v) {
        imageView.setRotate(v);
    }
    public double getWidth(){
        return imageView.getImage().getWidth();
    }
    public double getHeight(){
        return imageView.getImage().getHeight();
    }

    public void move(Vector2 to) {
        imageView.setLayoutX(to.getX());
        imageView.setLayoutY(to.getY());
    }
}
