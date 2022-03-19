module num3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens num3 to javafx.fxml;
    exports num3;
}
