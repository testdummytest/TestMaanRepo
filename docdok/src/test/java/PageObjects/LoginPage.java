package PageObjects;

import Entities.Doctor;
import Entities.Patient;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void activateTheUserBySettingNewPassword(Patient patient) {
        setCodeReceivedToTheNewUser(patient.getSmsCode());
        setNewPassword(patient.getPassword(), patient.getConfirmPassword());
    }

    public void loginAsPatient(Patient patient) {
        loginAsUser(patient.getEmail(), patient.getPassword());
    }

    public void loginAsDoctor(Doctor doctor) {
        loginAsUser(doctor.getEmail(), doctor.getPassword());
    }
}
