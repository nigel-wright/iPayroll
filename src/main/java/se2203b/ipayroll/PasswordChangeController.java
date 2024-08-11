package se2203b.ipayroll;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PasswordChangeController implements Initializable {

    private IPayrollController iPayrollController;

    private LoginTableAdapter table;

    private String curr;

    @FXML
    private Button cancelBtn;

    @FXML
    private PasswordField newPass;

    @FXML
    private PasswordField newPassConf;

    @FXML
    private PasswordField oldPass;

    @FXML
    private Button saveBtn;

    @FXML
    private Label passowrdLbl;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save(ActionEvent event) throws  Exception{

        String username = curr;
        String oldPassword = oldPass.getText();
        String newPassword = newPass.getText();
        String newPasswordConfirm = newPassConf.getText();

        table = new LoginTableAdapter(false);

        try {
            boolean success = table.changePassword(username, oldPassword, newPassword, newPasswordConfirm);
            if (success) {
                Stage stage = (Stage) cancelBtn.getScene().getWindow();
                stage.close();
                iPayrollController.afterPasswordChange();
            } else {
                System.out.println("Password change did NOT work!");
            }
        } catch (Exception ex)  {
            System.out.println("ERROR: " + ex);
        }
    }

    public void setIPayrollController(IPayrollController iPayrollController) {
        this.iPayrollController = iPayrollController;
    }

    public void setLoginTableAdapter(LoginTableAdapter table) {
        this.table = table;
    }

    public void after() {
        curr = iPayrollController.getProfile();
        passowrdLbl.setText("Change password for " + curr);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
