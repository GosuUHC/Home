package lab_5;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    Button selectAll;
    @FXML
    Button update;
    @FXML
    Button delete;
    @FXML
    Button insert;

    @FXML
    TextField updateId;
    @FXML
    TextField updateName;
    @FXML
    TextField deleteid;
    @FXML
    TextField insertId;
    @FXML
    TextField insertName;

    @FXML
    TableView deptTable;

    @FXML
    TableColumn<Department, Integer> deptIdCol;

    @FXML
    TableColumn<Department, String> deptNameCol;

    @FXML
    void initialize() {
        deptIdCol.setCellValueFactory(cellData -> cellData.getValue().deptIdProperty().asObject());
        deptNameCol.setCellValueFactory(cellData -> cellData.getValue().deptNameProperty());
    }

    @FXML
    void populateDepartment(ObservableList<Department> deptData) throws ClassNotFoundException {
        deptTable.setItems(deptData);
    }

    @FXML
    void searchDepartment(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            ObservableList<Department> deptData = DepartmentDAO.searchDepartments();
            populateDepartment(deptData);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @FXML
    void updateDeptName(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            DepartmentDAO.updateDeptName(updateId.getText(), updateName.getText());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @FXML
    void insertDept(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            DepartmentDAO.insertDept(insertId.getText(), insertName.getText());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    @FXML
    void deleteDept(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try{
            DepartmentDAO.deleteDeptWithId(deleteid.getText());
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

}
