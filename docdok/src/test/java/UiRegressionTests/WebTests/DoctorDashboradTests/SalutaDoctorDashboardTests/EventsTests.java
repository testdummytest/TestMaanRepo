package UiRegressionTests.WebTests.DoctorDashboradTests.SalutaDoctorDashboardTests;

import Entities.Doctor;
import Entities.Patient;
import Framework.DataProviderClass;
import PageObjects.DoctorHomePage;
import PageObjects.LoginPage;
import UiRegressionTests.ChLoginBaseTest;
import org.testng.annotations.Test;

public class EventsTests extends ChLoginBaseTest {
    @Test(dataProvider = "login-doctor-And-patient-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyEventValidation(Doctor doctor, Patient patient) {

        //login as a doctor
        LoginPage loginPage= new LoginPage(driver);
        loginPage.loginAsDoctor(doctor);
        
        //search the patient and click(select) on that
        DoctorHomePage doctorHomePage = new DoctorHomePage(driver);
        doctorHomePage.selectPatient(patient);

        //event add and save on todays date
        doctorHomePage.addAnEventForTodayDate();

        //verify that event is added successfully
        doctorHomePage.verifyThatTheEventAddedSuccessfully();

        //update the event
        doctorHomePage.updateEvent();

        // verify eveent updated or not
        doctorHomePage.verifyThatTheEventUpdatedSuccessfully();

        //event delete and verify
        doctorHomePage.deleteEventAndVerifyThatTheEventIsDeleted();
    }

    @Test(dataProvider = "login-doctor-And-patient-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyEventStatus(Doctor doctor, Patient patient) {
        
        //login as doctor
        LoginPage loginPage= new LoginPage(driver);
        loginPage.loginAsDoctor(doctor);

        //search the patient and click(select) on that
        DoctorHomePage doctorHomePage = new DoctorHomePage(driver);
        doctorHomePage.selectPatient(patient);

        //add event at past tinme
        doctorHomePage.addAnEventInPastTime();

        //verify event status done or  not
        doctorHomePage.verifyEventStatus();
    }



    @Test(dataProvider = "login-doctor-And-patient-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyThatTheMonthlyWeeklyAndDailyViewsLookLikeTheyShouldBe(Doctor doctor, Patient patient) {
        
        //login as doctor
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsDoctor(doctor);

        //search the patient and click(select) on that
        DoctorHomePage doctorHomePage = new DoctorHomePage(driver);
        doctorHomePage.selectPatient(patient);
        

        doctorHomePage.verifyTheTheWeeklyViewIsAsExpected();
        
        doctorHomePage.verifyTheTheDailyViewIsAsExpected();
        doctorHomePage.verifyTheTheMonthlyViewIsAsExpected();
    }


}
