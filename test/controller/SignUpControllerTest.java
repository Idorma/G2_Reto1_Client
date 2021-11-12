/*
 * Esta clase es una clase que comprueba mediante tests, que  todas las 
 * comprobaciones posibles den error o no
 */
package controller;

import application.App;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import org.testfx.matcher.control.LabeledMatchers;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

/**
 *
 * @author Alejandro
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class SignUpControllerTest extends ApplicationTest {

    private TextField txtName;
    private TextField txtUser;
    private TextField txtEmail;
    private TextField txtPasswd;
    private TextField txtPassw2;
    private Alert alert;

    /**
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        new App().start(stage);
    }

    /**
     * Este test valida que no haya nada escrito en los campos de la ventana
     * SignUp
     */
    @Test
    @Ignore
    public void testA_CamposVacios() {
        clickOn("#linkSignIn");
        clickOn("#txtName");
        txtName = lookup("#txtName").query();
        eraseText(txtName.getText().length());
        clickOn("#txtUser");
        txtUser = lookup("#txtUser").query();
        eraseText(txtUser.getText().length());
        clickOn("#txtEmail");
        txtEmail = lookup("#txtEmail").query();
        eraseText(txtEmail.getText().length());
        clickOn("#txtPasswd");
        txtPasswd = lookup("#txtPasswd").query();
        eraseText(txtPasswd.getText().length());
        clickOn("#txtPassw2");
        txtPassw2 = lookup("#txtPassw2").query();
        eraseText(txtPassw2.getText().length());
        verifyThat("#btnSignUp", isDisabled());
    }

    /**
     * Este test valida que no haya nada escrito en el campo txtName de la
     * ventana SignUp
     */
    @Test
    @Ignore
    public void testB_NombreYApellidoVacio() {
        clickOn("#linkSignIn");
        clickOn("#txtName");
        txtName = lookup("#txtName").query();
        eraseText(txtName.getText().length());
        clickOn("#txtUser");
        write("fran");
        clickOn("#txtEmail");
        write("fran@gmail.com");
        clickOn("#txtPasswd");
        write("abcd*1234");
        clickOn("#txtPassw2");
        write("abcd*1234");
        verifyThat("#btnSignUp", isDisabled());
    }

    /**
     * Este test valida que no haya nada escrito en el campo txtUser de la
     * ventana SignUp
     */
    @Test
    @Ignore
    public void testC_UsuarioVacio() {
        clickOn("#linkSignIn");
        clickOn("#txtName");
        write("Manuela Sanabria");
        clickOn("#txtUser");
        txtUser = lookup("#txtUser").query();
        eraseText(txtUser.getText().length());
        clickOn("#txtEmail");
        write("manolita@gmail.com");
        clickOn("#txtPasswd");
        write("abcd*1234");
        clickOn("#txtPassw2");
        write("abcd*1234");
        verifyThat("#btnSignUp", isDisabled());
    }

    /**
     * Este test valida que no haya nada escrito en el campo txtEmail de la
     * ventana SignUp
     */
    @Test
    @Ignore
    public void testD_CorreoVacio() {
        clickOn("#linkSignIn");
        clickOn("#txtName");
        write("Marcelino Gómez");
        clickOn("#txtUser");
        write("marcelino");
        clickOn("#txtEmail");
        txtEmail = lookup("#txtEmail").query();
        eraseText(txtEmail.getText().length());
        clickOn("#txtPasswd");
        write("abcd*1234");
        clickOn("#txtPassw2");
        write("abcd*1234");
        verifyThat("#btnSignUp", isDisabled());
    }

    /**
     * Este test valida que no haya nada escrito en el campo txtPasswd de la
     * ventana SignUp
     */
    @Test
    @Ignore
    public void testE_ContraseñaVacia() {
        clickOn("#linkSignIn");
        clickOn("#txtName");
        write("Cristina Martínez");
        clickOn("#txtUser");
        write("cris");
        clickOn("#txtEmail");
        write("cristina@gmail.com");
        clickOn("#txtPasswd");
        txtPasswd = lookup("#txtPasswd").query();
        eraseText(txtPasswd.getText().length());
        clickOn("#txtPassw2");
        write("abcd*1234");
        verifyThat("#btnSignUp", isDisabled());
    }

    /**
     * Este test valida que no haya nada escrito en el campo txtPassw2 de la
     * ventana SignUp
     */
    @Test
    @Ignore
    public void testF_RepetirContraseñaVacia() {
        clickOn("#linkSignIn");
        clickOn("#txtName");
        write("Guillermo Galán");
        clickOn("#txtUser");
        write("guillermo");
        clickOn("#txtEmail");
        write("guillermo@gmail.com");
        clickOn("#txtPasswd");
        write("abcd*1234");
        clickOn("#txtPassw2");
        txtPassw2 = lookup("#txtPassw2").query();
        eraseText(txtPassw2.getText().length());
        verifyThat("#btnSignUp", isDisabled());
    }

