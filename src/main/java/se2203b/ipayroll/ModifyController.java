package se2203b.ipayroll;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;

import static java.time.LocalTime.parse;

public class ModifyController implements Initializable {

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
    private ComboBox<String> employeeChoice;

    @FXML
    private Button saveBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField sin;

    @FXML
    private TextField skillCode;

    private EmployeeTableAdapter empTable;
    private LoginTableAdapter loginTable;
    private IPAYROLLUserTableAdapter payrollTable;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save(ActionEvent event) throws Exception {
        empTable = new EmployeeTableAdapter(false);

        String id = employeeChoice.getValue();
        String empFullName = employeeName.getText();

        Login login = new Login(id, empFullName, true);

        iPAYROLLUser user = new iPAYROLLUser(id, empFullName);

        System.out.println(login.getID());

        System.out.println(login.getFullName());

        Employee emp = new Employee(id, empFullName,
                sin.getText(),
                phone.getText(),
                province.getText(),
                postalCode.getText(),
                city.getText(),
                jobName.getText(),
                java.sql.Date.valueOf(dateOfBirth.getValue()),
                java.sql.Date.valueOf(dateOfHire.getValue()),
                java.sql.Date.valueOf(dateOfLastPromotion.getValue()),
                martialStatus.getText(),
                Integer.parseInt(skillCode.getText()));

        System.out.println(emp.getName());

        empTable.updateTable(emp);

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();

    }

    @FXML
    void choice(ActionEvent event) throws Exception {
        empTable = new EmployeeTableAdapter(false);
        Employee emp = empTable.findEmployee(employeeChoice.getValue());

        employeeName.setText(empTable.getName(employeeChoice.getValue()));
        province.setText(emp.getProvince());
        city.setText(emp.getCity());
        phone.setText(emp.getPhoneNumber());
        postalCode.setText(emp.getPostalCode());
        martialStatus.setText(emp.getMaritalStatus());
        skillCode.setText(String.valueOf(emp.getSkillCode()));
        sin.setText(emp.getSIN());
        jobName.setText(emp.getJobClass());
        dateOfBirth.setValue(getDate(emp.getDOB().toString()));
        dateOfHire.setValue(getDate(emp.getDateOfHire().toString()));
        dateOfLastPromotion.setValue(getDate(emp.getDateOfLastPromotion().toString()));
    }

    public static LocalDate getDate(String date) {
        DateTimeFormatter format = DateTimeFormatter. ofPattern ("yyyy-MM-dd") ;
        try {
            LocalDate newDate = LocalDate.parse(date, format);
            return newDate;
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
            return null; //
        }
    }

    @FXML
    void delete(ActionEvent event) throws Exception {
        empTable = new EmployeeTableAdapter(false);
        loginTable = new LoginTableAdapter(false);
        payrollTable = new IPAYROLLUserTableAdapter(false);

        if (payrollTable.loginCheck(employeeChoice.getValue())) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("noDelete-view.fxml"));
            Parent About = (Parent) fxmlLoader.load();
            Scene scene = new Scene(About);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.getIcons().add(new Image("file:src/main/resources/se2203b/ipayroll/WesternLogo.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } else {
            Employee emp = empTable.findEmployee(employeeChoice.getValue());

            empTable.deleteEmployee(employeeChoice.getValue());
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        }
    }



    public void initialize(URL url, ResourceBundle resourceBundle)  {
        try {
            empTable = new EmployeeTableAdapter(false);
            List<String> list = empTable.getKeys();
            employeeChoice.getItems().addAll(list);
        } catch (Exception ex) {
            System.out.println("ERROR in 'initialize': " + ex);
        }
    }
}
