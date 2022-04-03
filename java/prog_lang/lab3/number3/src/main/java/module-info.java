module number3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens number3 to javafx.fxml;
    exports number3;
}
