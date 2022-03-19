package num5;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class PrimaryController {

    //Map<Integer, String>colourAndPos = new HashMap<Integer, String>();
    private String[] colours = new String[3];
    private int count = 0;  
    private boolean[] isarmed = new boolean[5];
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    @FXML
    Label colour1 = new Label();

    @FXML
    Label colour2 = new Label();

    @FXML
    Label colour3 = new Label();

    @FXML
    Label colour4 = new Label();

    @FXML
    Label colour5 = new Label();

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
    public void drawFlag(){
        drawcolour1.setStyle(colours[0]);
        drawcolour2.setStyle(colours[1]);
        drawcolour3.setStyle(colours[2]);
        
    }

    @FXML
    public void colBtncheck1(){
        if(colourButton1.isSelected()){
            colours[count] = "-fx-background-color: Red;";
            isarmed[0] = true;
            count++;
            System.out.println("btn1");
            Checkcount();
        }
        else {
            count--;
            colours[count]="";
            isarmed[0] = false;
            System.out.println("btn1_dis");
            Checkcount();

        }
    }
    @FXML
    public void colBtncheck2(){
        if(colourButton2.isSelected()){
            colours[count] = "-fx-background-color: Yellow;";
            isarmed[1] = true;
            count++;
            System.out.println("btn2");
            Checkcount();

        }
        else {
            count--;
            colours[count] = "";
            isarmed[1] = false;
            System.out.println("btn2_dis");
            Checkcount();

        }
    }
    @FXML
    public void colBtncheck3(){
        if(colourButton3.isSelected()){
            colours[count]= "-fx-background-color: Green;";
            isarmed[2] = true;
            count++;
            Checkcount();

        }
        else{
            count--;
            colours[count] = "";
            isarmed[2] = false;
            Checkcount();

        }
    }
    @FXML
    public void colBtncheck4(){
        if(colourButton4.isSelected()){
            colours[count] = "-fx-background-color: White;";
            isarmed[3] = true;
            count++;
            Checkcount();

        }
        else{
            count--;
            colours[count] = "";
            isarmed[3] = false;
            Checkcount();

        }
    }
    @FXML
    public void colBtncheck5(){
        if(colourButton5.isSelected()){
            colours[count] =  "-fx-background-color: Blue;";
            isarmed[4] = true;
            count++;
            Checkcount();

        }
        else{
            count--;
            colours[count] = "";
            isarmed[4] = false;
            Checkcount();

        }

    }


    public void Checkcount(){
        if(count>=3){
            drawButton.setDisable(false);
            if(!colourButton1.isArmed()){
                colourButton1.setDisable(true);
            }
            else{
                colourButton1.setDisable(false);
            }
            if(!colourButton2.isArmed()){
                colourButton2.setDisable(true);
            }
            else{
                colourButton2.setDisable(false);
            }

            if(!colourButton3.isArmed()){
                colourButton3.setDisable(true);
            }
            else{
                colourButton3.setDisable(false);
            }
            if(!colourButton4.isArmed()){
                colourButton4.setDisable(true);
            }
            else{
                colourButton4.setDisable(false);
            }
            if(!colourButton5.isArmed()){
                colourButton5.setDisable(true);
            }
            else{
                colourButton5.setDisable(false);
            }

        }
        else{
            drawButton.setDisable(true);
            colourButton1.setDisable(false);
            colourButton2.setDisable(false);
            colourButton3.setDisable(false);
            colourButton4.setDisable(false);
            colourButton5.setDisable(false);
        }
        
    }


}
