package AjioPageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import dataProvider.ConfigFileReader;

public class HomePage {

    WebDriver driver;
    ConfigFileReader configFileReader;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        configFileReader= new ConfigFileReader();
    }

    public void navigateTo_HomePage() {
        driver.get(configFileReader.getApplicationUrl());
    }
}
