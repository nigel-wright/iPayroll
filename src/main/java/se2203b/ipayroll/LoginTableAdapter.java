package se2203b.ipayroll;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginTableAdapter {

    Connection connection;

    public LoginTableAdapter(Boolean reset) throws SQLException {
        String DB_URL = "jdbc:derby:iPayrollDB";
        connection = DriverManager.getConnection(DB_URL);
        Statement stmt = connection.createStatement();
        if (reset) {
            try {
                // Remove the table if exists to create a new one from scratch.
                // This will throw an exception if the table does not exist
                stmt.execute("DROP TABLE LoginInfo");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
            }
        }

        try {
            // Create the table
            String command ="CREATE TABLE LoginInfo ("
                    + "employeeID VARCHAR(5), "
                    + "fullName VARCHAR(50), "
                    + "email VARCHAR(50),"
                    + "userName VARCHAR(50), "
                    + "password VARCHAR(50)"
                    + ")";
            stmt.execute(command);
        } catch (SQLException e) {
            //   throw new RuntimeException(e);
        }

        if (reset) {
            populateSamples();
        }
    }

    private void populateSamples() throws SQLException {
        // Add an admin user
        addNewLogin(new Login("admin", "admin"));
    }

    public Login getLogin(String key) throws Exception {
        ResultSet rs;
        Login login = null;

        try {
            Statement stmt = connection.createStatement();
            String command = String.format("SELECT * FROM LoginInfo WHERE employeeID = '%s'", key);
            rs = stmt.executeQuery(command);

            if (rs.next()) {
                login = new Login(rs.getString("employeeID"),
                        rs.getString("fullName"),
                        rs.getString("userName"),
                        rs.getString("email"),
                        rs.getString("password"));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR in 'getLogin': " + ex);
        }
        return login;
    }

    public void addNewLogin(Object data) {
        System.out.println("here");
        Login user = (Login) data;
        String command = "INSERT INTO LoginInfo (employeeID, fullName, email, username, password) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(command)) {
            stmt.setString(1, user.getID());
            stmt.setString(2, user.getFullName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getUserName());
            stmt.setString(5, user.getPassword());
            int affectedRow = stmt.executeUpdate();

            System.out.println(command);

            if (affectedRow > 0) {
                System.out.println("Employee added successfully. (addNewLogin)");
            } else {
                System.out.println("Employee was not added. (addNewLogin)");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            throw new RuntimeException(e);
        }
    }

    public String findPassword(String key) throws SQLException {
        ResultSet rs;
        String pass = "";
        try {
            Statement stmt = connection.createStatement();
            String command = String.format("SELECT * FROM LoginInfo WHERE username = '%s'", key);
            rs = stmt.executeQuery(command);

            if (rs.next()) {
                pass += rs.getString("password");
                System.out.println("the username is: " + key);
                System.out.println("the passowrd is: " + pass);
            } else {
                System.out.println("There is no 'next'!!");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex);
        }
        return pass;
    }

    public boolean adminCheck(String key) throws SQLException{
        boolean isAdmin = false;
        ResultSet rs;

        try {
            Statement stmt = connection.createStatement();
            String command = String.format("SELECT * from LoginInfo where username = '%s'", key);
            rs = stmt.executeQuery(command);
            if (rs.next()) {
                String username = rs.getString("username");
                // isAdmin = employeeID == null || employeeID.trim().isEmpty();
                if (username.equals("admin") || username.equals("Admin")) {
                    isAdmin = true;
                } else {
                    isAdmin = false;
                }
            }
        } catch(SQLException ex) {
            System.out.println("ERROR: " + ex);
        }
        return isAdmin;
    }

    public boolean changePassword(String user, String oldPassword, String newPassword, String confirmPassword) throws SQLException {
        ResultSet rs;
        boolean passwordExist = false;

        String storedPassword = findPassword(user);

        System.out.println(user);
        System.out.println(oldPassword);

        if (storedPassword == null || !storedPassword.equals(oldPassword)) {
            System.out.println("The old password IS incorrect!");
            return passwordExist;
        }

        if (!newPassword.equals(confirmPassword)) {
            System.out.println("The passwords do NOT match!");
            return passwordExist;
        }

        try (PreparedStatement stmt = connection.prepareStatement("UPDATE LoginInfo SET password = ? WHERE username = ?")) {
            stmt.setString(1, newPassword);
            stmt.setString(2, user);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
        catch (SQLException ex) {
            System.out.println("ERROR: " + ex);
        }
        return passwordExist;
    }

    public List<String> getUserName() {
        ResultSet rs;
        List<String> list = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            String command = "SELECT userName FROM LoginInfo";
            rs = stmt.executeQuery(command);

            while (rs.next()) {
                String user = rs.getString("userName");

                if (user.trim().equalsIgnoreCase("admin")) {
                    continue;
                }

                list.add(user);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR in 'getUserName': " + ex);
        }
        return list;
    }

    public Login findUserInfo(String key) {
        ResultSet rs;
        Login login = null;

        try {
            Statement stmt = connection.createStatement();
            String command = String.format("SELECT * FROM LoginInfo WHERE userName = '%s'", key);
            rs = stmt.executeQuery(command);

            while (rs.next()) {
                login = new Login(rs.getString("employeeID"),
                        rs.getString("fullName"),
                        rs.getString("userName"),
                        rs.getString("email"),
                        rs.getString("password"));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR in 'getUserInfo': " + ex);
        }
        return login;
    }

    public String idName;
    public String empName;

    public void getInfo(String key) {
        ResultSet rs;

        try {
            Statement stmt = connection.createStatement();
            String command = String.format("Select * FROM LoginInfo WHERE userName = '%s'", key);
            rs = stmt.executeQuery(command);

            idName = rs.getString("employeeID");
            empName = rs.getString("fullName");

        } catch (SQLException ex) {
            System.out.println("ERROR in 'removeUser': " + ex);
        }
    }

    public String getIdName() {
        return idName;
    }

    public String getEmpName() {
        return empName;
    }

    public void removeUser(String key) {

        try {
            Statement stmt = connection.createStatement();
            String command = String.format("DELETE FROM LoginInfo WHERE userName = '%s'", key);
            int affectedRows = stmt.executeUpdate(command);

            if (affectedRows > 0) {
                System.out.println("Success in deleting an employee from LoginInfo!!");
            } else {
                System.out.println("Fail in deleting an employee from LoginInfo!!");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR in 'removeUser': " + ex);
        }
    }

//    public boolean loginCheck(String key) {
//        ResultSet rs;
//        try {
//            Statement stmt = connection.createStatement();
//            String command = String.format("SELECT * FROM LoginInfo WHERE username = '%s'", key);
//            rs = stmt.executeQuery(command);
//
//            while (rs.next()) {
//                if (rs.getString("username") != null) {
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        } catch (SQLException ex) {
//            System.out.println("ERROR in 'loginCheck': " + ex);
//        }
//        return false;
//    }
}
