package PageObjects;

import Entities.Patient;
import Framework.Generate;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class DoctorHomePage extends BasePage {
    public DoctorHomePage(WebDriver driver) {
        super(driver);
    }

    public void selectPatient(Patient patient) {
        clickOnPatientsTab();
        WebElement searchButton = driver.findElement(By.id("patient-search-bar-search-button"));
        click(searchButton);
        fillTextById(patient.getEmail(), "patient-search-bar-search-field");
        waitFewSeconds(2000);
        WebElement myPatients = driver.findElement(By.id("myPatients"));
        WebElement myPatient = myPatients.findElement(By.xpath(("//a[contains(@href, '/private/app/patients/PAT')]")));
        click(myPatient);
    }

    private void clickOnPatientsTab() {
        WebElement patientsTab = driver.findElement(By.id("menu.patients"));
        click(patientsTab);
    }

    public void addAnEventForTodayDate() {
        WebElement addButton = driver.findElement(By.id("add-events-button"));
        click(addButton);
        WebElement addEventButton = driver.findElement(By.id("add-events-particular-button"));
        click(addEventButton);
        fillEventData();
        WebElement saveButton = driver.findElement(By.id("save"));
        click(saveButton);
    }

    private void fillEventData() {
        fillTextById("automationTest", "editEvent-name");
        fillTextById("my event " + Generate.todayDate(), "editEvent-notes");
    }

    public void verifyThatTheEventAddedSuccessfully() {
        WebElement closeSuccessfulMessageButton = driver.findElement(By.className("jss57"));
        click(closeSuccessfulMessageButton);
        clickOnTheLastEvent();
        String notes = driver.findElement(By.id("editEvent-notes")).getText();
        Assert.assertEquals(notes, "my event " + Generate.todayDate());
    }

    public void updateEvent() {
        WebElement startTime = driver.findElement(By.name("startTime"));
        click(startTime);
        startTime.sendKeys(Keys.ARROW_UP);
        fillTextByName("myLocation", "location");
        fillTextById("my event " + Generate.dateForAppointment(1), "editEvent-notes");
    }

    public void verifyThatTheEventUpdatedSuccessfully() {
        String getStartTime = driver.findElement(By.name("startTime")).getAttribute("value");
        String getLocation = driver.findElement(By.name("location")).getAttribute("value");
        String getNotes = driver.findElement(By.id("editEvent-notes")).getText();

        WebElement saveButton = driver.findElement(By.id("save"));
        click(saveButton);
        WebElement closeSuccessfulMessageButton = driver.findElement(By.className("jss57"));
        click(closeSuccessfulMessageButton);
        clickOnTheLastEvent();

        Assert.assertEquals(getStartTime, driver.findElement(By.name("startTime")).getAttribute("value"));
        Assert.assertEquals(getLocation, "myLocation");
        Assert.assertEquals(getNotes, "my event " + Generate.dateForAppointment(1));

        WebElement closeEventButton = driver.findElement(By.id("close"));
        click(closeEventButton);
    }

    private void clickOnTheLastEvent() {
        WebElement today = driver.findElements(By.className("fc-day-today")).get(2);
        List<WebElement> events = today.findElements(By.className("fc-timegrid-event-harness-inset"));
        WebElement theLastEvent = events.get(events.size() - 1);
        click(theLastEvent);
    }

    public void deleteEventAndVerifyThatTheEventIsDeleted() {
        List<WebElement> events = driver.findElements(By.className("fc-timegrid-event-harness-inset"));
        Integer eventsSize = events.size();
        WebElement theLastEvent = events.get(events.size() - 1);
        click(theLastEvent);
        deleteEvent();
        List<WebElement> eventsAfterDeleteAnEvent = driver.findElements(By.className("fc-timegrid-event-harness-inset"));
        Integer eventsSizeAfterDeleteAnEvent = eventsAfterDeleteAnEvent.size();

        Assert.assertEquals(eventsSizeAfterDeleteAnEvent, eventsSize - 1, "The event does not deleted ! ");
    }

    public void deleteEvent() {
        WebElement deleteButton = driver.findElement(By.xpath("//*[contains(text(), 'Delete')]"));
        click(deleteButton);

        driver.switchTo().alert().accept();

        waitFewSeconds(2000);
    }

    public void addAnEventInPastTime() {
        WebElement addButton = driver.findElement(By.id("add-events-button"));
        click(addButton);
        WebElement addEventButton = driver.findElement(By.id("add-events-particular-button"));
        click(addEventButton);
        fillTextById("automationTest", "editEvent-name");
        WebElement startTime = driver.findElement(By.name("startTime"));
        click(startTime);
        startTime.sendKeys(Keys.ARROW_DOWN);
        fillTextById("status done " + Generate.dateForAppointment(10), "editEvent-notes");
        WebElement saveButton = driver.findElement(By.id("save"));
        click(saveButton);
    }

    public void verifyEventStatus() {
        WebElement closeSuccessfulMessageButton = driver.findElement(By.className("jss57"));
        click(closeSuccessfulMessageButton);
        String eventColor = driver.findElement(By.className("fc-event-today")).getAttribute("style");
        Assert.assertEquals(eventColor,"background-color: rgb(255, 191, 131);","event color is not correct! ");
        clickOnTheFirstEvent();
        String notes = driver.findElement(By.id("editEvent-notes")).getText();
        Assert.assertEquals(notes, "status done " + Generate.dateForAppointment(10));
        WebElement done = driver.findElement(By.id("DONE"));
        String status = done.findElement(By.className("jss62")).getAttribute("class");
        Integer length = status.length();
        if (length < 40) {
            Assert.fail("The event status should be Done! ");
        }
        deleteEvent();
    }

    private void clickOnTheFirstEvent() {
        WebElement today = driver.findElements(By.className("fc-day-today")).get(2);
        WebElement event = today.findElement(By.className("fc-timegrid-event-harness-inset"));
        click(event);
    }

    public void verifyTheTheWeeklyViewIsAsExpected() {
        Integer daysNumber = driver.findElements(By.className("fc-daygrid-day-bottom")).size();
        Assert.assertEquals(daysNumber,7);
    }

    public void verifyTheTheDailyViewIsAsExpected() {
        WebElement dayButton = driver.findElement(By.className("fc-timeGridDay-button"));
        click(dayButton);
        Integer daysNumber = driver.findElements(By.className("fc-daygrid-day-bottom")).size();
        Assert.assertEquals(daysNumber,1);
    }

    public void verifyTheTheMonthlyViewIsAsExpected() {
        WebElement dayButton = driver.findElement(By.className("fc-dayGridMonth-button"));
        click(dayButton);
        Integer daysNumber = driver.findElements(By.className("fc-daygrid-day-bottom")).size();
        Assert.assertEquals(daysNumber,42);
    }

    public void sendSurveyToThePatientAndVerifyTheSurveyWasSentSuccessfully() {
        WebElement surveysButton = driver.findElements(By.className("tab")).get(2);
        click(surveysButton);
        APIsPage apIsPage = new APIsPage(driver);
        int SurveysNumberBeforeAddingSurveyByCallingAPI = apIsPage.getSurveysNumber();
        String SurveysNumberBeforeAddingSurvey = getNumberOfSurveys();
        sendSurvey();
        String SurveysNumberAfterAddedSurvey = getNumberOfSurveys();
        int SurveysNumberAfterAddedSurveyByCallingAPI = apIsPage.getSurveysNumber();
        Assert.assertNotEquals(SurveysNumberAfterAddedSurvey, SurveysNumberBeforeAddingSurvey);
        Assert.assertEquals(SurveysNumberBeforeAddingSurveyByCallingAPI +1, SurveysNumberAfterAddedSurveyByCallingAPI);
    }

    public void sendSurveyToThePatient() {
        WebElement surveysButton = driver.findElements(By.className("tab")).get(2);
        click(surveysButton);
        sendSurvey();
    }

    private String getNumberOfSurveys() {
        WebElement element = driver.findElements(By.className("sc-fjdhpX")).get(2);
        String element2 = element.findElements(By.tagName("span")).get(3).getText();
        String numberOfSurveys = element2.substring(element2.lastIndexOf(' ') + 1);
        return numberOfSurveys;
    }

    private void sendSurvey() {
        WebElement sendSurveyButton = driver.findElement(By.id("send-survey-button"));
        click(sendSurveyButton);
        fillTextById("Gesundheitsfragebogen final", "sendSurveyFromPatient-surveys-search-field");
        WebElement agreeCheckbox = driver.findElement(By.cssSelector("input[type='checkbox']"));
        click(agreeCheckbox);
        WebElement submitButton = driver.findElement(By.id("save"));
        click(submitButton);
    }

    public void createPatientByTheDoctor(Patient patient) {
        clickOnPatientsTab();
        clickOnCreatePatientButton();
        fillMandatoryFields(patient);
        clickOnSaveButton();
        WebElement closeButton = driver.findElements(By.className("sc-ifAKCX")).get(1);
        click(closeButton);
        logoutFromUser();
    }

    public void createPatientByHyCareDoctor(Patient patient) {
        clickOnPatientsTab();
        clickOnCreatePatientButton();
        fillMandatoryHyCareFields(patient);
        chooseIcdCode();
        clickOnSaveButton();
        WebElement closeButton = driver.findElement(By.className("sc-ifAKCX"));
        click(closeButton);
        logoutFromUser();
    }

    public void createPatientByHyCareDoctorForMobile(Patient patient) {
        clickOnPatientsTab();
        clickOnCreatePatientButton();
        fillMandatoryHyCareFields(patient);
        chooseIcdCode();
        clickOnSaveButton();
    }

    private void chooseIcdCode() {
        WebElement createOnPEPButton = driver.findElement(By.id("createPatient-pepIntegration"));
        click(createOnPEPButton);
        WebElement sibling = driver.findElement(By.id("createPatient-disease"));
        WebElement selectDisease = sibling.findElement(By.xpath("preceding-sibling::div[1]"));
        click(selectDisease);
        WebElement icdCode = driver.findElement(By.tagName("li"));
        click(icdCode);
    }

    private void fillMandatoryHyCareFields(Patient patient) {
        fillTextById("Mrs.", "createPatient-salutation");
        setFirstName(patient.getFirstName());
        setLastName(patient.getLastName());
        setGender();
        setBirthdate(patient.getBirthDate());
        setMobileNumber(patient.getMobileNumber());
        setEmail(patient.getEmail());
        setLanguage();
    }


    private void clickOnSaveButton() {
        WebElement saveButton = driver.findElement(By.id("save"));
        click(saveButton);
        waitFewSeconds(2500);
    }

    private void clickOnCreatePatientButton() {
        WebElement dayButton = driver.findElement(By.id("createPatient-table"));
        click(dayButton);
    }

    private void fillMandatoryFields(Patient patient) {
        setFirstName(patient.getFirstName());
        setLastName(patient.getLastName());
        setGender();
        setBirthdate(patient.getBirthDate());
        setMobileNumber(patient.getMobileNumber());
        setEmail(patient.getEmail());
        setLanguage();
    }

    private void setLanguage() {
        WebElement languageButton = driver.findElement(By.id("createPatient-langKey"));
        WebElement language = languageButton.findElement(By.xpath("preceding-sibling::div[1]"));
        click(language);
        WebElement deButton = driver.findElement(By.id("de"));
        click(deButton);
    }

    private void setGender() {
        WebElement genderButton = driver.findElement(By.id("createPatient-gender"));
        WebElement gender = genderButton.findElement(By.xpath("preceding-sibling::div[1]"));
        click(gender);
        WebElement femaleButton = driver.findElement(By.id("FEMALE"));
        click(femaleButton);
    }

    private void setBirthdate(String birthdate) {
        fillTextById(birthdate, "createPatient-birthdate");
    }

    private void setMobileNumber(String mobileNumber) {
        fillTextById(mobileNumber, "createPatient-mobileNumber");
    }

    private void setEmail(String email) {
        fillTextById(email, "createPatient-email");
    }

    private void setLastName(String lastName) {
        fillTextByName(lastName, "lastName");
    }

    private void setFirstName(String firstName) {
        fillTextByName(firstName, "firstName");
    }

    public void sendMessageToPatient() {
        fillTextById(Generate.todayDate(), "chat-input");
        WebElement sendButton = driver.findElement(By.id("send-button"));
        click(sendButton);
    }

    public void sendVasSurveyToThePatient() {
        WebElement surveysButton = driver.findElements(By.className("tab")).get(2);
        click(surveysButton);
        WebElement sendSurveyButton = driver.findElement(By.id("send-survey-button"));
        click(sendSurveyButton);
        fillTextById("vas 12 ", "sendSurveyFromPatient-surveys-search-field");
        waitFewSeconds(2000);
        WebElement agreeCheckbox = driver.findElement(By.cssSelector("input[type='checkbox']"));
        click(agreeCheckbox);
        WebElement submitButton = driver.findElement(By.id("save"));
        click(submitButton);
    }

}
