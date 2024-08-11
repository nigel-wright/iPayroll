package se2203b.ipayroll;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CreateUserAccountController implements Initializable {

    @FXML
    private PasswordField confirmPassWord;

    @FXML
    private TextField emailField;

    @FXML
    private ComboBox<String> employeeChoice;

    @FXML
    private TextField fullName;

    @FXML
    private PasswordField passWord;

    @FXML
    private TextField userNameField;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button saveBtn;

    private EmployeeTableAdapter empTable;
    private LoginTableAdapter loginTableAdapter;
    private IPAYROLLUserTableAdapter ipayrollUserTableAdapter;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save(ActionEvent event) throws Exception {
        loginTableAdapter = new LoginTableAdapter(false);
        ipayrollUserTableAdapter = new IPAYROLLUserTableAdapter(false);

        String password = passWord.getText().trim();
        String confirmPassword = confirmPassWord.getText().trim();

        if (password.equals(confirmPassword)) {
            //Login login = new Login(employeeChoice.getValue(), fullName.getText(), userNameField.getText(), emailField.getText(), password);
            //loginTableAdapter.addNewLogin(login);
            Login login = new Login(userNameField.getText(), password);
            iPAYROLLUser user = new iPAYROLLUser(employeeChoice.getValue(), fullName.getText(), emailField.getText(), login);
            loginTableAdapter.addNewLogin(login);
            ipayrollUserTableAdapter.addNewRecord(user);
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        } else {
            System.out.println("Password must match! ");
        }
    }

    @FXML
    void choice(ActionEvent event) throws Exception{
        empTable = new EmployeeTableAdapter(false);
        String name = empTable.getName(employeeChoice.getValue());
        fullName.setText(name);
        fullName.setDisable(true);
    }

    @Override
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
