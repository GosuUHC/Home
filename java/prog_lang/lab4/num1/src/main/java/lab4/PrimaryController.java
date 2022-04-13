package lab4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PrimaryController {

    private String firstNText = "";
    private String whatText = "";
    private String secondNText = "";
    @FXML
    Button plus = new Button();
    @FXML
    Button minus = new Button("-");
    @FXML
    Button times = new Button("*");
    @FXML
    Button div = new Button("/");
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
    boolean isFirstLetter = true;
    double result = 0;  
    private Socket s;
    private DataInputStream in;
    private DataOutputStream out;


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

    public PrimaryController() {
        try{
        System.out.println("Cont init");
        Socket s = new Socket("127.0.0.1", 1111);
        System.out.println("Local port: " + s.getLocalPort());
        System.out.println("Remote port: " + s.getPort());

        in = new DataInputStream(s.getInputStream());
        out = new DataOutputStream(s.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void parse() {
        try {
        
            out.writeUTF(firstNText);
            out.writeUTF(whatText);
            out.writeUTF(secondNText);
            out.flush();
            
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
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
            this.parse();
            
            secondN.setOpacity(0);
            firstN.setOpacity(0);
            String res = "";
            try {
                res = in.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            What.setText(res);
        } else {
            What.setText("Seg fault");
        }
    }
}
