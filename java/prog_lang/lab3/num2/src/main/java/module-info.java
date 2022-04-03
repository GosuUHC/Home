module num2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens num2 to javafx.fxml;
    exports num2;
}
