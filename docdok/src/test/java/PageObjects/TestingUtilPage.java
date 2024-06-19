package PageObjects;

import Entities.Admin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestingUtilPage extends BasePage {

    static String activationUrl;

    public TestingUtilPage(WebDriver driver) {
        super(driver);
    }

    public void openActivationUrlByTestingUtil(Admin admin) {
        openTestingUtilPage();
        loginAsAdminInTestingUtil(admin);
        goToTheEmailsAndClickOnTheLastEmail();
        openUrlInNewTab(getActivationUrlForTheLastEmail(), 1);
        logoutFromUser();
        openUrlInTheSameTab(activationUrl);
    }

    public void openTestingUtilPage() {
        openUrlInTheSameTab("https://docdokutil.s3.eu-central-1.amazonaws.com/utilityTests.html");
    }

    private void loginAsAdminInTestingUtil(Admin admin) {
        WebElement envQA = driver.findElement(By.id("envQA"));
        click(envQA);
        loginAsUser(admin.getEmail(), admin.getPassword());
    }

    private void goToTheEmailsAndClickOnTheLastEmail() {
        WebElement getEmails = driver.findElement(By.xpath("//*[contains(text(), 'get\n" +
                "\t\t\t\temails')]"));
        click(getEmails);
        WebElement emails = driver.findElement(By.id("emails"));
        Integer emailsSize = emails.findElements(By.tagName("li")).size();
        WebElement lastEmail = emails.findElements(By.tagName("li")).get(emailsSize - 1);
        click(lastEmail);
    }

    private String getActivationUrlForTheLastEmail() {
        WebElement emails = driver.findElement(By.id("emails"));
        Integer emailsSize = emails.findElements(By.tagName("li")).size();
        WebElement lastEmail = emails.findElements(By.tagName("li")).get(emailsSize - 1);
        WebElement iframe = lastEmail.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);
        activationUrl = driver.findElement(By.xpath("//a[contains(@href, 'https://qa.dev.docdok.ch/rest/user/api/users/onboarding/?token')]")).getAttribute("href");
        return activationUrl;
    }

}
