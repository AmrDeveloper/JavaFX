package sample.Controllers;

import javax.swing.*;

public final class Debugger {

    /**
     * Show Error Dialog Message
     */
    public static void showErrorDialog(String dialogTitle , String dialogMessage) {
        JOptionPane.showMessageDialog(
                new JFrame(),
                dialogMessage,
                dialogTitle,
                JOptionPane.ERROR_MESSAGE);
    }
}
