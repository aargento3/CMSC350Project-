package cmsc350project1;

import java.util.*;

/**
 * 
 * @author AArgento
 * @date 19 October 2017
 * @class CMIS 350
 * @purpose Handle all required stack and arithmetic operations for InFix
 *          Expression Calculator. Determine precedence of operators. Throw 
 *          DivideByZero exception.
 */

public class InFix {
        Stack<String> operandStack;
        Stack<String> operatorStack;
        
        InFix(){
            operandStack = new Stack <> ();
            operatorStack = new Stack <> ();     
        }
        
        public int evalExp(String exp) throws DivideByZero{
            //tokenize the string containing the expression
            StringTokenizer st = new StringTokenizer(exp, "+-*/()",true);
            boolean fail = false;

            try{
                while (st.hasMoreTokens()){                  
                    String token = st.nextToken();
                    
                    //if token is an operand push it onto the operand stack
                    if (!token.equals("+") && !token.equals("-") &&
                            !token.equals("*") && !token.equals("/") &&
                            !token.equals("(") && !token.equals(")")) {
                        operandStack.push(token);
                    }
                    
                    //if token is a left paranthesis push onto the operator stack
                    else if (token.equals("(")){
                        operatorStack.push(token);
                    }
                    
                    //if token is a right paranthesis push onto the operator stack
                    else if (token.equals(")")){

                        //while top of operator stack is not left paranthesis
                        while(!operatorStack.peek().equals("(")){

                            performOperation (exp);
                        } 
                        operatorStack.pop();
                    }
                    //if token is operator
                    else if (token.equals("+") || token.equals("-") ||
                            token.equals("*") || token.equals("/")){
                        
                        //if operator stack is not empty and top of stack is same
                            // or higher precedence then current operator 
                        while (!operatorStack.empty() && 
                                precedence(operatorStack.peek(), token)){
                            performOperation(exp);
                        }
                        operatorStack.push(token);
                    }                  
                }
                
                //while operator stack is not empty
                while(!operatorStack.empty()){                 
                    performOperation (exp);
                }
                
            } catch (EmptyStackException e) {
                fail = true;
            }
            
            if (fail == false)
                return Integer.parseInt(operandStack.pop());
            else
                return -1;
        } 
    
    //determine operator precedence at the top of the operator stack as compared
        //to precedence of current operator
    boolean precedence(String top, String current){
        int topP = -1;
        int currentP = -1;
        
        if ("+".equals(top) || "-".equals(top)){
            topP = 0;
        }
        if ("*".equals(top) || "/".equals(top)){
            topP = 1;
        }
        if ("+".equals(current) || "-".equals(current)){
            currentP = 0;
        }
        if ("*".equals(current) || "/".equals(current)){
            currentP = 1;
        }
            return topP >= currentP;
    }
    
    //perform arithmetic operations
    public void performOperation(String exp) throws DivideByZero{
        int x = Integer.parseInt(operandStack.pop());
        int y = Integer.parseInt(operandStack.pop());
        String operator = operatorStack.pop();
        int z;

        switch (operator) {
            case "+":
                z = y + x;
                operandStack.push(Integer.toString(z));
                break;
            case "-":
                z = y - x;
                operandStack.push(Integer.toString(z));
                break;
            case "*":
                z = y * x;
                operandStack.push(Integer.toString(z));
                break;
            case "/":
                zeroCheck (x);
                z = y / x;
                operandStack.push(Integer.toString(z));
                break;
            default:
                break;
        }
    }//end performOperation
    
    //check for divison by zero. throw exception if true
    public void zeroCheck(int x) throws DivideByZero{
        int zeroX = x;
        
        if (zeroX == 0) {
            throw new DivideByZero();
        }  
    }//end zeroCheck

}//end InFix
