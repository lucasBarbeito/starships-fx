package edu.austral.dissis.starships;

import edu.austral.dissis.starships.controller.GameObjectController;
import edu.austral.dissis.starships.controller.MyGameManager;
import edu.austral.dissis.starships.file.GameLoder;
import edu.austral.dissis.starships.file.ImageLoader;
import edu.austral.dissis.starships.game.*;
import edu.austral.dissis.starships.model.SpaceShipButton;
import edu.austral.dissis.starships.player.Player;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.util.ArrayList;

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
    GameLoder gameLoder;
    ArrayList<Player> players;
    boolean isMenu = true;
    boolean isNewGame = false;

    public GameManager(RootSetter rootSetter, GameContext gameContext) {
        this.rootSetter = rootSetter;
        this.context = gameContext;
        players = new ArrayList<>();
        gameLoder = new GameLoder();
    }

    Parent init() throws IOException {
        Parent parent = new Pane();
        if (isMenu) {
            parent = loadMenu();
        }
        if (isNewGame) {
            parent = newGame();
        }
        if (!isMenu && !isNewGame) {
            parent = savedGame();
        }
        return parent;
    }


    private Parent loadMenu() throws IOException {
        ImageLoader imageLoader = new ImageLoader();
        Pane pane = new Pane();

        pane.setPrefSize(1920, 1080);
        BackgroundImage backgroundImage = new BackgroundImage(imageLoader.loadFromResources("background.jpg", 1920, 1080),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(backgroundImage));

        Text t = new Text();
        t.setText("Starships!");
        t.setFont(Font.font("Verdana", 100));
        t.setFill(Color.BLUEVIOLET);
        t.setLayoutX(750);
        t.setLayoutY(200);
        pane.getChildren().add(t);

        SpaceShipButton startButton = new SpaceShipButton("New Game!", 900, 540, event -> {
            isMenu = !isMenu;
            isNewGame = !isNewGame;
            try {
                rootSetter.setRoot(init());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        SpaceShipButton loadGameButton = new SpaceShipButton("Load Game!", 900, 630, event -> {
            isMenu = !isMenu;
//            isNewGame = !isNewGame;
            try {
                rootSetter.setRoot(init());
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        SpaceShipButton exitButton = new SpaceShipButton("Exit", 900, 720, event -> {
            System.exit(0);
        });

        pane.getChildren().add(startButton);
        pane.getChildren().add(loadGameButton);
        pane.getChildren().add(exitButton);
        return pane;
    }

    private Parent newGame() throws IOException {
        ImageLoader imageLoader = new ImageLoader();
        Pane pane = new Pane();
        GameObjectController gameObjectController = new GameObjectController();
        gameLoder.loadNewGame(gameObjectController);

        BackgroundImage backgroundImage = new BackgroundImage(imageLoader.loadFromResources("background.jpg", 1920, 1080),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(backgroundImage));
        pane.getChildren().add(gameObjectController.getFirst().getView().getImageView());
        pane.getChildren().add(gameObjectController.getSecond().getView().getImageView());
        mainTimer = new MainTimer(pane, gameObjectController, context.getKeyTracker(), gameLoder.getPlayers());
        mainTimer.start();
        return pane;
    }

    private Parent savedGame() throws IOException {
        ImageLoader imageLoader = new ImageLoader();
        Pane pane = new Pane();
        GameObjectController gameObjectController = new GameObjectController();
        gameLoder.loadSavedGame(gameObjectController);

        BackgroundImage backgroundImage = new BackgroundImage(imageLoader.loadFromResources("background.jpg", 1920, 1080),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(backgroundImage));
        pane.getChildren().add(gameObjectController.getFirst().getView().getImageView());
        pane.getChildren().add(gameObjectController.getSecond().getView().getImageView());
        mainTimer = new MainTimer(pane, gameObjectController, context.getKeyTracker(), gameLoder.getPlayers());
        mainTimer.start();
        return pane;
    }

    class MainTimer extends GameTimer {

        MyGameManager gameController;
        KeyTracker keyTracker;
        ArrayList<Player> players;
        VBox scoreLabels;
        boolean isPaused = false;

        public MainTimer(Pane pane, GameObjectController gameObjectController, KeyTracker keyTracker, ArrayList<Player> players) {
            gameController = new MyGameManager(pane, gameObjectController);
            this.keyTracker = keyTracker;
            this.players = players;
            scoreLabels = new VBox();
            pane.getChildren().add(scoreLabels);
        }

        @Override
        public void nextFrame(double secondsSinceLastFrame) {
            scoreLabels.getChildren().clear();
            for (Player player : players) {
                Label label = new Label();
                label.setText("Score: " + player.getScore() + ". Lives: " + player.getCurrentLives());
                label.setFont(new Font(30));
                label.setTextFill(Color.WHITE);
                scoreLabels.getChildren().add(label);
            }
//        if (gameController.isOver()){
//            stop();
//        }
            if (keyTracker.getKeySet().contains(KeyCode.P)) {
                isPaused = !isPaused;
                keyTracker.getKeySet().remove(KeyCode.P);
            }

            if (!isPaused) {
                gameController.update(secondsSinceLastFrame, keyTracker);
            } else if (keyTracker.getKeySet().contains(KeyCode.V)) {
                try {
                    gameController.save();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}