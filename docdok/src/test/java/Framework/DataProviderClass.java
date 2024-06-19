package Framework;

import Entities.Admin;
import Entities.Doctor;
import Entities.Patient;
import org.testng.annotations.DataProvider;
import java.util.Random;
import java.util.UUID;

public class DataProviderClass {

    public static String getRandomMobileNumber() {
        Random rand = new Random();
        Integer randomNum = 11 + rand.nextInt((999999999 - 11) + 1);
        return randomNum.toString();
    }

    public static String getUniqueId() {
        return String.format("%s_%s", UUID.randomUUID().toString().substring(0, 5), System.currentTimeMillis() / 1000);
    }

    public static String getRandomEmailForSaluta() {
        return String.format("%s@%s", getUniqueId(), "saluta.ch");
    }

    public static String getRandomEmailForVinzenz() {
        return String.format("%s@%s", getUniqueId(), "vinzenz.de");
    }

    public static String getRandomEmailForHyCare() {
        return String.format("%s@%s", getUniqueId(), "HyCare.ch");
    }

    @DataProvider(name = "create-saluta-patient-by-self-Registration-data")
    public static Object[][] getSalutaPatientData() {
        Object patient = new Patient("automation", "test", Generate.date(25), getRandomEmailForSaluta(), getRandomMobileNumber(), "12345678", "12345678", "12345678");
        Object admin = new Admin("admin@automation.ch", "12345678Aa");
        return new Object[][]{{patient, admin}};
    }

    @DataProvider(name = "create-Vinzenz-patient-by-self-Registration-data")
    public static Object[][] getVinzenzPatientData() {
        Object patient = new Patient("automation", "test", Generate.date(25), getRandomEmailForVinzenz(), getRandomMobileNumber(), "12345678", "12345678", "12345678", "1234");
        Object admin = new Admin("admin@automation.ch", "12345678Aa");
        return new Object[][]{{patient, admin}};
    }

    @DataProvider(name = "login-patient-data")
    public static Object[][] getLoginPatientData(){
        Object patient = new Patient("patient@saluta.tests","12345678");
        return new Object[][]{{patient}};
    }

    @DataProvider(name = "login-doctor-And-patient-data")
    public static Object[][] getLoginDoctorAndPatientData(){
        Object doctor = new Doctor("test@saluta.test", "12345678");
        Object patient = new Patient("patient@saluta.tests");

        return new Object[][]{{doctor,patient}};
    }

    @DataProvider(name = "create-saluta-patient-by-doctor-data")
    public static Object[][] getSalutaPatientDataByDoctor() {
        Object patient = new Patient("automation", "test", Generate.date(25), getRandomEmailForSaluta(), "+" + getRandomMobileNumber(), "12345678", "12345678", "12345678");
        Object admin = new Admin("admin@automation.ch", "12345678Aa");
        Object doctor = new Doctor("test@saluta.test", "12345678");
        return new Object[][]{{patient, admin, doctor}};
    }

    @DataProvider(name = "create-hycare-patient-by-doctor-data")
    public static Object[][] getHyCarePatientDataByDoctor() {
        Object patient = new Patient("Amany", "HyCare", Generate.date(25), "hy6@care.test", "+" + getRandomMobileNumber(), "12345678", "12345678", "12345678");
        Object doctor = new Doctor("docdok.tests+4@gmail.com", "12345678");
        Object admin = new Admin("admin@automation.ch", "12345678Aa");
        return new Object[][]{{patient, admin, doctor}};
    }

    @DataProvider(name = "existing-hycare-patient-and-doctor-data")
    public static Object[][] getExistingHyCarePatientAndDoctorData() {
        Object patient = new Patient("automation@HyCare.test", "12345678");
        Object doctor = new Doctor("hycare@phy.com", "12345678");
        return new Object[][]{{patient, doctor}};
    }

    @DataProvider(name = "existing-hycare-patient-data")
    public static Object[][] getExistingHyCarePatientData() {
        Object patient = new Patient("automation@HyCare.test", "12345678");
        return new Object[][]{{patient}};
    }
}
