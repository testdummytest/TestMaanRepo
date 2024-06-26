package Entities;

public class Patient extends User {
    private String smsCode;
    private String confirmPassword;
    private String insuranceNumber;

    public Patient(String firstName, String lastName, String birthDate, String email, String mobileNumber, String smsCode, String password, String confirmPassword) {
        super(firstName, lastName, birthDate, email, mobileNumber);
        setSmsCode(smsCode);
        setPassword(password);
        setConfirmPassword(confirmPassword);
    }

    public Patient(String firstName, String lastName, String birthDate, String email, String mobileNumber, String smsCode, String password, String confirmPassword, String insuranceNumber) {
        super(firstName, lastName, birthDate, email, mobileNumber);
        setSmsCode(smsCode);
        setPassword(password);
        setConfirmPassword(confirmPassword);
        setInsuranceNumber(insuranceNumber);
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public Patient(String email, String password) {
        super(email, password);
    }

    public Patient(String email) {
        super(email);
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
