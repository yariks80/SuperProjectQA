package com.it.helpers;

import com.it.pages.LoginPage;
import com.it.users.User;

public class LoginHelper extends LoginPage {

    public void login(String login,String password) {
        setInputLogin(login);
        setInputPassword(password);
        btnClickLogin();

    }
    public void login(User user) {
        log.info(String.format("Login user name - %s , password - %s .",user.userName,user.password));
        login(user.userName,user.password);

    }
}
