/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tembakanapa;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author NITRO 5
 */
public class Player extends Character{
    private AnchorPane gamePane;
    private ImageView player;
    private FXMLDocumentController controller;
    
    public Player(AnchorPane gamePane, ImageView player, FXMLDocumentController controller){
        super(gamePane, player, controller);
    }
    
    public void movePlayer(int dx, int dy) {
        double newX = getPlayer().getLayoutX() + dx;
        double newY = getPlayer().getLayoutY() + dy;

        if (newX >= 0 && newX <= getGamePane().getPrefWidth() - getPlayer().getFitWidth()) {
            getPlayer().setLayoutX(newX);
        }
        if (newY >= 0 && newY <= getGamePane().getPrefHeight() - getPlayer().getFitHeight()) {
            getPlayer().setLayoutY(newY);
        }
    }
    
    public void playerCollision() {
        for (ImageView enemy : getController().myEnemy.enemies) {
            if (getController().myBullet.checkCollision(getPlayer(), enemy)) {
                getGamePane().getChildren().remove(getPlayer());
                getGamePane().getChildren().remove(enemy);
                getController().myEnemy.enemies.remove(enemy);
                
            // Handle game over or any necessary action after collision   
            try {
                getController().endGame();
            } catch (Exception e) {
                e.printStackTrace();
            }
                break;
            }
        }
    }
}
