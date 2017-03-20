package cmsc350project1;

//import all required java libraries
import java.awt.event.*;
import javax.swing.*;

/**
 * 
 * @author AArgento
 * @date 19 March 2017
 * @class CMIS 350
 * @purpose Define all components required for GUI. Initialize GUI. Monitor GUI
 *          for user input. 
 * 
 */

public class CMSC350Project1 extends JFrame implements ActionListener {
    
    //define labels
    private final JLabel labelExpression = new JLabel("Enter Infix Expression:");
    private final JLabel labelResult = new JLabel("Result:");

    //define text fields
    private final JTextField textExpression = new JTextField();
    private final JTextField textResult = new JTextField();

    //define button
    private final JButton buttonEval = new JButton("Evaluate");
  
    //constructor
    CMSC350Project1() {
        
        //setup layout and position of all required components of GUI
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true); 
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(labelExpression)
               // .addComponent(buttonEval)
                .addComponent(labelResult))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(buttonEval))
            .addGroup(layout.createParallelGroup()
                .addComponent(textExpression)
                .addComponent(buttonEval)
                .addComponent(textResult))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelExpression)
                .addComponent(textExpression))
            .addGroup(layout.createParallelGroup()
                .addComponent(buttonEval))
            .addGroup(layout.createParallelGroup()
                .addComponent(labelResult)
                .addComponent(textResult))
        );
        
        //listener for eval button action
        buttonEval.addActionListener(this);
        
        //set behavior and particulars of GUI
        setTitle("Infix Expression Evaluator");
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
    }//end constructor
    
    @Override
    public void actionPerformed(ActionEvent arg){
        InFix InFixEvaluation = new InFix();
        
        String expression = textExpression.getText();
        
            try {
               int result = InFixEvaluation.evalExp(expression);

               if (result != -1)
                   textResult.setText(Integer.toString(result));
               else
                   textResult.setText("Error - Please Re-enter");
            } 
            catch (DivideByZero e){
                System.out.println("Expression w/ division by zero entered by "
                        + "user");
            }

    }//end actionPerfomed
    
    //main class initializes GUI
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new CMSC350Project1().setVisible(true);
        });   
    }//end main
  
}//end CMIS242Project4

