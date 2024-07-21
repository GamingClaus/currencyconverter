package gamingclaus;

import javax.swing.SwingUtilities;

public class CurrencyGui {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CurrencyFrame().setVisible(true));


    }
}
