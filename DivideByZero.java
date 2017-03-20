package cmsc350project1;

import javax.swing.JOptionPane;

/**
 * 
 * @author AArgento
 * @date 19 October 2017
 * @class CMIS 350
 * @purpose Define custom DivideByZero exception to be thrown by InFix class and 
 *          caught by main class
 * 
 */

public class DivideByZero extends Exception {

    public DivideByZero() {
        JOptionPane frame = new JOptionPane();
        JOptionPane.showMessageDialog(frame, "Division by zero error "
                + "\nPlease Re-enter Expression", "Division By Zero",
                JOptionPane.ERROR_MESSAGE);
    }
    
}//end DivideByZero