package Entities;

public class User {

    private String firstName;
    private String lastName;
    private String birthDate;
    private String email;
    private String mobileNumber;
    private String password;

    public User(String firstName, String lastName, String birthDate, String email, String mobileNumber) {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setEmail(email);
        setMobileNumber(mobileNumber);
    }

    public User(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public User(String email) {
        setEmail(email);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
