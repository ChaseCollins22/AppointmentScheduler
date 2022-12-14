package Model;

/**
 * This class is the outline for a User object.
 */
public class Users {
    private String userName;
    private String password;
    private int userId;

    /**
     * This function creates a User object.
     * @param userId Id of the user.
     * @param userName Name of the user.
     * @param password Password of the user.
     */
    public Users(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /**
     * This function gets the user id from a User object.
     * @return User id integer.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * This function gets the name of a User object.
     * @return The name of a User.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This funciton gets the password of a User object.
     * @return A User's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * This function overrides the toString method.
     * @return The user id in a string.
     */
    @Override
    public  String toString() {
        return (String.valueOf(getUserId()));
    }
}
