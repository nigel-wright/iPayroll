package se2203b.ipayroll;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 *
 * @author Abdelkader
 */
public class IPayrollController implements Initializable {
    @FXML
    private MenuBar mainMenu;

    @FXML
    private Menu about;

    @FXML
    private Menu file;

    @FXML
    private Menu profile;

    @FXML
    private Menu manageAccount;

    @FXML
    private Menu manageLeave;

    @FXML
    private Menu manageProfile;

    @FXML
    private Menu print;

    @FXML
    private Menu view;

    @FXML
    private Menu generateReports;

    private LoginTableAdapter login;

    private Employee emp;

    private EmployeeTableAdapter employeeTableAdapter;
    private LoginTableAdapter loginTableAdapter;
    private IPAYROLLUserTableAdapter ipayrollUserTableAdapter;

    public void showAbout() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("about-view.fxml"));
        Parent About = (Parent) fxmlLoader.load();
        Scene scene = new Scene(About);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/ipayroll/WesternLogo.png"));
        stage.setTitle("About Us");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showCreateUserAccount() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createAccount-view.fxml"));
        Parent userView = (Parent) fxmlLoader.load();
        Scene scene = new Scene(userView);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/ipayroll/WesternLogo.png"));
        stage.setTitle("Create User Account");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showLogin() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Parent loginView = fxmlLoader.load();
        LoginController login = fxmlLoader.getController();

        login.setIPayrollController(this);

        Scene scene = new Scene(loginView);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.getIcons().add(new Image("file:src/main/resources/se2203b/ipayroll/WesternLogo.png"));
        stage.setTitle("Login to iPAYROLL");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void changePassword() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("passwordChange-view.fxml"));
        Parent passwordView = fxmlLoader.load();
        PasswordChangeController password = fxmlLoader.getController();

        password.setLoginTableAdapter(login);

        password.setIPayrollController(this);

        Scene scene = new Scene(passwordView);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.getIcons().add(new Image("file:src/main/resources/se2203b/ipayroll/WesternLogo.png"));
        stage.setTitle("Change Password");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

        password.after();
    }

    public void showDelete() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("deleteAccount-view.fxml"));
        Parent userView = (Parent) fxmlLoader.load();
        Scene scene = new Scene(userView);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/ipayroll/WesternLogo.png"));
        stage.setTitle("Delete User Account");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showAddNewProfile() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addNewProfile-view.fxml"));
        Parent addView = (Parent) fxmlLoader.load();
        AddNewProfileController addNewPofile = fxmlLoader.getController();

        addNewPofile.setIPayrollController(this);

        Scene scene = new Scene(addView);
        Stage stage = new Stage();
        stage.setScene(scene);


        stage.getIcons().add(new Image("file:src/main/resources/se2203b/ipayroll/WesternLogo.png"));
        stage.setTitle("Add New Employee Profile");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showModify() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modify-view.fxml"));
        Parent addView = (Parent) fxmlLoader.load();

        Scene scene = new Scene(addView);
        Stage stage = new Stage();
        stage.setScene(scene);


        stage.getIcons().add(new Image("file:src/main/resources/se2203b/ipayroll/WesternLogo.png"));
        stage.setTitle("Modify User Profile");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void exit() {
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void resetDB() {
        try {
            // create Team Data store
            login = new LoginTableAdapter(false);
            System.out.println("Login table has been created");

        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        try{
            //employee data table
            employeeTableAdapter = new EmployeeTableAdapter(true);
            System.out.println("Employee table has been created ");
        }catch (SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void unlockAdmin(String name)
    {
        System.out.println("Unlocking for admin.");
        // un-disable the required function
        manageAccount.setDisable(false);
        print.setDisable(false);
        generateReports.setDisable(false);
        manageProfile.setDisable(false);

        // for ADMIN
        profile.setText(name);

        ImageView image = new ImageView("file:src/main/resources/se2203b/ipayroll/AdminPhoto.png");
        image.setFitHeight(20);
        image.setFitWidth(20);

        profile.setGraphic(image);

        profile.setVisible(true);
    }

    public void unlockEmployee(String name) {
        System.out.println("Unlocking for employee.");

        profile.setText(name);

        ImageView image = new ImageView("file:src/main/resources/se2203b/ipayroll/AdminPhoto.png");
        image.setFitHeight(20);
        image.setFitWidth(20);

        view.setDisable(false);
        manageLeave.setDisable(false);

        profile.setGraphic(image);

        profile.setVisible(true);
    }

    public String getProfile() {
        return profile.getText();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {
            loginTableAdapter = new LoginTableAdapter(false);
            ipayrollUserTableAdapter = new IPAYROLLUserTableAdapter(false);
            employeeTableAdapter = new EmployeeTableAdapter(false);

        } catch (SQLException ex) {
            System.out.println("ERROR in 'initialize': " + ex);
        }


        view.setDisable(true); // NOT for admin
        manageAccount.setDisable(true);
        manageLeave.setDisable(true); // NOT for admin
        manageProfile.setDisable(true);
        print.setDisable(true);
        generateReports.setDisable(true);
    }

    public void afterPasswordChange() {
        view.setDisable(true); // NOT for admin
        manageAccount.setDisable(true);
        manageLeave.setDisable(true); // NOT for admin
        manageProfile.setDisable(true);
        print.setDisable(true);
        generateReports.setDisable(true);
        profile.setVisible(false);
    }

    public void logout(ActionEvent event) {
        afterPasswordChange();
    }
}
