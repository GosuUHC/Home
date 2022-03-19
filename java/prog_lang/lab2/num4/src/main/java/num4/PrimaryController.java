package num4;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PrimaryController {
    private String firstNText = "";
    private String whatText = "";
    private String secondNText = "";
    @FXML
    Button plus = new Button("+");
    @FXML
    Button minus = new Button("-");
    @FXML
    Button times = new Button("*");
    @FXML
    Button div = new Button("/");
    @FXML
    Button dot = new Button(".");
    @FXML
    Button equals = new Button("=");
    @FXML
    Button zero = new Button("0");
    @FXML
    Button one = new Button("1");
    @FXML
    Button two = new Button("2");
    @FXML
    Button three = new Button("3");
    @FXML
    Button four = new Button("4");
    @FXML
    Button five = new Button("5");
    @FXML
    Button six = new Button("6");
    @FXML
    Button seven = new Button("7");
    @FXML
    Button eight = new Button("8");
    @FXML
    Button nine = new Button("9");
    @FXML
    Button clear = new Button("C");
    @FXML
    Label firstN = new Label();
    @FXML
    Label What = new Label();
    @FXML
    Label secondN = new Label();

    boolean issecondnumber = false;
    boolean firstNumberEntered = false;
    boolean WhatEntered = false;
    boolean secondNumberEntered = false;
    int result = 0;
    @FXML
    public void InsertNumber(ActionEvent event){
        if(!issecondnumber){
            Button tempB = (Button) event.getSource();
            firstNText += tempB.getText();
            firstN.setText(firstNText);
            firstNumberEntered = true;
        }
        else{
            Button tempB = (Button) event.getSource();
            secondNText += tempB.getText();
            secondN.setText(secondNText);
            secondNumberEntered = true;
        }
    }

    @FXML
    public void InsertWhatText(ActionEvent event){
        issecondnumber = true;
        Button tempB = (Button) event.getSource();
        whatText = tempB.getText();
        What.setText(whatText);
        WhatEntered = true;
    }

    public void Refresh(ActionEvent event){
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
    public void DisplayResult(ActionEvent event){
        if(firstNumberEntered && WhatEntered && secondNumberEntered){
            int firstNumber = Integer.valueOf(firstN.getText());
            int secondNumber = Integer.valueOf(secondN.getText());
            
            if(What.getText()==plus.getText()){
                result = firstNumber + secondNumber;
            }
            else if(What.getText()==minus.getText()){
                result = firstNumber - secondNumber;
            }
            else if(What.getText()==times.getText()){
                result = firstNumber * secondNumber;
            }   
            else if(What.getText()==div.getText()){
                result = firstNumber / secondNumber;
            }
            
            secondN.setOpacity(0);
            firstN.setOpacity(0);
            What.setText(""+result);
        }
        else{
            What.setText("Seg fault");
        }
    }
   

}
