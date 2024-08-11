package se2203b.ipayroll;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button cancelBtn;

    @FXML
    private Button okBtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private ImageView westernLogo;

    @FXML
    private Label wrongLbl;

    private LoginTableAdapter login;

    private IPayrollController iPayrollController;

    public void setIPayrollController(IPayrollController iPayrollController) {
        this.iPayrollController = iPayrollController;
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save(ActionEvent event) throws Exception {
        // Add code here
        login = new LoginTableAdapter(false);

        String user = username.getText().trim();
        String pass = password.getText().trim();

        System.out.println("'user' is: " + user);
        System.out.println("'pass' is: " + pass);

        try {
            String find = login.findPassword(user);

            System.out.println("'find' return this: " + find);

            if (find == null ) {
                wrongLbl.setText("Wrong username");
            } else if (!find.equals(pass) || (find == null)) {
                wrongLbl.setText("Wrong password");
            } else {
                if (login.adminCheck(user)) {
                    iPayrollController.unlockAdmin(user);
                } else {
                    iPayrollController.unlockEmployee(user);
                }
                Stage stage = (Stage) cancelBtn.getScene().getWindow();
                stage.close();
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
        }
    }

    public String getUser() {
        return username.getText();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        westernLogo.setImage(new Image("file:src/main/resources/se2203b/ipayroll/WesternLogo.png"));
    }
}
