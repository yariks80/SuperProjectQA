package com.it.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MyTest extends BaseTest {
    @Test
    public void test1() {
        app.login.login(validUser);
        app.common.takeScreenShot();
        Assert.assertEquals(app.dashboard.getLbUserEmail(), validUser.email);

    }

}
