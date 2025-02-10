module com.example.bancomfh {
  requires org.junit.jupiter.api;
  requires org.junit.jupiter.engine;
  requires org.junit.platform.commons;
  requires org.apiguardian.api;
  requires javafx.controls;
  requires javafx.fxml;
  requires java.sql;


  opens com.example.bancomfh to javafx.fxml;
  opens modelo to javafx.base;
  exports com.example.bancomfh;
  exports Controladores;
  opens Controladores to javafx.fxml;
}