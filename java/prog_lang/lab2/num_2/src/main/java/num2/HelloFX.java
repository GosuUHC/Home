package num2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloFX extends Application {

    public void start(Stage primarystage) {
        primarystage.setTitle("Prikol2");
        primarystage.centerOnScreen();
        TextField txtwidget1 = new TextField();
        TextField txtwidget2 = new TextField();
        TextField txtwidget3 = new TextField();
        txtwidget1.setOpacity(0);
        txtwidget2.setOpacity(0);
        txtwidget3.setOpacity(0);
        txtwidget1.setPrefSize(50, 60);
        txtwidget2.setPrefSize(50, 60);
        txtwidget3.setPrefSize(50, 60);

        CheckBox checkBox1 = new CheckBox("Action 1");
        CheckBox checkBox2 = new CheckBox("Action 2");
        CheckBox checkBox3 = new CheckBox("Action 3");
        checkBox1.setPrefSize(110, 20);
        checkBox2.setPrefSize(110, 20);
        checkBox3.setPrefSize(110, 20);
        VBox checBoxs = new VBox(10, checkBox1, checkBox2, checkBox3);
        checBoxs.setStyle("-fx-font: 16 arial;");
        checBoxs.setAlignment(Pos.TOP_CENTER);

        VBox widgets = new VBox(15, txtwidget1, txtwidget2, txtwidget3);
        widgets.setStyle("-fx-font: 20 arial;");
        VBox widsandchec = new VBox(35, checBoxs, widgets);
        widsandchec.setAlignment(Pos.CENTER);
        Scene primaryScene = new Scene(widsandchec, 640, 480);
        primarystage.setScene(primaryScene);

        primarystage.show();
        checkBox1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent1) {
                if (checkBox1.isSelected()) {
                    txtwidget1.setOpacity(100);
                    txtwidget1.setText("Hello!");
                } else {
                    txtwidget1.setOpacity(0);
                }

            }

        });
        checkBox2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent2) {
                if (checkBox2.isSelected()) {
                    txtwidget2.setOpacity(100);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    txtwidget2.setText(dateFormat.format(new java.util.Date()));
                } else {
                    txtwidget2.setOpacity(0);
                }
            }

        });
        checkBox3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent3) {
                if (checkBox3.isSelected()) {// что-то сделать
                    txtwidget3.setOpacity(100);
                    txtwidget3.setText("Goodbye!");
                    ;
                } else {
                    txtwidget3.setOpacity(0);
                }

            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}