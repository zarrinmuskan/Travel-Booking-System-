package travelbookingsystem;

import javax.swing.*;

public class EmailRecovery implements PasswordRecovery {
    @Override
    public void recoverPassword() {
        JOptionPane.showMessageDialog(null, "Recovery email sent to your registered email address.");
    }
}
