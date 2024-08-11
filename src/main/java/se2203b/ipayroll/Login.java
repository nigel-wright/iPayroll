package se2203b.ipayroll;

public class Login {

    private String ID;
    private String fullName;
    private String userName;
    private String email;
    private String password;

    public Login(String id, String fullName, String userName, String email, String password) {
        ID = id;
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public Login(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Login(String ID, String fullName, boolean add) {
        this.ID = ID;
        this.fullName = fullName;
    }

    public Login(String userName) {
        this.userName = userName;
    }

    public void setID(String id) {
        ID = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getID() {
        return ID;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
