package travelbookingsystem;

import javax.swing.*;

public class PhoneRecovery implements PasswordRecovery {
    @Override
    public void recoverPassword() {
        JOptionPane.showMessageDialog(null, "Recovery code sent to your registered phone number.");
    }
}