module num5 {
    requires javafx.controls;
    requires javafx.fxml;

    opens num5 to javafx.fxml;
    exports num5;
}
