/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tembakanapa;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author NITRO 5
 */
public class Bullet{
    private AnchorPane gamePane;
    private ImageView player;
    private FXMLDocumentController controller;

    private List<ImageView> bullets = new ArrayList<>();
    private final String BULLET_IMAGE = "image/bullet.png";
    
    public Bullet(AnchorPane gamePane, ImageView player, FXMLDocumentController controller) {
        this.gamePane = gamePane;
        this.player = player;
        this.controller = controller;
    }
    
        
    public void shootBullet() {
        ImageView bullet = new ImageView(new Image(BULLET_IMAGE));
        bullet.setFitWidth(10);  
        bullet.setFitHeight(20);
        bullet.setLayoutX(player.getLayoutX() + player.getFitWidth() / 2 - bullet.getFitWidth() / 2);
        bullet.setLayoutY(player.getLayoutY() - bullet.getFitHeight());
        gamePane.getChildren().add(bullet);
        bullets.add(bullet);

        Timeline bulletTimeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            bullet.setLayoutY(bullet.getLayoutY() - 5); // Move bullet upwards
            checkCollisions(bullet);
            if (bullet.getLayoutY() < 0) {
                gamePane.getChildren().remove(bullet);
                bullets.remove(bullet);
            }
        }));
        bulletTimeline.setCycleCount(Timeline.INDEFINITE);
        bulletTimeline.play();
    }

    public boolean checkCollision(ImageView bullet, ImageView enemy) {
        double bulletLeft = bullet.getLayoutX();
        double bulletRight = bulletLeft + bullet.getFitWidth();
        double bulletTop = bullet.getLayoutY();
        double bulletBottom = bulletTop + bullet.getFitHeight();

        double enemyLeft = enemy.getLayoutX();
        double enemyRight = enemyLeft + enemy.getFitWidth();
        double enemyTop = enemy.getLayoutY();
        double enemyBottom = enemyTop + enemy.getFitHeight();

        return bulletRight > enemyLeft && bulletLeft < enemyRight &&
               bulletBottom > enemyTop && bulletTop < enemyBottom;
    }
    
    public void checkCollisions(ImageView bullet) {
            
            
        for (ImageView enemy : controller.myEnemy.enemies) {
            if (checkCollision(bullet, enemy)) {
                gamePane.getChildren().remove(bullet);
                gamePane.getChildren().remove(enemy);
                controller.myEnemy.enemies.remove(enemy);
                bullets.remove(bullet);
                controller.score += 10;
                controller.updateScoreLabel();
                break;
            }
        }
    }

}
