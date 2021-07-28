package service;

import model.User;

public interface UserCreator {

    String LOGIN = "testdata.user.login";
    String PASSWORD = "testdata.user.password";

    User createUser();
}
