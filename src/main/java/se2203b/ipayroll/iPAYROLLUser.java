package se2203b.ipayroll;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class iPAYROLLUser {
//    private String ID;
//    private String name;
//
//    public iPAYROLLUser(Login login) {
//        this.ID = login.getID();
//        this.name = login.getFullName();
//    }
//
//    public iPAYROLLUser(String ID, String name) {
//        this.ID = ID;
//        this.name = name;
//    }
//
//    public iPAYROLLUser() {}
//
//    public void setID(String ID) {
//        this.ID = ID;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getID() {
//        return ID;
//    }
//
//    public String getName() {
//        return name;
//    }

    private StringProperty ID;
    private StringProperty name;
    private StringProperty email;

    String username;
    String password;

    public iPAYROLLUser(String id, String name, String email, Login login) {
        ID = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.username = login.getUserName();
        this.password = login.getPassword();
    }

    public iPAYROLLUser(String ID, String name) {
        this.ID = new SimpleStringProperty(ID);
        this.name = new SimpleStringProperty(name);
    }

    public iPAYROLLUser() {}

    public void setID(String ID) {
        this.ID.set(ID);
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty idProperty() {
        return ID;
    }
    public StringProperty nameProperty() {
        return name;
    }
    public StringProperty emailProperty() {
        return email;
    }

    public String getID() {
        return ID.get();
    }
    public String getName() {
        return name.get();
    }
    public String getEmail() {
        return email.get();
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
