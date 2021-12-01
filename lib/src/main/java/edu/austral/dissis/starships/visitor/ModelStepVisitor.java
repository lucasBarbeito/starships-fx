package edu.austral.dissis.starships.visitor;

import edu.austral.dissis.starships.dataStructure.*;
import edu.austral.dissis.starships.game.KeyTracker;
import edu.austral.dissis.starships.model.Bullet;
import edu.austral.dissis.starships.model.GameObject;
import edu.austral.dissis.starships.model.Ship;
import edu.austral.dissis.starships.player.Player;
import edu.austral.dissis.starships.view.BulletView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Optional;

public class ModelStepVisitor implements Visitor {
    private Pane pane;
    private double seconds;
    private KeyTracker keyTracker;
    private Optional<ModelViewTuple> updatedTuple = Optional.empty();
    private ArrayList<Optional<BulletModelViewTuple>> returningBullets = new ArrayList<>();


    @Override
    public void visit(ShipModelViewTuple shipModelViewTuple) {
        shipStep(shipModelViewTuple, shipModelViewTuple.getModel().getDimension().getWidth()/20);
    }
    @Override
    public void visit(AsteroidModelViewTuple asteroidModelViewTuple) { step(asteroidModelViewTuple,210); }
    @Override
    public void visit(BulletModelViewTuple bulletModelViewTuple) { step(bulletModelViewTuple,210); }

    private void step(ModelViewTuple modelViewTuple,double inBoundsplus){
        GameObject gameObject = modelViewTuple.getModel();
        Vector2 to = gameObject.getPosition().add(gameObject.getDirection().multiply(seconds * gameObject.getSpeed()));
        if(isInBounds(to, gameObject,inBoundsplus) && gameObject.isAlive()){
            gameObject.setPosition(to);
            modelViewTuple.getView().move(to);
             setUpdatedTuple(Optional.of(modelViewTuple));
        } else {
            setUpdatedTuple(Optional.empty());
        }
    }

    private boolean isInBounds(Vector2 to , GameObject gameObject,double inBoundsplus) {
        return to.getX() > - inBoundsplus && to.getX() < pane.getWidth() + inBoundsplus - gameObject.getDimension().getWidth() &&
                to.getY() > - inBoundsplus && to.getY() < pane.getHeight() + inBoundsplus - gameObject.getDimension().getHeight();
    }

    private void setUpdatedTuple(Optional<ModelViewTuple> updatedTuple){ this.updatedTuple = updatedTuple;}

    public Optional<ModelViewTuple> getUpdatedTuple() { return updatedTuple; }

    public void setPane(Pane pane) { this.pane = pane; }

    public void setSeconds(double seconds){ this.seconds = seconds; }

    public void setKeyTracker(KeyTracker keyTracker) { this.keyTracker = keyTracker; }

    private void shipStep(ShipModelViewTuple shipModelViewTuple, double inBoundsplus){
        if(shipModelViewTuple.getModel().isAlive()){
            keyTracker.getKeySet().forEach(keyCode -> {
                if (keyCode.toString().equals(((Ship) shipModelViewTuple.getModel()).getPlayer().getMoveForwardKey())) {
                    shipMoveFoward(shipModelViewTuple,inBoundsplus);
                }
                if (keyCode.toString().equals(((Ship) shipModelViewTuple.getModel()).getPlayer().getMoveBackwardKey())) {
                    shipMoveBackward(shipModelViewTuple,inBoundsplus);
                }
                if (keyCode.toString().equals(((Ship) shipModelViewTuple.getModel()).getPlayer().getRotateLeftKey())){
                    shipRotateLeft(shipModelViewTuple);
                }
                if (keyCode.toString().equals(((Ship) shipModelViewTuple.getModel()).getPlayer().getRotateRightKey())){
                    shipRotateRight(shipModelViewTuple);
                }
                if (keyCode.toString().equals(((Ship) shipModelViewTuple.getModel()).getPlayer().getShootKey())){
                    Bullet bullet = ((Ship) shipModelViewTuple.getModel()).shoot();
                    BulletView bulletView = new BulletView(bullet.getPosition().getX(),bullet.getPosition().getY(),bullet.getDirection().getAngle()*180/Math.PI,
                            bullet.getDimension().getWidth(), bullet.getDimension().getHeight());
                    BulletModelViewTuple bulletModelViewTuple = new BulletModelViewTuple(bullet, bulletView);
                    returningBullets.add(Optional.of(bulletModelViewTuple));
                    pane.getChildren().add(bulletModelViewTuple.getView().getImageView());

                }
            });
        }else if (((Ship) shipModelViewTuple.getModel()).getPlayer().getCurrentLives() > 0){
            ((Ship) shipModelViewTuple.getModel()).getPlayer().restLife();
            shipModelViewTuple.getModel().setAlive(true);
            shipModelViewTuple.getView().move(new Vector2(pane.getWidth()/2,pane.getHeight()/2));
            shipModelViewTuple.getModel().setPosition(new Vector2(pane.getWidth()/2,pane.getHeight()/2));
        }

        if (shipModelViewTuple.getModel().isAlive()) {
            setUpdatedTuple(Optional.of(shipModelViewTuple));
        }

    }
    private void shipMoveFoward(ShipModelViewTuple shipModelViewTuple, double inBoundsplus){
        double movement = seconds * shipModelViewTuple.getModel().getSpeed();
        Vector2 movementVector = Vector2.vectorFromModule(movement, (Math.toRadians(shipModelViewTuple.getView().getRotate()) - Math.PI/2));
        Vector2 from = Vector2.vector((float) shipModelViewTuple.getView().getLayoutX(), (float) shipModelViewTuple.getView().getLayoutY());
        Vector2 to = from.add(movementVector);
        if(isInBounds(to,shipModelViewTuple.getModel(),inBoundsplus)){
            shipModelViewTuple.getView().move(to);
            shipModelViewTuple.getModel().setPosition(to);
            setUpdatedTuple(Optional.of(shipModelViewTuple));
        } else {
            setUpdatedTuple(Optional.empty());
        }
    }
    private void shipMoveBackward(ShipModelViewTuple shipModelViewTuple,double inBoundsplus){
        double movement = seconds * shipModelViewTuple.getModel().getSpeed();
        Vector2 movementVector = Vector2.vectorFromModule(-movement, (Math.toRadians(shipModelViewTuple.getView().getRotate()) - Math.PI/2));
        Vector2 from = Vector2.vector((float) shipModelViewTuple.getView().getLayoutX(), (float) shipModelViewTuple.getView().getLayoutY());
        Vector2 to = from.add(movementVector);
        if(isInBounds(to,shipModelViewTuple.getModel(),inBoundsplus)){
            shipModelViewTuple.getView().move(to);
            shipModelViewTuple.getModel().setPosition(to);
            setUpdatedTuple(Optional.of(shipModelViewTuple));
        } else {
            setUpdatedTuple(Optional.empty());
        }
    }
    private void shipRotateLeft(ShipModelViewTuple shipModelViewTuple) {
        double movement = seconds * shipModelViewTuple.getModel().getSpeed()/80;
        shipModelViewTuple.getModel().setDirection(shipModelViewTuple.getModel().getDirection().rotate(-movement));
        shipModelViewTuple.getView().setRotate(shipModelViewTuple.getModel().getDirection().getAngle()*180/Math.PI+90);
    }
    private void shipRotateRight(ShipModelViewTuple shipModelViewTuple) {
        double movement = seconds * shipModelViewTuple.getModel().getSpeed()/80;
        shipModelViewTuple.getModel().setDirection(shipModelViewTuple.getModel().getDirection().rotate(movement));
        shipModelViewTuple.getView().setRotate(shipModelViewTuple.getModel().getDirection().getAngle()*180/Math.PI+90);
    }

    private boolean isAvailableKey(Player player){
        return keyTracker.getKeySet().stream().noneMatch(keyCode -> keyCode.toString().equals(player.getMoveForwardKey()));
    }

    public ArrayList<Optional<BulletModelViewTuple>> getReturningBullets() {
        ArrayList<Optional<BulletModelViewTuple>> newReturningBullets = new ArrayList<>(returningBullets);
        returningBullets.clear();
        return newReturningBullets;
    }
}

