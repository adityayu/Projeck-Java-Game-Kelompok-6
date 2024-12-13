package tembakanapa;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane gamePane;

    @FXML
    private ImageView player;

    @FXML
    private Label scoreLabel;
    
    Player myPlayer;
    Bullet myBullet;
    Enemy myEnemy;

    public int score = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myPlayer = new Player(gamePane, player, this);
        myBullet = new Bullet(gamePane, player, this);
        myEnemy = new Enemy(gamePane, player, this);
        
        gamePane.setOnMouseClicked(event -> gamePane.requestFocus());
        gamePane.setOnKeyPressed(this::handleKeyPress);

        // Start the game loop
        startGameLoop();
    }

    public void setFocusOnGamePane() {
        gamePane.requestFocus();
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case A:
                myPlayer.movePlayer(-10, 0);
                break;
            case D:
                myPlayer.movePlayer(10, 0);
                break;
            case W:
                myPlayer.movePlayer(0, -10);
                break;
            case S:
                myPlayer.movePlayer(0, 10);
                break;
            case SPACE:
                myBullet.shootBullet();
                break;
        }
    }

    






    public void startGameLoop() {
        // Create a game loop to spawn enemies, check collisions, and game updates
        Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
            myEnemy.spawnEnemy();
            myPlayer.playerCollision(); // Check for player-enemy collisions
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }

    public void updateScoreLabel() {
        scoreLabel.setText("Score: " + score);
    }



    public void endGame()  throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("selesai.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) scoreLabel.getScene().getWindow();
        Scene newScene = new Scene(root);

        SelesaiController controller = loader.getController();
        controller.setScore(score); 
    
        stage.setScene(newScene);
        stage.show();
        
        
    }
}