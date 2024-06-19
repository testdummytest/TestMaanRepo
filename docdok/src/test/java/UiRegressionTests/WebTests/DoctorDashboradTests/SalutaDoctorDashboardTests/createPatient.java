package UiRegressionTests.WebTests.DoctorDashboradTests.SalutaDoctorDashboardTests;

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

    @Test(dataProvider = "create-saluta-patient-by-doctor-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyThatTheDoctorCanCreatePatientSuccessfully(Patient patient, Admin admin, Doctor doctor) {
        LoginPage loginPage= new LoginPage(driver);
        loginPage.loginAsDoctor(doctor);
        DoctorHomePage doctorHomePage = new DoctorHomePage(driver);
        doctorHomePage.createPatientByTheDoctor(patient);
        TestingUtilPage testingUtilPage = new TestingUtilPage(driver);
        testingUtilPage.openActivationUrlByTestingUtil(admin);
        loginPage.activateTheUserBySettingNewPassword(patient);
        PatientHomePage patientHomePage = new PatientHomePage(driver);
        patientHomePage.verifyThatTheUserLoggedInSuccessfully();
    }
}
