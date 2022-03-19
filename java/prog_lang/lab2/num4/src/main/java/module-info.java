module num4 {
    requires javafx.controls;
    requires javafx.fxml;

    opens num4 to javafx.fxml;
    exports num4;
}
