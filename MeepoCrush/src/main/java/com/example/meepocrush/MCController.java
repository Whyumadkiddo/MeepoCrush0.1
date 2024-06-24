package com.example.meepocrush;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MCController {
    @FXML
    private Label welcomeText;
    private Object event;


    @FXML
    private Button button;



    @FXML
    private void PlayButton(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MCG.fxml"));
        Parent root = (Parent) loader.load();

        Scene playScene = new Scene(root);

        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        primaryStage.setScene(playScene);

        primaryStage.setMaximized(true);

        primaryStage.show();


    }
    @FXML
    private void ExitButton(ActionEvent event){
        System.exit(0);
    }
}