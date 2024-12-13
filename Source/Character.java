/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tembakanapa;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author NITRO 5
 */
public class Character {
    private AnchorPane gamePane;
    private ImageView player;
    private FXMLDocumentController controller;

    public Character(AnchorPane gamePane, ImageView player, FXMLDocumentController controller){
        this.gamePane = gamePane;
        this.player = player;
        this.controller = controller;
    }

    public AnchorPane getGamePane() {
        return gamePane;
    }

    public void setGamePane(AnchorPane gamePane) {
        this.gamePane = gamePane;
    }

    public ImageView getPlayer() {
        return player;
    }

    public void setPlayer(ImageView player) {
        this.player = player;
    }

    public FXMLDocumentController getController() {
        return controller;
    }

    public void setController(FXMLDocumentController controller) {
        this.controller = controller;
    }
}
