/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tembakanapa;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class MenuController implements Initializable {

    @FXML
    private ImageView playBtn;
    @FXML
    private ImageView quitBtn;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Tambahkan aksi untuk ImageView sebagai tombol
        quitBtn.setOnMouseClicked(event -> quitApplication());
    }

    @FXML
    public void startGame(MouseEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        FXMLDocumentController controller = loader.getController();

        Stage stage = (Stage) playBtn.getScene().getWindow();
        Scene newScene = new Scene(root);

        stage.setScene(newScene);
        stage.show();

        controller.setFocusOnGamePane();
    }
    
    private void quitApplication() {
        System.exit(0); 
    }
}
