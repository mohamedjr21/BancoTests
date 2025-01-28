package Controladores;

import DAO.Conexiondb;
import modelo.irDefault;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class Altacontroller {

  @FXML
  private TextField idField;
  @FXML
  private TextField companyIdField;
  @FXML
  private TextField conditionField;
  @FXML
  private TextField jsonValueField;
  @FXML
  private Label registro;
  @FXML
  private Button botonAceptar;

  private final BooleanProperty camposValidos = new SimpleBooleanProperty(false);
  private irDefault guardarpEdit;
  private HelloController controladorPrincipal;
  private boolean editando = false;

  @FXML
  public void initialize() {
    idField.textProperty().addListener((obs, old, nuevo) -> validarCampos());
    companyIdField.textProperty().addListener((obs, old, nuevo) -> validarCampos());
    conditionField.textProperty().addListener((obs, old, nuevo) -> validarCampos());
    jsonValueField.textProperty().addListener((obs, old, nuevo) -> validarCampos());

    if (botonAceptar != null) {
      botonAceptar.disableProperty().bind(camposValidos.not());
    }
  }
  private void validarCampos() {
    boolean esValido = false;
    try {
      if (!idField.getText().trim().isEmpty() &&
          !companyIdField.getText().trim().isEmpty() &&
          !conditionField.getText().trim().isEmpty() &&
          !jsonValueField.getText().trim().isEmpty()) {

        Integer.parseInt(idField.getText().trim());
        Integer.parseInt(companyIdField.getText().trim());
        esValido = true;
      }
    } catch (NumberFormatException e) {
      esValido = false;
    }
    camposValidos.set(esValido);
  }

  public void setControladorPrincipal(HelloController controlador) {
    this.controladorPrincipal = controlador;
  }
  private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
    Alert alert = new Alert(tipo);
    alert.setTitle(tipo == Alert.AlertType.ERROR ? "Error" : "Éxito");
    alert.setContentText(mensaje);
    alert.showAndWait();
  }

  private void cerrarVentana() {
    Stage stage = (Stage) idField.getScene().getWindow();
    if (stage != null) {
      stage.close();
    }
  }
  public void limpiar() {
    idField.clear();
    companyIdField.clear();
    conditionField.clear();
    jsonValueField.clear();

    idField.setEditable(true);
    registro.setText("NUEVO REGISTRO");
    editando = false;
    guardarpEdit = null;
    validarCampos();
  }
  public void cargarDatos(irDefault registro) {
    idField.setText(String.valueOf(registro.getId()));
    companyIdField.setText(String.valueOf(registro.getFieldId()));
    conditionField.setText(registro.getCondition());
    jsonValueField.setText(registro.getJsonValue());

    idField.setEditable(false);
    this.registro.setText("EDITANDO REGISTRO");
    editando = true;
    guardarpEdit = registro;
    validarCampos();
  }
  public void ButonAceptar(ActionEvent actionEvent) {
    try {
      int id = Integer.parseInt(idField.getText());
      int fieldId = Integer.parseInt(companyIdField.getText());
      String condition = conditionField.getText();
      String jsonValue = jsonValueField.getText();

      irDefault registro = new irDefault(id, fieldId, condition, jsonValue);

      Thread miHilo = new Thread(() -> {
        String sql = editando
            ? "UPDATE ir_default SET field_id=?, condition=?, json_value=? WHERE id=?"
            : "INSERT INTO ir_default (id, field_id, condition, json_value) VALUES (?, ?, ?, ?)";

        try (Connection conexion = Conexiondb.getConnection();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

          if (editando) {
            stmt.setInt(1, registro.getFieldId());
            stmt.setString(2, registro.getCondition());
            stmt.setString(3, registro.getJsonValue());
            stmt.setInt(4, registro.getId());
          } else {
            stmt.setInt(1, registro.getId());
            stmt.setInt(2, registro.getFieldId());
            stmt.setString(3, registro.getCondition());
            stmt.setString(4, registro.getJsonValue());
          }

          stmt.executeUpdate();

          Platform.runLater(() -> {
            try {
              controladorPrincipal.actualizarTabla();
            } catch (SQLException elofrs) {
              throw new RuntimeException(elofrs);
            }
            cerrarVentana();
          });
        } catch (SQLException e) {
          Platform.runLater(() ->
              mostrarAlerta("Ha surgido un error: " + e.getMessage(), Alert.AlertType.ERROR)
          );
        }
      });

      miHilo.start();

    } catch (NumberFormatException e) {
      mostrarAlerta("Los campos Id y id empresa son obligatorios. (son números)", Alert.AlertType.ERROR);
    }
  }
  public void ButonCancelar(ActionEvent actionEvent) {
    cerrarVentana();

  }
}
