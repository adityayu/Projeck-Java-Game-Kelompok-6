/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tembakanapa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
public class Enemy extends Character implements IEnemy{
    private AnchorPane gamePane;
    private ImageView player;
    private FXMLDocumentController controller;
    
    
    public List<ImageView> enemies = new ArrayList<>();
    private final String ENEMY_IMAGE = "image/target.png";

    public Enemy(AnchorPane gamePane, ImageView player, FXMLDocumentController controller) {
        super(gamePane, player, controller);
    }
    
    @Override
    public void spawnEnemy() {
        Random rand = new Random();
        double x = rand.nextDouble() * (getGamePane().getPrefWidth() - 50);
        double y = 0;

        ImageView enemy = new ImageView(new Image(ENEMY_IMAGE));
        enemy.setFitWidth(50);
        enemy.setFitHeight(50);
        enemy.setLayoutX(x);
        enemy.setLayoutY(y);
        getGamePane().getChildren().add(enemy);
        enemies.add(enemy);

        Timeline enemyTimeline = new Timeline(new KeyFrame(Duration.millis(20), e -> {
            enemy.setLayoutY(enemy.getLayoutY() + 3); // Move down
            if (enemy.getLayoutY() > getGamePane().getPrefHeight()) {
                getGamePane().getChildren().remove(enemy);
                enemies.remove(enemy);
            }
        }));
        enemyTimeline.setCycleCount(Timeline.INDEFINITE);
        enemyTimeline.play();
    }
}
