package BankAccount;

public class CustomerDto {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String nic;

    public CustomerDto(Long customerId, String firstName, String lastName, String nic) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nic = nic;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
               "customerId=" + customerId +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", nic='" + nic + '\'' +
               '}';
    }
}
