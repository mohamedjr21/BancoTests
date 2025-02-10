package Controladores;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
import java.io.IOException;
import java.sql.SQLException;

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
  void testViewLoads(FxRobot robot) {
    FxAssert.verifyThat(mainroot, Pane::isVisible);
  }


}


