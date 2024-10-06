module org.example.laboratoire3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;


    opens org.example.laboratoire3 to javafx.fxml;
    exports org.example.laboratoire3;
}