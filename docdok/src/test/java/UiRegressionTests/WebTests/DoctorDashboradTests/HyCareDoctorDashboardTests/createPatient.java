package UiRegressionTests.WebTests.DoctorDashboradTests.HyCareDoctorDashboardTests;

import Entities.Admin;
import Entities.Doctor;
import Entities.Patient;
import Framework.DataProviderClass;
import PageObjects.DoctorHomePage;
import PageObjects.LoginPage;
import PageObjects.PatientHomePage;
import PageObjects.TestingUtilPage;
import UiRegressionTests.ChLoginBaseTest;
import org.testng.annotations.Test;

public class createPatient extends ChLoginBaseTest {

    @Test(dataProvider = "create-hycare-patient-by-doctor-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyThatTheDoctorCanCreatePatientSuccessfully(Patient patient, Admin admin, Doctor doctor) {
        
        // Login doctor => create patient => fill mandatory fields(fname,lname..) => then save => logout
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsDoctor(doctor);
        DoctorHomePage doctorHomePage = new DoctorHomePage(driver);
        doctorHomePage.createPatientByHyCareDoctor(patient);

        // admin is open the page, then login, last email click, get activation url from new page and open in new tab,
        // then logout, then agai open url in same tab
        TestingUtilPage testingUtilPage = new TestingUtilPage(driver);
        testingUtilPage.openActivationUrlByTestingUtil(admin);

        // patient can reset password, then verify patient by checkbox of con dition check or not, login or not by go to patient home page or not
        loginPage.activateTheUserBySettingNewPassword(patient);
        PatientHomePage patientHomePage = new PatientHomePage(driver);
        patientHomePage.verifyThatTheUserLoggedInSuccessfully();

    }
}
