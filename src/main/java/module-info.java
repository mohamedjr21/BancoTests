module com.example.bancomfh {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.sql;


  opens com.example.bancomfh to javafx.fxml;
  opens modelo to javafx.base;
  exports com.example.bancomfh;
  exports Controladores;
  opens Controladores to javafx.fxml;
}