package se2203b.ipayroll;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddNewProfileController  {

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField city;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private DatePicker dateOfHire;

    @FXML
    private DatePicker dateOfLastPromotion;

    @FXML
    private TextField employeeID;

    @FXML
    private TextField employeeName;

    @FXML
    private TextField jobName;

    @FXML
    private TextField martialStatus;

    @FXML
    private TextField phone;

    @FXML
    private TextField postalCode;

    @FXML
    private TextField province;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField sin;

    @FXML
    private TextField skillCode;

    private IPayrollController iPayrollController;

    private EmployeeTableAdapter empTable;

    public void setIPayrollController(IPayrollController iPayrollController) {
        this.iPayrollController = iPayrollController;
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save(ActionEvent event) throws SQLException {
        empTable = new EmployeeTableAdapter(false);

        // Take in data from the given inputted values
        String ID = employeeID.getText();
        String name = employeeName.getText();
        String SIN = sin.getText();
        String prov = province.getText();
        String cit = city.getText();
        String pCode = postalCode.getText();
        String pNumber = phone.getText();
        String jClass = jobName.getText();
        java.sql.Date dobValue = java.sql.Date.valueOf(dateOfBirth.getValue());
        String maritalStatus = martialStatus.getText();
        int sCode = Integer.parseInt(skillCode.getText());
        java.sql.Date hireDateValue = java.sql.Date.valueOf(dateOfHire.getValue());
        java.sql.Date promotionDateValue = java.sql.Date.valueOf(dateOfLastPromotion.getValue());

        //Login login = new Login(ID, name, true);

        Employee emp = new Employee(ID, name, SIN, pNumber, prov, pCode, cit, jClass, dobValue, hireDateValue, promotionDateValue, maritalStatus, sCode);

        try {
            empTable.addNewEmployee(emp);
            System.out.println("DONE! The employee was added!!!");
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }
    }

}
