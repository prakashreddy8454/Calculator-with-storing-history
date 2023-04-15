import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
public class App {
    private ArrayList<String> history = new ArrayList<>();

    public void add(double num1, double num2) {
        double result = num1 + num2;
        System.out.println(num1 + " + " + num2 + " = " + result);
        history.add(num1 + " + " + num2 + " = " + result);
    }

    public void subtract(double num1, double num2) {
        double result = num1 - num2;
        System.out.println(num1 + " - " + num2 + " = " + result);
        history.add(num1 + " - " + num2 + " = " + result);
    }

    public void multiply(double num1, double num2) {
        double result = num1 * num2;
        System.out.println(num1 + " * " + num2 + " = " + result);
        history.add(num1 + " * " + num2 + " = " + result);
    }

    public void divide(double num1, double num2) {
        double result = num1 / num2;
        System.out.println(num1 + " / " + num2 + " = " + result);
        history.add(num1 + " / " + num2 + " = " + result);
    }

    public void displayHistory() {
        System.out.println("History:");
        for (String calc : history) {
            System.out.println(calc);
        }
    }

    public void power(double num1, double num2) {
        double result = Math.pow(num1, num2);
        System.out.println(num1 + " ^ " + num2 + " = " + result);
        history.add(num1 + " ^ " + num2 + " = " + result);
    }

    public void sin(double num) {
        double result = Math.sin(num);
        System.out.println("sin(" + num + ") = " + result);
        history.add("sin(" + num + ") = " + result);
    }

    public void cos(double num) {
        double result = Math.cos(num);
        System.out.println("cos(" + num + ") = " + result);
        history.add("cos(" + num + ") = " + result);
    }

    public void sqrt(double num) {
        double result = Math.sqrt(num);
        System.out.println("sqrt(" + num + ") = " + result);
        history.add("sqrt(" + num + ") = " + result);
    }

    public void log(double num) {
        double result = Math.log(num);
        System.out.println("log(" + num + ") = " + result);
        history.add("log(" + num + ") = " + result);
    }

    public void tan(double num) {
        double result = Math.tan(num);
        System.out.println("tan(" + num + ") = " + result);
        history.add("tan(" + num + ") = " + result);
    }

    public void factorial(double num) {
        int result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        System.out.println(num + "! = " + result);
        history.add(num + "! = " + result);
    }

    public double evaluateLongExpression(String expression) {
        List<String> tokens = tokenize(expression);
        Stack<Double> operandStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();
        
        for (String token : tokens) {
            if (isNumeric(token)) {
                double value = Double.parseDouble(token);
                operandStack.push(value);
            } else if (isOperator(token)) {
                while (!operatorStack.isEmpty() && hasHigherPrecedence(token, operatorStack.peek())) {
                    double b = operandStack.pop();
                    double a = operandStack.pop();
                    String op = operatorStack.pop();
                    double result = applyOperation(op, a, b);
                    operandStack.push(result);
                }
                operatorStack.push(token);
            } else {
                System.out.println("Invalid expression");
                return Double.NaN;
            }
        }
        
        while (!operatorStack.isEmpty()) {
            double b = operandStack.pop();
            double a = operandStack.pop();
            String op = operatorStack.pop();
            double result = applyOperation(op, a, b);
            operandStack.push(result);
        }
        
        if (operandStack.size() == 1) {
            double result = operandStack.pop();
            System.out.println("The expression evaluates to " + result);
            history.add("The expression evaluates to " + result);
            return result;
        } else {
            System.out.println("Invalid expression");
            return Double.NaN;
        }
    }
    
    private List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        
        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                builder.append(c);
            } else {
                if (builder.length() > 0) {
                    tokens.add(builder.toString());
                    builder.setLength(0);
                }
                tokens.add(Character.toString(c));
            }
        }
        
        if (builder.length() > 0) {
            tokens.add(builder.toString());
        }
        
        return tokens;
    }
    
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private boolean isOperator(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }
    
    private boolean hasHigherPrecedence(String op1, String op2) {
        if ((op1.equals("*") || op1.equals("/")) && (op2.equals("+") || op2.equals("-"))) {
            return true;
        } else {
            return false;
        }
    }
    
    private double applyOperation(String op, double a, double b) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                return Double.NaN;
        }
    }
    


    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        App calculator = new App();
        restart :
        while (true) {
           
            System.out.println(
                    "Enter an operation (+, -, *, / , ^, sin , cos , sqrt , log , tan , !  , expr for expression ) or type 0 to exit or type \"history\" to see previous calculations:");
            System.out.println("For sin , cos , tan , sqrt , log,  !  - Write first input as number for you want to use ,  write 0 for other number  ");
           System.out.print(">>  ");
            String operator = scanner.nextLine();

            if (operator.equals("history")) {
                calculator.displayHistory();
                continue;
            }
            if(operator.equals("expr")){
    System.out.println("Enter String to Evaluate");
                String expression = scanner.nextLine();
                calculator.evaluateLongExpression(expression);
                   continue restart;
            }
    if (operator.equals("0"))return;
    

         

            System.out.println("Enter numbers:");
            double num1 = scanner.nextDouble();
            double num2 = scanner.nextDouble();
            scanner.nextLine();

            switch (operator) {
               
                case "+":
                    calculator.add(num1, num2);
                    break;
                case "-":
                    calculator.subtract(num1, num2);
                    break;
                case "*":
                    calculator.multiply(num1, num2);
                    break;
                case "/":
                    calculator.divide(num1, num2);
                    break;
                case "^":
                    calculator.power(num1, num2);
                    break;
                case "sin":
                    calculator.sin(num1);
                    break;
                case "cos":
                    calculator.cos(num1);
                    break;
                case "sqrt":
                    calculator.sqrt(num1);
                    break;
                case "log":
                    calculator.log(num1);
                    break;
                case "tan":
                    calculator.tan(num1);
                    break;
                case "!":
                    calculator.factorial(num1);
                    break;
                
                default:
                    System.out.println("Invalid operator.");
                    break;
            }
        }
    }
}
