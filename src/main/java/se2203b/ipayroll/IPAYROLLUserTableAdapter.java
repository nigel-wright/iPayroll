package se2203b.ipayroll;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IPAYROLLUserTableAdapter {

    Connection connection;

    public IPAYROLLUserTableAdapter(Boolean reset) throws SQLException {
        String DB_URL = "jdbc:derby:iPayrollDB";
        connection = DriverManager.getConnection(DB_URL);
        Statement stmt = connection.createStatement();
        if (reset) {
            try {
                // Remove the table if exists to create a new one from scratch.
                // This will throw an exception if the table does not exist
                stmt.execute("DROP TABLE UserInfo");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
            }
        }

        try {
            // Create the table
            String command ="CREATE TABLE UserInfo ("
                    + "employeeID VARCHAR(5), "
                    + "fullname VARCHAR(50), "
                    + "email VARCHAR(50), "
                    + "userName VARCHAR(50), "
                    + "password VARCHAR(50)"
                    + ")";
            stmt.execute(command);
            populateSamples();
        } catch (SQLException e) {
            //   throw new RuntimeException(e);
        }
    }

    public void populateSamples() throws SQLException {
        // Add an iPayroll user
        Login login = new Login("admin", "admin");
        addNewRecord(new iPAYROLLUser("A001", " ", " ", login));
    }

    public void addNewRecord(Object data) throws SQLException {

        iPAYROLLUser user = (iPAYROLLUser) data;

        String command = "INSERT INTO UserInfo (employeeID, fullname, email, username, password) VALUES (?, ?, ?, ?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(command)) {
            stmt.setString(1, user.getID());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getUsername());
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
        }
    }

    public iPAYROLLUser findUser(String key) {
        ResultSet rs = null;
        iPAYROLLUser user = null;
        Login login = null ;
        try {
            Statement stmt = connection.createStatement();
            String command = String.format("SELECT * FROM UserInfo WHERE userName = '%s'",key);
            rs = stmt.executeQuery(command);


            while (rs.next()) {
                login = new Login(rs.getString("username"), rs.getString("password"));
                user = new iPAYROLLUser(rs.getString("employeeID"), rs.getString("fullName"), rs.getString("email"), login);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR in 'findUser': " + ex);
        }
        return user;
    }

    public boolean loginCheck(String key) {
        ResultSet rs;
        try {
            Statement stmt = connection.createStatement();
            String command = String.format("SELECT * FROM UserInfo WHERE employeeID = '%s'", key);
            rs = stmt.executeQuery(command);

            while (rs.next()) {
                if (rs.getString("employeeID") != null) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            System.out.println("ERROR in 'loginCheck': " + ex);
        }
        return false;
    }

    public void removeUser(String key) {
        try {
            Statement stmt = connection.createStatement();
            String command = String.format("DELETE FROM UserInfo WHERE userName = '%s'", key);
            int affectedRows = stmt.executeUpdate(command);

            if (affectedRows > 0) {
                System.out.println("Success in deleting an employee from UserInfo!!");
            } else {
                System.out.println("Fail in deleting an employee from UserInfo!!");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR in 'removeUser': " + ex);
        }
    }

    public List<String> getUserName() {
        ResultSet rs;
        List<String> list = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            String command = "SELECT userName FROM UserInfo";
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
}
