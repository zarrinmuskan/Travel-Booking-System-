package travelbookingsystem;

public class PasswordRecoveryFactory {
    public static PasswordRecovery getRecoveryMethod(String type) {
        if (type.equalsIgnoreCase("email")) {
            return new EmailRecovery();
        } else if (type.equalsIgnoreCase("phone")) {
            return new PhoneRecovery();
        } else {
            throw new IllegalArgumentException("Invalid recovery method");
        }
    }
}
