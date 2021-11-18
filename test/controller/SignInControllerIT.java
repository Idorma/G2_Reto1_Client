/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import application.App;
import exceptions.SignInException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;
import org.testfx.api.FxAssert;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import org.testfx.matcher.base.WindowMatchers;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 *
 * @author Jonathan Camacho y Alain Cosgaya
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignInControllerIT extends ApplicationTest {

    private TextField textUser;
    private TextField textPasswd;

    @Override
    public void start(Stage stage) throws Exception {
        new App().start(stage);
    }

    /**
     * método que comprobará si la ventana se inicializa con los campos vacíos.
     */
    
    @Test
    public void testA_CamposVacios() {

        clickOn("#textUser");
        textUser = lookup("#textUser").query();
        eraseText(textUser.getText().length());
        clickOn("#textPasswd");
        textPasswd = lookup("#textPasswd").query();
        eraseText(textPasswd.getText().length());
        verifyThat("#btnLogin", isDisabled());
    }

    /**
     * Este método comprueba las diferentes restricciones que deshabilitan el
     * botón btnLogin.
     *
     * Se comprueba si se ha introducido un usuario correcto pero el campo de
     * contraseña esta vacío.
     *
     * Se comprueba si se ha introducido una contraseña pero el campo de usuario
     * esta vacío.
     *
     * Se comprueba si el usuario y al contraseña son correctas.
     *
     * Se comprueba si la contraseña tiene la longitud requerida y si contiene
     * tanto números como caracteres.
     */
    @Ignore
    @Test
    public void testB_botonLogInDeshabilitado() {
        //rellenamos solo el campo del user, comprobamos y borramos
        clickOn("#textUser");
        write("pepe");
        verifyThat("#btnLogin", isDisabled());
        eraseText(4);
        //rellenamos solo el campo password de forma correcta y lo comprobamos
        clickOn("#textPasswd");
        write("abcd*1234");
        verifyThat("#btnLogin", isDisabled());
        eraseText(9);
        //
        clickOn("#textUser");
        write("rodolfo");
        clickOn("#textPasswd");
        write("1234567");
        verifyThat("#btnLogin", isDisabled());
        eraseText(7);
        //
        clickOn("#textPasswd");
        write("asdfghjk");
        verifyThat("#btnLogin", isDisabled());
        eraseText(1);
        verifyThat("#btnLogin", isDisabled());
    }

    /**
     * Metodo el cual comprueba si el botón btnLogin se habilita cuando un
     * usuario introduce unos parametros correctos.
     */
    @Ignore
    @Test
    public void testC_btnLoginEnabled() {
        //metemos un usuario y una contraseña correcta
        clickOn("#textUser");
        write("pepe");
        clickOn("#textPasswd");
        write("abcd*1234");
        verifyThat("#btnLogin", isEnabled());
    }

    /**
     * Método el cual comprueba que se abre la ventana de SignUp cuando se pulsa
     * el link de registrarse.
     */
    @Ignore
    @Test
    public void testD_OpenSignUpWindow() {
        //comprobamos que podemos entrar a la ventana de registarse
        clickOn("#linkSignIn");
        //FxAssert.verifyThat(window("#Session.fxml"), WindowMatchers.isShowing());
        verifyThat("#paneSignUp", isVisible());
    }

    /**
     * Método en el cual se comprueba si se pueden introducir espacios en los
     * campos de la ventana SignIn.
     */
    @Ignore
    @Test
    public void testE_btnLoginDisable() {
        //metemos un usuario y una contraseña correcta
        clickOn("#textUser");
        write(" ");
        clickOn("#textPasswd");
        write(" ");
        verifyThat("#btnLogin", isDisabled());
    }

    /**
     * Método el cual comprueba si es posible iniciar sesión de forma correcta.
     */
    @Ignore
    @Test
    public void testF_CorrectlogIn() {
        //metemos un usuario y una contraseña correcta
        String nombre = "pepe";
        String password = "abcd*1234";

        clickOn("#textUser");
        write(nombre);
        clickOn("#textPasswd");
        write(password);
        verifyThat("#btnLogin", isEnabled());
        clickOn("#btnLogin");
        verifyThat("#lblUsername", hasText(nombre));
    }

    /**
     * Metodo el cual comprueba que al meter un usuario y contraseña correcta,
     * teniendo el servidor cerrado, salta una excepcion tipo ConnectException.
     */
    @Test
    public void testG_ConnectException() {
        //metemos un usuario y una contraseña correcta
        String nombre = "jonathan";
        String password = "abcd*1234";

        clickOn("#textUser");
        write(nombre);
        clickOn("#textPasswd");
        write(password);
        verifyThat("#btnLogin", isEnabled());
        clickOn("#btnLogin");
        verifyThat("Error al intentar abrir/cerrar la conexion al servidor, intentelo mas tarde", NodeMatchers.isVisible());
    }

    /**
     * Metodo el cual comprueba que al meter un usuario y/o contraseña
     * incorrecta, y el servidor esta abierto, salta una excepcion tipo
     * SignInException.
     */
    @Ignore
    @Test
    public void testH_SignInException() {
        //metemos un usuario y una contraseña incorrecta
        String nombre = "hola";
        String password = "abcd*1234";

        clickOn("#textUser");
        write(nombre);
        clickOn("#textPasswd");
        write(password);
        verifyThat("#btnLogin", isEnabled());
        clickOn("#btnLogin");
        //comprobamos que es la alerta adecuada para este tipo de excepcion
        verifyThat("Los parametros introducidos no corresponden a ningún cliente", NodeMatchers.isVisible());
    }
}
