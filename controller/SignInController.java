/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.User;
import exceptions.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import static logic.UiLogicFactory.getUiImplem;


/**
 *
 * @author idoia
 */
public class SignInController implements Initializable {

    private boolean exception = false;

    @FXML
    private TextField textUser;

    @FXML
    private PasswordField textPasswd;

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblCaract;

    @FXML
    private Label lblNum;
    
    private final int max = 50;

    @FXML
    private void eventKey(KeyEvent event) {
        Object evt = event.getSource();

        if (evt.equals(textUser)) {
            if (event.getCharacter().equals(" ")) {
                event.consume();
            }
        }
        if (evt.equals(textPasswd)) {
            if (event.getCharacter().equals(" ")) {
                event.consume();
            }
        }
        if (textUser.getText().length() >= max) {
            event.consume();
        }
        if (textPasswd.getText().length() >= max) {
            event.consume();
        }
    }

    @FXML
    private void buttonEventSignIn(ActionEvent event) throws IOException, PasswordLengthException, PasswordNumException {

        try {
            User user = new User();
            user.setUsername(textUser.getText());
            user.setPassword(textPasswd.getText());
            user = getUiImplem().signIn(user);
            
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("/view/Session.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setResizable(false);
            anotherStage.getIcons().add(new Image("/photos/descargas-removebg-preview.png"));
            anotherStage.setTitle("Session");
            anotherStage.setScene(new Scene(anotherRoot));
            anotherStage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();
        

        } catch (IOException e1) {
            e1.printStackTrace();
            //LOGGER
        } catch (ConnectException | SignInException | UpdateException ex) {
           Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
           alert.show();
        }
    }

    @FXML
    private void buttonEvent(ActionEvent event) throws IOException {
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("/view/SignUp.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setResizable(false);
            anotherStage.getIcons().add(new Image("/photos/descargas-removebg-preview.png"));
            anotherStage.setTitle("SignUp");
            anotherStage.setScene(new Scene(anotherRoot));
            anotherStage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
            //LOGGER
        }
    }

    private void passwdTextCaractValidation() {
        textPasswd.textProperty().addListener((ov, oldV, newV) -> {
            if (!textPasswd.getText().equals("")) {
                try {
                    String passwd = textPasswd.getText();
                    validarMinCaractPasswdPattern(passwd);
                    lblCaract.setVisible(false);
                } catch (PasswordLengthException e) {
                    lblCaract.setVisible(true);
                }
            } else {
                lblCaract.setVisible(false);
            }
        });
    }

    private void passwdTextNumValidation() {
        textPasswd.textProperty().addListener((ov, oldV, newV) -> {
            if (!textPasswd.getText().equals("")) {
                try {
                    String passwd = textPasswd.getText();
                    validarNumPasswdPattern(passwd);
                    lblNum.setVisible(false);
                } catch (PasswordNumException e) {
                    lblNum.setVisible(true);
                }
            } else {
                lblNum.setVisible(false);
            }
        });
    }

    private void reportedFields() {
        btnLogin.disableProperty().bind(
                textUser.textProperty().isEmpty().or(
                        textPasswd.textProperty().isEmpty()).or(
                        lblCaract.visibleProperty().or(
                                lblNum.visibleProperty()
                        )
                )
        );
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        passwdTextCaractValidation();
        passwdTextNumValidation();
        reportedFields();
    }

    public void validarMinCaractPasswdPattern(String passwd) throws PasswordLengthException {
        String regex = "^(.+){8,50}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(passwd);

        if (!matcher.matches()) {
            throw new PasswordLengthException(lblCaract.getText());
        }

    }

    public void validarNumPasswdPattern(String passwd) throws PasswordNumException {

        String regex = ".*\\d.*";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(passwd);
        if (!matcher.matches()) {
            throw new PasswordNumException(lblNum.getText());
        }
    }

}
