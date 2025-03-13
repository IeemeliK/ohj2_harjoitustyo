module com.github.ieemelik.harjoitustyo {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;

  opens com.github.ieemelik.harjoitustyo to javafx.fxml;
  exports com.github.ieemelik.harjoitustyo;
}