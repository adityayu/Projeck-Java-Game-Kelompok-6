/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tembakanapa;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NITRO 5
 */
public class SelesaiController implements Initializable {

    @FXML
    private ImageView restBtn;
    @FXML
    private ImageView pulangBtn;
    @FXML
    private Label point;

    private int score; // Property untuk menyimpan score

    public void setScore(int score) {
        this.score = score;
        updatePointLabel();
    }

    private void updatePointLabel() {
        if (point != null) {
            point.setText("Score Anda: " + score); // Mengupdate teks label
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Optional: Initial setup
    }
    
    @FXML
    private void restartGame(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();

        FXMLDocumentController controller = loader.getController();

        Stage stage = (Stage) restBtn.getScene().getWindow();
        Scene newScene = new Scene(root);

        stage.setScene(newScene);
        stage.show();
        controller.setFocusOnGamePane();
    }

    @FXML
    private void quitGame(MouseEvent event) {
        System.exit(0); // Keluar dari aplikasi
    }
}
