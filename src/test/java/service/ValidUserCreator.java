package service;

import model.User;
import model.ValidUser;

public class ValidUserCreator implements UserCreator {

    @Override
    public User createUser() {
        return new ValidUser(TestDataReader.getTestData(LOGIN),
                TestDataReader.getTestData(PASSWORD));
    }
}
