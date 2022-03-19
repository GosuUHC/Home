package tezt;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HelloFX extends Application {

    public void start(Stage primarystage) {
        primarystage.setTitle("Prikol");
        primarystage.centerOnScreen();
        TextField txt1 = new TextField();
        TextField txt2 = new TextField();
        Button arrow_button = new Button("->");
        arrow_button.setMinSize(40, 25);
        txt1.setMaxSize(130, 50);
        txt2.setMaxSize(130, 50);

        arrow_button.setAlignment(Pos.CENTER);
        HBox root = new HBox(20, txt1, arrow_button, txt2);
        root.setPrefSize(400, 400);
        root.setAlignment(Pos.CENTER);
        Scene primaryScene = new Scene(root, 640, 480);

        primarystage.setScene(primaryScene);

        primarystage.show();
        arrow_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (arrow_button.getText() == "->") {
                    txt2.setText(txt1.getText());
                    txt1.setText("");
                    arrow_button.setText("<-");
                } else {
                    txt1.setText(txt2.getText());
                    txt2.setText("");
                    arrow_button.setText("->");
                }

            }

        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
