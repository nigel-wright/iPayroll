package se2203b.ipayroll;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DeleteAccountController implements Initializable {

    @FXML
    private Button cancelBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private ComboBox<String> employeeChoice;

    @FXML
    private TextField idBox;

    @FXML
    private TextField nameBox;

    private EmployeeTableAdapter empTable;
    private LoginTableAdapter loginTable;
    private IPAYROLLUserTableAdapter payrollTable;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void delete(ActionEvent event) throws Exception{
        loginTable = new LoginTableAdapter(false);

        payrollTable = new IPAYROLLUserTableAdapter(false);

        payrollTable.removeUser(employeeChoice.getValue());

        //loginTable.removeUser(employeeChoice.getValue());
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void choice(ActionEvent event) throws Exception {
        loginTable = new LoginTableAdapter(false);
        payrollTable = new IPAYROLLUserTableAdapter(false);

        //Login login = loginTable.findUserInfo(employeeChoice.getValue());
        iPAYROLLUser user = payrollTable.findUser(employeeChoice.getValue());

        idBox.setText(user.getID());
        idBox.setDisable(true);

        nameBox.setText(user.getName());
        nameBox.setDisable(true);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            payrollTable = new IPAYROLLUserTableAdapter(false);
            //loginTable = new LoginTableAdapter(false);
            List<String> list = payrollTable.getUserName();
            employeeChoice.getItems().addAll(list);
        } catch (Exception ex) {
            System.out.println("ERROR in 'initialize': " + ex);
        }
    }
}
