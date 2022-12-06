package Model;

public class Users {
    private String userName;
    private String password;
    private int userId;

    public Users(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public  String toString() {
        return (String.valueOf(getUserId()));
    }
}
