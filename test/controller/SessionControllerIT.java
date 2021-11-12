/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import application.App;
import java.util.concurrent.TimeoutException;
import javafx.stage.Stage;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;

/**
 *
 * @author Idoia Ormaetxea y Alejandro Gomez
 */
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class SessionControllerIT extends ApplicationTest{
    
    @BeforeClass
    public static void setUp() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(App.class);
    }
    
    /**
     * Test de logeo correcto
     */
    @Test
    public void testA_CorrectLogin() {
        clickOn("#textUser");
        write("idoia");
        clickOn("#textPasswd");
        write("abcd*1234");
        verifyThat("#btnLogin", isEnabled());
        clickOn("#btnLogin");
        verifyThat(".alert", NodeMatchers.isVisible());
    }
    /**
     * Test de pulsacion de boton de logOut
     */
    @Test
    public void testB_LogOut() {
        clickOn("#window");
        clickOn("#menuLogOut");
    }
    /**
     * Test de pulsacion de logOut
     */
    @Test
    public void testC_Salir() {
        clickOn("#window");
        clickOn("#menuSalir");
    }
}
