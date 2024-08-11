package se2203b.ipayroll;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTableAdapter {

    Connection connection;

    public EmployeeTableAdapter(Boolean reset) throws SQLException {
        String DB_URL = "jdbc:derby:iPayrollDB";
        connection = DriverManager.getConnection(DB_URL);
        Statement stmt = connection.createStatement();
        if (reset) {
            try {
                // Remove the table if exists to create a new one from scratch.
                // This will throw an exception if the table does not exist
                stmt.execute("DROP TABLE EmployeeInfo");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
            }
        }

        try {
            // Create the table
            String command ="CREATE TABLE EmployeeInfo ("
                    + "employeeID VARCHAR(5) UNIQUE , "
                    + "employeeName VARCHAR(50), "
                    + "province VARCHAR(50),"
                    + "city VARCHAR(50), "
                    + "phone VARCHAR(50), "
                    + "postalCode VARCHAR(10), "
                    + "maritalStatus VARCHAR(50), "
                    + "skillCode INT,"
                    + "SIN VARCHAR(50), "
                    + "jobClass VARCHAR(50), "
                    + "dateOfBirth DATE, "
                    + "dateOfHire DATE, "
                    + "dateOfLastPromotion DATE"
                    + ")";
            stmt.execute(command);
        } catch (SQLException e) {
            // throw new RuntimeException(e);
        }
    }

    public void addNewEmployee(Employee emp) {
        String command = "INSERT INTO EmployeeInfo (employeeID, employeeName, province, city, phone, maritalStatus, postalCode, skillCode, SIN, jobClass, dateOfBirth, dateOfHire, dateOfLastPromotion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(command)) {
            stmt.setString(1, emp.getID());
            stmt.setString(2, emp.getName());
            stmt.setString(3, emp.getProvince());
            stmt.setString(4, emp.getCity());
            stmt.setString(5, emp.getPhoneNumber());
            stmt.setString(6, emp.getMaritalStatus());
            stmt.setString(7, emp.getPostalCode());
            stmt.setInt(8, emp.getSkillCode());
            stmt.setString(9, emp.getSIN());
            stmt.setString(10, emp.getJobClass());
            stmt.setDate(11, (Date) emp.getDOB());
            stmt.setDate(12, (Date) emp.getDateOfHire());
            stmt.setDate(13, (Date) emp.getDateOfLastPromotion());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Success!! Employee was added.");
            } else {
                System.out.println("Fail!! Did not ADD employee.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR in the 'addNewEmployee': " + e);
        }
    }

    public String getName(String id) throws SQLException {
        ResultSet rs = null;
        String line = "";

        try {
            Statement stmt = connection.createStatement();
            String command = String.format("SELECT * FROM EmployeeInfo WHERE employeeID = '%s'", id);
            rs = stmt.executeQuery(command);

            while (rs.next()) {
                line += rs.getString("employeeName");
                System.out.println("THE NAME IS: " + line);
            }
        } catch (SQLException ex) {
            System.out.println();
        } finally { // remove or research further
            if (rs != null) {
                System.out.println("RS was not null and it was closed!!");
                rs.close();
            }
        }
        return line;
    }

    public List<String> getKeys() {
        List<String> list = new ArrayList<>();
        ResultSet rs;

        try {
            Statement stmt = connection.createStatement();
            String command = "SELECT employeeID FROM EmployeeInfo";
            rs = stmt.executeQuery(command);

            while(rs.next()) {
                String line = rs.getString("employeeID");
                System.out.println(line);
                list.add(line);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR in 'getKeys': " + ex);
            throw new RuntimeException(ex);
        }
        return list;
    }

    public void updateTable(Object data) {
        Employee emp = (Employee) data;
        ResultSet rs = null;

        try (PreparedStatement stmt = connection.prepareStatement(
                "UPDATE EmployeeInfo SET employeeName=?, province=?, city=?, phone=?, postalCode=?, maritalStatus=?, skillCode=?, SIN=?, jobClass=?, dateOfBirth=?, dateOfHire=?, dateOfLastPromotion=? WHERE employeeID=?")) {
            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getProvince());
            stmt.setString(3, emp.getCity());
            stmt.setString(4, emp.getPhoneNumber());
            stmt.setString(5, emp.getPostalCode());
            stmt.setString(6, emp.getMaritalStatus());
            stmt.setInt(7, emp.getSkillCode()); //int
            stmt.setString(8, emp.getSIN()); //string
            stmt.setString(9, emp.getJobClass());
            stmt.setDate(10, emp.getDOB());
            stmt.setDate(11, emp.getDateOfHire());
            stmt.setDate(12, emp.getDateOfLastPromotion());
            stmt.setString(13, emp.getID());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("updateTable worked! ");
            } else {
                System.out.println("updateTable did NOT work! ");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR in 'updateTable': " + ex);;
        }
    }

    public Employee findEmployee(String key) throws SQLException {
        ResultSet rs;
        Employee employee = null;

        LoginTableAdapter login = new LoginTableAdapter(false);

        try {
            Statement stmt = connection.createStatement();;
            String command = String.format("Select * FROM EmployeeInfo WHERE employeeID = '%s'", key);
            rs = stmt.executeQuery(command);

            if (rs.next()) {
                employee = new Employee(rs.getString("employeeName"),
                                        rs.getString("SIN"),
                                        rs.getString("phone"),
                                        rs.getString("province"),
                                        rs.getString("postalCode"),
                                        rs.getString("city"),
                                        rs.getString("jobClass"),
                                        rs.getDate("dateOfBirth"),
                                        rs.getDate("dateOfHire"),
                                        rs.getDate("dateOfLastPromotion"),
                                        rs.getString("maritalStatus"),
                                        rs.getInt("skillCode"));
            }
        } catch (Exception ex) {
            System.out.println("ERROR in 'findEmployee': " + ex);
            throw new RuntimeException(ex);
        }
        return employee;
    }

    public void deleteEmployee(String key) {
        try {
            Statement stmt = connection.createStatement();
            String command = String.format("DELETE FROM EmployeeInfo WHERE employeeID = '%s'", key);
            int affectedRows = stmt.executeUpdate(command);

            if (affectedRows > 0) {
                System.out.println("The deletion of an Employee worked!! ");
            } else {
                System.out.println("The deletion of an Employee did NOT work!!");
            }

        } catch (SQLException ex){
            System.out.println("ERROR in 'deleteEmployee': " + ex);
        }
    }
}
