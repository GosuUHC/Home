module lab4 {
    requires javafx.controls;
    requires javafx.fxml;

    opens lab4 to javafx.fxml;
    exports lab4;
}
