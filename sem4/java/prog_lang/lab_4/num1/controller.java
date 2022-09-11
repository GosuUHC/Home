package lab_4.num1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class controller {
    private String firstNText = "";
    private String whatText = "";
    private String secondNText = "";
    private Net net = new Net();
    @FXML
    Button plus, minus, times, div, equals;

    @FXML
    Button zero, one, two, three, four, five, six, seven, eight, nine;

    @FXML
    Button clear;
    
    @FXML
    Label firstN, What, secondN;

    boolean issecondnumber = false;
    boolean firstNumberEntered = false;
    boolean WhatEntered = false;
    boolean secondNumberEntered = false;
    boolean isFirstLetter = true;
    double result = 0;

    @FXML
    public void InsertNumber(ActionEvent event) {
        if (!issecondnumber) {
            Button tempB = (Button) event.getSource();
            firstNText += tempB.getText();
            firstN.setText(firstNText);
            firstNumberEntered = true;
        } else {
            Button tempB = (Button) event.getSource();
            secondNText += tempB.getText();
            secondN.setText(secondNText);
            secondNumberEntered = true;
        }
    }

    public void sendData() {
        String[] labelTexts = new String[3];
        labelTexts[0] = firstNText;
        labelTexts[1] = whatText;
        labelTexts[2] = secondNText;
        net.sendToServer(labelTexts);
    }

    @FXML
    public void InsertWhatText(ActionEvent event) {
        if (firstNumberEntered) {
            issecondnumber = true;
            Button tempB = (Button) event.getSource();
            whatText = tempB.getText();
            What.setText(whatText);
            WhatEntered = true;
        }
    }

    public void Refresh(ActionEvent event) {
        issecondnumber = false;
        firstNumberEntered = false;
        WhatEntered = false;
        secondNumberEntered = false;
        firstNText = "";
        whatText = "";
        secondNText = "";
        firstN.setText("FirstN");
        secondN.setText("SecondN");
        What.setText("Operator");
        secondN.setOpacity(100);
        firstN.setOpacity(100);
    }

    @FXML
    public void DisplayResult(ActionEvent event) {
        if (firstNumberEntered && WhatEntered && secondNumberEntered) {
            sendData();

            secondN.setOpacity(0);
            firstN.setOpacity(0);
            String res = "";
            try {
                res = net.receiveFromServ();
            } catch (Exception e) {
                e.printStackTrace();
            }
            What.setText(res);
        } else {
            What.setText("Seg fault");
        }
        firstNumberEntered = false;
        WhatEntered = false;
        secondNumberEntered = false;
    }

}
