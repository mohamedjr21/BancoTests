package Controladores;

import DAO.Banco;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.irDefault;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class HelloControllerTest {

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
  void testSoloBuscarButton(FxRobot robot) {
    robot.clickOn("#BuscarButton");
    robot.sleep(500);
    assertTrue(robot.lookup("#InicializarTabla").query().isVisible());

  }

  @Test
  void testBuscarButtonAndRecords(FxRobot robot) {
    FxAssert.verifyThat(mainroot, Parent::isVisible);
    TextField nombreIntro = robot.lookup("#NombreIntro").query();
    robot.write("real madrid");
    Button buscarButton = (Button) robot.lookup("#BuscarButton").query();
    robot.clickOn(buscarButton);
    robot.sleep(1000);

    TableView<?> tableView = robot.lookup("#InicializarTabla").query();
    assertTrue(tableView.getItems().size() > 0, "tablaa");

  }

  @Test
  void testBuscarYEditarRegistro(FxRobot robot) {
    robot.clickOn("#BuscarButton");
    robot.sleep(500);

    assertTrue(robot.lookup("#InicializarTabla").query().isVisible());

    robot.clickOn("1");

    robot.clickOn("#editarButton");
    robot.sleep(1000);
    robot.lookup("#companyIdField").query();
    robot.clickOn("#companyIdField");
    robot.eraseText(10);

    robot.write("3");

    robot.clickOn("#botonAceptar");


  }

  @Test
  void TestBorrarRegistros(FxRobot robot) {
    robot.clickOn("#BuscarButton");
    robot.sleep(500);
    assertTrue(robot.lookup("#InicializarTabla").query().isVisible());
    robot.clickOn("1");
    robot.clickOn("#borrarButton");

    robot.sleep(500);

    robot.lookup(".button").queryAll().stream()
        .filter(button -> button instanceof Button && ((Button) button).getText().equals("Aceptar"))
        .findFirst()
        .ifPresent(robot::clickOn);

    robot.sleep(500);

    robot.clickOn("#BuscarButton");
    robot.sleep(500);

  }
  @Test
  void TestAltaCliente(FxRobot robot){
    robot.clickOn("#BuscarButton");
    robot.sleep(500);
    assertTrue(robot.lookup("#InicializarTabla").query().isVisible());
    robot.clickOn("#altaBoton");
    robot.sleep(1000);
    robot.write("4");
    robot.clickOn("#companyIdField");
    robot.write("7");
    robot.clickOn("#conditionField");
    robot.write("murgi");
    robot.clickOn("#jsonValueField");
    robot.write("hola selectividad. hola fuente nuevaa ");

    robot.clickOn("#botonAceptar");
    robot.sleep(500);

    robot.clickOn("#BuscarButton");








  }
}







