/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author idoia
 */
public class RegistroController implements Initializable {

    @FXML
    private BorderPane secPane;
    private Label lblCaract;
    private PasswordField txtPasswd;

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("Login_1.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setResizable(false);
            anotherStage.getIcons().add(new Image("/photos/descargas-removebg-preview.png"));
            anotherStage.setTitle("Registro");
            anotherStage.setScene(new Scene(anotherRoot, 600, 400));
            anotherStage.show();

            anotherStage.focusedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean lostFocus, Boolean gainFocus) -> {
                if (lostFocus) {
                    handlePlayAction();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handlePlayAction() {
        lblCaract.setVisible(true);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    
}
