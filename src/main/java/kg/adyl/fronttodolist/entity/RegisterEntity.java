package kg.adyl.fronttodolist.entity;

public class RegisterEntity {
    private String userName;
    private String password;

    public RegisterEntity() {
    }

    public RegisterEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
