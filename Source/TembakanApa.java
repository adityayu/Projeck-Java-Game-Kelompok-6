package tembakanapa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class to launch the JavaFX application
 */
public class TembakanApa extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Muat file FXML menu
            Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root);

            // Atur judul aplikasi dan tampilkan stage
            primaryStage.setTitle("Tembakan Apa Game");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false); // Nonaktifkan pengubahan ukuran window
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args); // Jalankan aplikasi
    }
}
