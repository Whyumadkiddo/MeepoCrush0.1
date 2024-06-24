package com.example.meepocrush;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MCGController {
    @FXML
    private Label countNum;
    @FXML
    private ImageView meeepo1;
    @FXML
    private ImageView meeepo2;
    @FXML
    private ImageView meeepo3;
    @FXML
    private ImageView meeepo4;
    @FXML
    private ImageView meeepo5;
    @FXML
    private ImageView meeepo6;
    @FXML
    private ImageView meeepo7;

    private List<Node> sources;
    @FXML
    private Image image;
    @FXML
    private Button button;
    @FXML
    private Timeline timer;
    private Node source;
    private MediaPlayer mediaPlayer;
    public int count;
    private Timeline gameTimer;
    @FXML
    private Label endTimer;
    private Timeline timerTimeline;
    @FXML
    private AnchorPane root;
    private boolean isWindowShow = true;
    private boolean windowClosed = false;
    private Stage stage;


    public void initialize(){

        sources = new ArrayList<>();
        sources.add(meeepo1);
        sources.add(meeepo2);
        sources.add(meeepo3);
        sources.add(meeepo4);
        sources.add(meeepo5);
        sources.add(meeepo6);
        sources.add(meeepo7);
        meeepo1.setVisible(false);
        meeepo2.setVisible(false);
        meeepo3.setVisible(false);
        meeepo4.setVisible(false);
        meeepo5.setVisible(false);
        meeepo6.setVisible(false);
        meeepo7.setVisible(false);
        timerTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            int remainingSeconds = (int) ((int) gameTimer.getCycleDuration().toSeconds() - gameTimer.getCurrentTime().toSeconds());
            endTimer.setText("" + remainingSeconds);
        }));
        timerTimeline.setCycleCount(Timeline.INDEFINITE);
        timerTimeline.play();

        gameTimer = new Timeline(
                new KeyFrame(Duration.seconds(60), event -> {
                    gameTimer.stop();
                    try {
                        showGameOverDialog(count);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    timerTimeline.stop();
                })
        );
        gameTimer.setCycleCount(1);
        gameTimer.play();

        for (Node source : sources) {
            setupVisibilityCycle(source);
        }


    }

    private void setupVisibilityCycle(Node source) {

            Random random = new Random();
            int delayoff = random.nextInt(3) + 2;
            int delayonn = random.nextInt(4) + 5;

            PauseTransition meepoOn = new PauseTransition(Duration.seconds(delayonn));
            PauseTransition meepoOf = new PauseTransition(Duration.seconds(delayoff));

            meepoOf.setOnFinished(f -> {
                source.setVisible(false);
                meepoOn.play();
            });
            meepoOn.setOnFinished(e -> {
                if(isWindowShow) {
                    source.setVisible(true);
                    String sound = "C:\\Users\\vladm\\IdeaProjects\\MeepoCrush\\src\\main\\resources\\sounds\\MeepoSound.mpeg";
                    Media h = new Media(Paths.get(sound).toUri().toString());
                    mediaPlayer = new MediaPlayer(h);
                    mediaPlayer.play();
                    setupVisibilityCycle(source);
                }
            });
            meepoOf.play();

    }

    public void showGameOverDialog(int count) throws IOException {
        isWindowShow = false;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MCR.fxml"));
        Parent root = loader.load();

        MCRController controller = loader.getController();
        controller.setResult(count);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        ((Stage) countNum.getScene().getWindow()).hide();

        stage.show();
    }
    public void mepoOff(MouseEvent mouseEvent) {
        Node source = (Node) mouseEvent.getSource();
        source.setVisible(false);
        count += 100;
        countNum.setText("" + count);
        setupVisibilityCycle(source);
    }
}
