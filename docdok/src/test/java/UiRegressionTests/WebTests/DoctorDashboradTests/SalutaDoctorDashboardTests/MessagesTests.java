package UiRegressionTests.WebTests.DoctorDashboradTests.SalutaDoctorDashboardTests;

import Entities.Doctor;
import Entities.Patient;
import Framework.DataProviderClass;
import PageObjects.DoctorHomePage;
import PageObjects.LoginPage;
import UiRegressionTests.ChLoginBaseTest;
import org.testng.annotations.Test;

public class MessagesTests extends ChLoginBaseTest {

    @Test(dataProvider = "login-doctor-And-patient-data", dataProviderClass = DataProviderClass.class)
    public void verifySendingMessageValidation(Doctor doctor, Patient patient) {

        //login the doctor
        LoginPage loginPage= new LoginPage(driver);
        loginPage.loginAsDoctor(doctor);

        //go to patient tab, search the patient by its email,then click on patient
        DoctorHomePage doctorHomePage = new DoctorHomePage(driver);
        doctorHomePage.selectPatient(patient);

        //send the messsage to the searched patient
        doctorHomePage.sendMessageToPatient();
    }
}
