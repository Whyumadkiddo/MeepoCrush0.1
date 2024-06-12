package com.example.meepocrush;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.sound.sampled.Clip;
import java.io.File;
import java.nio.file.Paths;
import java.util.Random;
public class MCGController {
    @FXML
    private ImageView meeepo1;
    @FXML
    private Image image;
    @FXML
    private Button button;
    @FXML
    private Timeline timer;

    private MediaPlayer mediaPlayer;

    public void mepoOff(MouseEvent event) {
        Node source = (Node) event.getSource();
        source.setVisible(false);

        Random random = new Random();
        int delayoff = random.nextInt(3) + 1;

        PauseTransition pause = new PauseTransition(Duration.seconds(delayoff));

        PauseTransition pauseBeforeHide = new PauseTransition(Duration.seconds(2));

        pauseBeforeHide.setOnFinished(f -> {
            source.setVisible(false);
        });


        pauseBeforeHide.play();

        pause.setOnFinished(e -> {
            source.setVisible(true);
            String sound = "C:\\Users\\vladm\\IdeaProjects\\MeepoCrush\\src\\main\\resources\\sounds\\MeepoSound.mpeg";
            Media h = new Media(Paths.get(sound).toUri().toString());
            mediaPlayer = new MediaPlayer(h);
            mediaPlayer.play();
        });


        pause.play();
    }
}
