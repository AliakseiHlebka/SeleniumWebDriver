package service;

import model.InvalidUser;
import model.User;

public class InvalidUserCreator implements UserCreator {

    @Override
    public User createUser() {
        return new InvalidUser(TestDataReader.getTestData(LOGIN));
    }
}
