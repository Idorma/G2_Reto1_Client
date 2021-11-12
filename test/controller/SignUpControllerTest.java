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
    public void testD_CorreoVacio() {
        clickOn("#linkSignIn");
        clickOn("#txtName");
        write("Marcelino GÃ³mez");
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
    public void testE_ContrasenaVacia() {
        clickOn("#linkSignIn");
        clickOn("#txtName");
        write("Cristina MartÃ­nez");
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
    public void testF_RepetirContrasenaVacia() {
        clickOn("#linkSignIn");
        clickOn("#txtName");
        write("Guillermo GalÃ¡n");
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

    /**
     * Este test comprueba que no se puedan escribir mÃ¡s de 50 caracteres
     */
    @Test
    public void testG_MasDelMaxNumDeCaractereres() {
        String name = "MarÃ­aIgnacioMarÃ­aIgnacioMarÃ­aIgnacioMarÃ­aIgnacioMarÃ­aIgnacio";

        clickOn("#linkSignIn");
        clickOn("#txtName");
        write(name);
        txtName = lookup("#txtName").query();
        verifyThat(txtName.getText(), hasText(name));
        /*clickOn("#txtUser");
        write("maria");
        clickOn("#txtEmail");
        write("maria@gmail.com");
        clickOn("#txtPasswd");
        write("abcd*1234");
        clickOn("#txtPassw2");
        write("abcd*1234");*/
    }

    /**
     * Este test valida que el usuario haya sido registrado
     */
    @Test
    public void testH_UsuarioYaRegistrado() {
        clickOn("#linkSignIn");
        clickOn("#txtName");
        write("Alejandro GÃ³mez");
        clickOn("#txtUser");
        write("alejandro");
        clickOn("#txtEmail");
        write("agomez@gmail.com");
        clickOn("#txtPasswd");
        write("abcd*1234");
        clickOn("#txtPassw2");
        write("abcd*1234");
        verifyThat("#btnSignUp", isEnabled());
        clickOn("#btnSignUp");
        verifyThat(".alert", NodeMatchers.isVisible());
    }

    /**
     * Este test valida que el correo no cumpla los requerimientos
     */
    @Test
    public void testI_CorreoNoCumpleLosRequerimientos() {
        clickOn("#linkSignIn");
        clickOn("#txtName");
        write("Justo Quintero");
        clickOn("#txtUser");
        write("justo");
        clickOn("#txtEmail");
        write("justo.com");
        clickOn("#txtPasswd");
        write("abcd*1234");
        clickOn("#txtPassw2");
        write("abcd*1234");
        verifyThat("#btnSignUp", isDisabled());
    }

    /**
     * Este test valida que el correo haya sido registrado
     */
    @Test
    public void testJ_CorreoYaRegistrado() {
        clickOn("#linkSignIn");
        clickOn("#txtName");
        write("Alejandro GÃ³mez");
        clickOn("#txtUser");
        write("alejandro");
        clickOn("#txtEmail");
        write("gomezmonfort@hotmail.es");
        clickOn("#txtPasswd");
        write("abcd*1234");
        clickOn("#txtPassw2");
        write("abcd*1234");
        verifyThat("#btnSignUp", isEnabled());
        clickOn("#btnSignUp");
        verifyThat(".alert", NodeMatchers.isVisible());
    }

    /**
     * Este test valida que la contraseÃ±a no cumpla los requerimientos mÃ­nimos
     */
    @Test
    public void testK_ContrasenaNoCumpleLosRequirimientosMÃ­nimos() {
        clickOn("#linkSignIn");
        clickOn("#txtName");
        write("Maica VÃ©lez");
        clickOn("#txtUser");
        write("maica");
        clickOn("#txtEmail");
        write("maica@gmail.com");
        clickOn("#txtPasswd");
        write("maica");
        clickOn("#txtPassw2");
        write("maica");
        verifyThat("#btnSignUp", isDisabled());
    }

    /**
     * Este test valida que la contraseÃ±a no contenga numeros
     */
    @Test
        public void testL_ContrasenaNoContieneNumeros() {
        clickOn("#linkSignIn");
        clickOn("#txtName");
        write("Gorka Etura");
        clickOn("#txtUser");
        write("gorka");
        clickOn("#txtEmail");
        write("maica@gmail.com");
        clickOn("#txtPasswd");
        write("gorkaetu");
        clickOn("#txtPassw2");
        write("gorkaetu");
        verifyThat("#btnSignUp", isDisabled());
    }


}
