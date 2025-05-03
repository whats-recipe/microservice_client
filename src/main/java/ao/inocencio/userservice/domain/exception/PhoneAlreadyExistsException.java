package ao.inocencio.userservice.domain.exception;

public class PhoneAlreadyExistsException  extends  RuntimeException {
    private final String phoneNumber;

    public PhoneAlreadyExistsException(String phoneNumber) {
        super("Phone number already in use: " + phoneNumber);
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
