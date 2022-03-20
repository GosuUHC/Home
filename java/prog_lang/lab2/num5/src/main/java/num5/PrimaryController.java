package num5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class PrimaryController {
    private String[] colours = new String[3];
    private int count = 0;

    @FXML
    RadioButton colourButton1 = new RadioButton();

    @FXML
    RadioButton colourButton2 = new RadioButton();

    @FXML
    RadioButton colourButton3 = new RadioButton();

    @FXML
    RadioButton colourButton4 = new RadioButton();

    @FXML
    RadioButton colourButton5 = new RadioButton();

    @FXML
    Button drawButton = new Button();

    @FXML
    Label drawcolour1 = new Label();

    @FXML
    Label drawcolour2 = new Label();

    @FXML
    Label drawcolour3 = new Label();

    @FXML
    Button updButton = new Button();

    @FXML
    public void drawFlag() {
        drawcolour1.setStyle(colours[0]);
        drawcolour2.setStyle(colours[1]);
        drawcolour3.setStyle(colours[2]);
        System.out.println("No");
    }

    @FXML
    public void colBtnOnAction(ActionEvent event) {
        RadioButton tempB = (RadioButton) event.getSource();
        colours[count] = (String) "-fx-background-color: " + (String) tempB.getText() + (String) ";";
        System.out.println(colours[count]);
        count++;
        Checkcount();
    }

    @FXML
    public void Update() {
        count = 0;
        colourButton1.setDisable(false);
        colourButton2.setDisable(false);
        colourButton3.setDisable(false);
        colourButton4.setDisable(false);
        colourButton5.setDisable(false);
        colourButton1.setSelected(false);
        colourButton2.setSelected(false);
        colourButton3.setSelected(false);
        colourButton4.setSelected(false);
        colourButton5.setSelected(false);
        drawButton.setDisable(true);

    }

    public void Checkcount() {
        if (count >= 3) {

            drawButton.setDisable(false);
            colourButton1.setDisable(true);
            colourButton2.setDisable(true);
            colourButton3.setDisable(true);
            colourButton4.setDisable(true);
            colourButton5.setDisable(true);
        }

    }
}
