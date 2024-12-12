package warp2;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class FXMLDocumentController {

    @FXML
    private Rectangle player1Box;
    @FXML
    private Rectangle player2Box;
    @FXML
    private Label player1ScoreLabel;
    @FXML
    private Label player2ScoreLabel;

    @FXML
    private Circle bullet1; // Peluru Pemain 1
    @FXML
    private Circle bullet2; // Peluru Pemain 2

    private int player1Score = 0;
    private int player2Score = 0;

    private Timeline bullet1Timeline; // Timeline untuk pergerakan peluru pemain 1
    private Timeline bullet2Timeline; // Timeline untuk pergerakan peluru pemain 2

    // Inisialisasi pengaturan keyboard
    @FXML
    private void initialize() {
        player1Box.setFocusTraversable(true);
        player2Box.setFocusTraversable(true);

        // Fokus pada player1Box agar bisa menerima input keyboard
        player1Box.requestFocus();

        // Setup untuk timeline peluru
        setupBulletMovement();
    }

    // Setup gerakan peluru dengan Timeline
    private void setupBulletMovement() {
        // Timeline untuk peluru Pemain 1
        bullet1Timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> moveBullet(bullet1, 5, 0))); // Bergerak ke kanan
        bullet1Timeline.setCycleCount(Timeline.INDEFINITE);

        // Timeline untuk peluru Pemain 2
        bullet2Timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> moveBullet(bullet2, -5, 0))); // Bergerak ke kiri
        bullet2Timeline.setCycleCount(Timeline.INDEFINITE);
    }

    // Handle pergerakan pemain berdasarkan input keyboard
    @FXML
    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case W: // Pemain 1 ke atas
                movePlayer(player1Box, 0, -10);
                break;
            case S: // Pemain 1 ke bawah
                movePlayer(player1Box, 0, 10);
                break;
            case UP: // Pemain 2 ke atas
                movePlayer(player2Box, 0, -10);
                break;
            case DOWN: // Pemain 2 ke bawah
                movePlayer(player2Box, 0, 10);
                break;
            case SPACE: // Pemain 1 menembak
                shootBullet(player1Box, bullet1);
                break;
            case ENTER: // Pemain 2 menembak
                shootBullet(player2Box, bullet2);
                break;
        }
    }

    // Memindahkan pemain berdasarkan pergerakan x dan y
    private void movePlayer(Rectangle player, int dx, int dy) {
        double newX = player.getLayoutX() + dx;
        double newY = player.getLayoutY() + dy;

        // Batasi pergerakan agar tidak keluar layar
        if (newX >= 0 && newX <= player.getParent().getLayoutBounds().getWidth() - player.getWidth()) {
            player.setLayoutX(newX);
        }
        if (newY >= 0 && newY <= player.getParent().getLayoutBounds().getHeight() - player.getHeight()) {
            player.setLayoutY(newY);
        }
    }

    // Menembak peluru dari posisi pemain dan bergerak ke arah horizontal
   private void shootBullet(Rectangle player, Circle bullet) {
    if (!bullet.isVisible()) {
        // Menempatkan peluru di depan kotak pemain
        if (player == player1Box) {
            // Untuk player 1, peluru muncul di depan kotak pemain (ke kanan)
            bullet.setLayoutX(player.getLayoutX() + player.getWidth());
        } else {
            // Untuk player 2, peluru muncul di depan kotak pemain (ke kiri)
            bullet.setLayoutX(player.getLayoutX() - bullet.getRadius());
        }

        // Menempatkan peluru pada posisi vertikal tengah kotak pemain
        bullet.setLayoutY(player.getLayoutY() + player.getHeight() / 2 - bullet.getRadius());
        bullet.setVisible(true);

        // Mulai gerakan peluru
        if (player == player1Box) {
            bullet1Timeline.play();
        } else {
            bullet2Timeline.play();
        }
    }
}


    // Gerakkan peluru ke arah horizontal
    private void moveBullet(Circle bullet, double dx, double dy) {
        double newX = bullet.getLayoutX() + dx;  // Kecepatan pergerakan horizontal
        double newY = bullet.getLayoutY() + dy;  // Tidak ada pergerakan vertikal

        // Jika peluru keluar layar, sembunyikan dan hentikan gerakannya
        if (newX < 0 || newX > bullet.getParent().getLayoutBounds().getWidth()) {
            bullet.setVisible(false);
            if (bullet == bullet1) {
                bullet1Timeline.stop();
            } else {
                bullet2Timeline.stop();
            }
        } else {
            bullet.setLayoutX(newX);
            bullet.setLayoutY(newY);
        }
    }
}
