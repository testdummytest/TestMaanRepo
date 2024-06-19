package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PatientHomePage extends BasePage{
    public PatientHomePage(WebDriver driver) {
        super(driver);
    }

    public void verifyThatTheUserLoggedInSuccessfully() {
        agreeTermsAndConditions();
        try {
            driver.findElement(By.className("chat-content"));
            driver.findElement(By.id("chat-input"));
            driver.findElement(By.className("events"));
        } catch (Exception e) {
            Assert.fail("There is an issue in the patient home page, please check! ");
        }
    }

}
