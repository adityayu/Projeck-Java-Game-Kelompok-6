package tembakanapa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TembakanApa extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Buat instance FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));

        // Muat file FXML
        Parent root = loader.load();

        // Dapatkan controller dari FXMLLoader
        FXMLDocumentController controller = loader.getController();

        // Buat scene
        Scene scene = new Scene(root);

        // Atur judul dan tampilkan
        stage.setTitle("Shooter Game");
        stage.setScene(scene);
        stage.show();

        // Tangkap event keyboard dan hubungkan ke controller
        scene.setOnKeyPressed(controller::handleKeyPress);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
