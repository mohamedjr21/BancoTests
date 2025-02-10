package Controladores;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.matcher.control.LabeledMatchers;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class AltacontrollerTest {

  Pane mainroot;
  Stage mainstage;

  @Start
  public void start(Stage stage) throws IOException {
    mainroot = (Pane) FXMLLoader.load(getClass().getResource("/com/example/bancomfh/clienteAlta.fxml"));
    mainstage = stage;
    stage.setScene(new Scene(mainroot));
    stage.show();
    stage.toFront();
  }

  /**
   * @param robot - Will be injected by the test runner.
   */
  @Test
  void al_pulsar_boton_se_abre_una_nueva_ventana(FxRobot robot) {

    robot.clickOn("#altaBoton");
    robot.sleep(1000);
    FxAssert.verifyThat(robot.window("registro"), WindowMatchers.isShowing());
    robot.targetWindow("registro");
    FxAssert.verifyThat("#registro", LabeledMatchers.hasText("NUEVO REGISTRO"));

  }



  @Test
  void initialize() {
  }

  @Test
  void setControladorPrincipal() {
  }

  @Test
  void limpiar() {
  }

  @Test
  void cargarDatos() {
  }

  @Test
  void butonAceptar() {
  }

  @Test
  void butonCancelar() {
  }
}