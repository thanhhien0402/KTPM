package shopvn.model;

public class User {

    private String fullName;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String confirmPassword;
    private String birthDate;
    private String gender;
    private String referralCode;
    private boolean agreeTerms;

    public User(String fullName, String username, String email, String phone,
                String password, String confirmPassword,
                String birthDate, String gender,
                String referralCode, boolean agreeTerms) {

        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.birthDate = birthDate;
        this.gender = gender;
        this.referralCode = referralCode;
        this.agreeTerms = agreeTerms;
    }

    public String getFullName() { return fullName; }

    public String getUsername() { return username; }

    public String getEmail() { return email; }

    public String getPhone() { return phone; }

    public String getPassword() { return password; }

    public String getConfirmPassword() { return confirmPassword; }

    public String getBirthDate() { return birthDate; }

    public String getGender() { return gender; }

    public String getReferralCode() { return referralCode; }

    public boolean isAgreeTerms() { return agreeTerms; }
}