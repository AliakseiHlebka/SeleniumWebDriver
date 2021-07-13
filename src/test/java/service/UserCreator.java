package service;

import model.User;

public class UserCreator {

    public static final String LOGIN = "testdata.user.login";
    public static final String PASSWORD = "testdata.user.password";

    public static User withValidCredentials() {
        return new User(TestDataReader.getTestData(LOGIN),
                TestDataReader.getTestData(PASSWORD));
    }

    public static User withEmptyLogin() {
        return new User("", TestDataReader.getTestData(PASSWORD));
    }

    public static User withEmptyPassword() {
        return new User(TestDataReader.getTestData(LOGIN), "");
    }
}
