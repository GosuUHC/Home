package num3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

public class PrimaryController {
    private int count = 0;
    private boolean[] orders = new boolean[3];
    private String finalcosttext = "";
    private String c1text = "500";
    private String c2text = "600";
    private String c3text = "700";

    @FXML
    Spinner spin1 = new Spinner<Integer>(0, 10, 1);

    @FXML
    Spinner spin2 = new Spinner<Integer>(0, 10, 1);

    @FXML
    Spinner spin3 = new Spinner<Integer>(0, 10, 1);

    @FXML
    CheckBox check1 = new CheckBox("Добавить в заказ");

    @FXML
    CheckBox check2 = new CheckBox("Добавить в заказ");

    @FXML
    CheckBox check3 = new CheckBox("Добавить в заказ");

    @FXML
    Label cost1 = new Label();

    @FXML
    Label cost2 = new Label();

    @FXML
    Label cost3 = new Label();

    @FXML
    Label finalcost = new Label();

    @FXML
    Button butt = new Button("Сделать заказ");

    private void CheckCount(int count) {
        if (count > 0) {
            butt.setDisable(false);
        } else {
            finalcost.setText("");
            butt.setDisable(true);
        }
    }

    @FXML
    public void cost1Update() {
        c1text = "" + 500 * ((Integer) spin1.getValue());
        cost1.setText(c1text);
    }

    @FXML
    public void cost2Update() {
        c2text = "" + 600 * ((Integer) spin2.getValue());
        cost2.setText(c2text);
    }

    @FXML
    public void cost3Update() {
        c3text = "" + 700 * ((Integer) spin3.getValue());
        cost3.setText(c3text);
    }

    public void finalcostUpdate() {
        int fincost = 0;
        if (orders[0]) {
            fincost += Integer.valueOf(c1text);
        }
        if (orders[1]) {
            fincost += Integer.valueOf(c2text);
        }
        if (orders[2]) {
            fincost += Integer.valueOf(c3text);
        }

        finalcosttext = "" + fincost;
        finalcost.setText(finalcosttext);
    }

    @FXML
    public void SetOpcty1() {
        if (check1.isSelected()) {
            spin1.setDisable(false);
            count++;
            CheckCount(count);
            orders[0] = true;
            cost1.setText(c1text);
        } else {
            spin1.setDisable(true);
            count--;
            CheckCount(count);
            orders[0] = false;
            cost1.setText("0");
        }
    }

    @FXML
    public void SetOpcty2() {
        if (check2.isSelected()) {
            spin2.setDisable(false);
            count++;
            CheckCount(count);
            orders[1] = true;
            cost2.setText(c2text);

        } else {
            spin2.setDisable(true);
            count--;
            CheckCount(count);
            orders[1] = false;
            cost2.setText("0");

        }
    }

    @FXML
    public void SetOpcty3() {
        if (check3.isSelected()) {
            spin3.setDisable(false);
            count++;
            CheckCount(count);
            cost3.setText(c3text);
            orders[2] = true;

        } else {
            spin3.setDisable(true);
            count--;
            CheckCount(count);
            orders[2] = false;
            cost3.setText("0");

        }
    }

    public int GetCount() {
        return this.count;
    }

}
