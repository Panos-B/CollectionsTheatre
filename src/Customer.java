public class Customer {
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
    private boolean isAdult;

    public Customer(String name, String password, String email, String phoneNumber, boolean isAdult) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isAdult = isAdult;
    }

    public void reserveSeat(Theatre.Seat seat, String customerName) {
        seat.reserve(customerName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }
}
