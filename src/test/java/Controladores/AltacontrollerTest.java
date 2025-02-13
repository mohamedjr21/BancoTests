package Controladores;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
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

  private Pane mainroot;
  private Stage mainstage;

  @BeforeAll
  public static void initJavaFX() {
  }

  @Start
  public void start(Stage stage) throws IOException {
    mainroot = FXMLLoader.load(getClass().getResource("/com/example/bancomfh/hello-view.fxml"));
    mainstage = stage;
    stage.setScene(new Scene(mainroot));
    stage.show();
    stage.toFront();
  }

  @Test
  void testVista(FxRobot robot) {
    FxAssert.verifyThat(mainroot, Pane::isVisible);
  }



  @Test
  void TestAltaCliente1Campo(FxRobot robot) {
    robot.clickOn("#BuscarButton");
    robot.sleep(500);
    assertTrue(robot.lookup("#InicializarTabla").query().isVisible());

    robot.clickOn("#altaBoton");
    robot.sleep(1000);

    robot.write("0jdsahfsdhajshfhsdhfsdfdsf");
    robot.clickOn("#companyIdField");

    robot.clickOn("#botonAceptar");
    robot.sleep(500);
  }


  @Test
  void TestAltaCliente2Campos(FxRobot robot) {
    robot.clickOn("#BuscarButton");
    robot.sleep(500);
    assertTrue(robot.lookup("#InicializarTabla").query().isVisible());

    robot.clickOn("#altaBoton");
    robot.sleep(1000);

    robot.clickOn("#companyIdField");
    robot.write("kjfsdafkjsdjfjasdf");
    robot.clickOn("#conditionField");
    robot.write("murgi");

    robot.clickOn("#botonAceptar");
    robot.sleep(500);
  }

  @Test
  void TestAltaCliente3Campos(FxRobot robot) {
    robot.clickOn("#BuscarButton");
    robot.sleep(500);
    assertTrue(robot.lookup("#InicializarTabla").query().isVisible());

    robot.clickOn("#altaBoton");
    robot.sleep(1000);

    robot.write("0jdsahfsdhajshfhsdhfsdfdsf");
    robot.clickOn("#companyIdField");
    robot.write("kjfsdafkjsdjfjasdf");
    robot.clickOn("#conditionField");
    robot.write("murgi");


    robot.clickOn("#botonAceptar");
    robot.sleep(500);
  }

  @Test
  void TestAltaCliente4CamposNoSeActivaBUTON(FxRobot robot) {
    robot.clickOn("#BuscarButton");
    robot.sleep(500);
    assertTrue(robot.lookup("#InicializarTabla").query().isVisible());

    robot.clickOn("#altaBoton");
    robot.sleep(1000);

    robot.write("0jdsahfsdhajshfhsdhfsdfdsf");
    robot.clickOn("#companyIdField");
    robot.write("kjfsdafkjsdjfjasdf");
    robot.clickOn("#conditionField");
    robot.write("murgi");
    robot.clickOn("#jsonValueField");
    robot.write("hola selectividad. hola fuente nuevaa ");

    robot.clickOn("#botonAceptar");
    robot.sleep(500);
    robot.clickOn("#BuscarButton");
  }




  @Test
  void TestAltaCliente(FxRobot robot) {
    robot.clickOn("#BuscarButton");
    robot.sleep(500);
    assertTrue(robot.lookup("#InicializarTabla").query().isVisible());

    robot.clickOn("#altaBoton");
    robot.sleep(1000);

    robot.write("6");
    robot.clickOn("#companyIdField");
    robot.write("1");
    robot.clickOn("#conditionField");
    robot.write("Almeria fc");
    robot.clickOn("#jsonValueField");
    robot.write("hola Almeria fc ");

    robot.clickOn("#botonAceptar");

    robot.sleep(500);
    robot.clickOn("#BuscarButton");
    robot.clickOn("6");
    robot.sleep(1000);



  }



}