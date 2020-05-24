package com.it.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage  extends BasePage{



    @FindBy(xpath = "//span[@class='sn_menu_title']")
    private WebElement ldUserEmail;


    public String getLbUserEmail() {
     return ldUserEmail.getText();
    }

}

