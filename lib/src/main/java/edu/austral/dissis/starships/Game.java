package edu.austral.dissis.starships;

import edu.austral.dissis.starships.config.Config;
import edu.austral.dissis.starships.controller.GameController;
import edu.austral.dissis.starships.controller.GameObjectController;
import edu.austral.dissis.starships.controller.ShipController;
import edu.austral.dissis.starships.file.ImageLoader;
import edu.austral.dissis.starships.game.*;
import edu.austral.dissis.starships.model.Ship;
import edu.austral.dissis.starships.model.SpaceShipButton;
import edu.austral.dissis.starships.dataStructure.DimensionTuple;
import edu.austral.dissis.starships.dataStructure.Vector2;
import edu.austral.dissis.starships.player.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Game extends GameApplication {
    @Override
    public @NotNull WindowSettings setupWindow() {
        return WindowSettings.fromTitle("Starships!").withSize(1920,1080);
    }

    @Override
    public Parent initRoot(@NotNull GameContext context) throws IOException {
            return new GameManager(this, context).init();
    }
}

class GameManager {

    final RootSetter rootSetter;
    final GameContext context;
    MainTimer mainTimer;
    ArrayList<Player> players;
    boolean isMenu = true;

    public GameManager(RootSetter rootSetter, GameContext gameContext){
        this.rootSetter = rootSetter;
        this.context = gameContext;
        players = new ArrayList<>();
    }

    Parent init() throws IOException {
        return isMenu ? loadMenu() : loadGame();
    }


    private Parent loadMenu() throws IOException {
        ImageLoader imageLoader = new ImageLoader();
        Pane pane = new Pane();
        pane.setPrefSize(1920, 1080);
        BackgroundImage backgroundImage= new BackgroundImage(imageLoader.loadFromResources("background.jpg", 1920, 1080),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(backgroundImage));
        SpaceShipButton startButton = new SpaceShipButton("Start Game!",960,540);
        pane.getChildren().add(startButton);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isMenu = !isMenu;
                try {
                    rootSetter.setRoot(init());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return pane;
    }

    private Parent loadGame() throws IOException {
        ImageLoader imageLoader = new ImageLoader();
        Pane pane = new Pane();
//        load();

        ShipController shipController = new ShipController(
                new Ship(new Vector2(900,500),new Vector2(0,-1),200,new DimensionTuple(85,85))
        );
//        BulletController bulletController = new BulletController();
//
//        AsteroidsController asteroidsController = new AsteroidsController();
        GameObjectController gameObjectController = new GameObjectController();
        BackgroundImage backgroundImage= new BackgroundImage(imageLoader.loadFromResources("background.jpg", 1920, 1080),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(backgroundImage));

        pane.getChildren().add(shipController.getShipView().getImageView());
//        pane.getChildren().add(players.get(0).getShipController().getShipView().getImageView());
        mainTimer = new MainTimer(pane,shipController,gameObjectController, context.getKeyTracker());
//        mainTimer = new MainTimer(pane,players.get(0).getShipController(),bulletController, asteroidsController, context.getKeyTracker());
        mainTimer.start();
        return pane;

    }
    public void load() {
        List<Map<String, String>> gameConfiguration = Config.getGameConfiguration();
        for (int i = 0; i < gameConfiguration.size(); i++) {
            Map<String, String> playerConfiguration = gameConfiguration.get(i);
            Player player = new Player(playerConfiguration);
            players.add(player);
        }

    }
}

 class MainTimer extends GameTimer {

    GameController gameController;
    KeyTracker keyTracker;
    boolean isPaused = false;
     public MainTimer(Pane pane, ShipController shipController, GameObjectController gameObjectController, KeyTracker keyTracker) {
        gameController = new GameController(pane, shipController, gameObjectController);
        this.keyTracker = keyTracker;
     }

    @Override
    public void nextFrame(double secondsSinceLastFrame) {

        if (gameController.isOver()){
            stop();
        }
        if (keyTracker.getKeySet().contains(KeyCode.P)){
            isPaused = !isPaused;
            if (keyTracker.getKeySet().contains(KeyCode.S)){

            }
            keyTracker.getKeySet().remove(KeyCode.P);
        }
        if (!isPaused) {
            gameController.update(secondsSinceLastFrame, keyTracker);
        }
    }

 }
