/*
Nadav Horowitz CS211 5/25/2022

This program simulates a calculator implemented by using a stack.
The calculator processes integer inputs and accepted operator inputs.
The calculator prints the end result to the console.

*/
import java.util.*;
public class Calculator {
	
	private static Stack<Integer> inputStack = new Stack<Integer>(); 
	
	public static void main(String[] args) {		
		boolean continueProgram = true;	
		
		while(continueProgram) {
				String inputLine = takeInput();
				continueProgram = processInput(inputLine);
		}
	}

	//Process user input according to input category (operator or integer)
	public static boolean processInput(String inputLine) {
		int result = 0;
		Scanner inputScan = new Scanner(inputLine);

		while(inputScan.hasNext()) {			
			if(inputScan.hasNextInt()) {
				inputStack.push(inputScan.nextInt());
			} 
			
			else {
				String operator = inputScan.next();		
				
				boolean validToken = validateToken(operator);
				if(!validToken) {
					System.out.println("Invalid input");
				} 
				
				else if(operator.equals("\\")) {
						System.out.println("Result: " + result);
						return false;
					} 
				
				else {			
					result = processOperator(operator, result);				
				}
			}
		}
		
		System.out.println("Please finish expression with a backslash \"\\\"");
		return true;
	}
	
	//Process operator input, update stack and return result
	public static int processOperator(String operator, int result) {		
		if(operator.equals("<<")) {
			System.out.println(inputStack);
		}
		
		else if(inputStack.size() == 0) {
			System.out.println("Missing operands");
		}
		
		else if(operator.equals("^")) {
			result = inputStack.pop();
			System.out.println(result);
		}
		
		else if(inputStack.size() < 2) {
			System.out.println("Missing operands");
		} 
		
		else {
			int top = inputStack.pop();
			int secondToTop = inputStack.pop();
			
			if(operator.equals("+")) {
				result = top + secondToTop;
				inputStack.push(result);
			}
			
			else if(operator.equals("-")) {
				result = top - secondToTop;
				inputStack.push(result);
			}
			
			else if(operator.equals("*")) {
				result = top * secondToTop;
				inputStack.push(result);
			}
			
			else if(operator.equals("/")) {
				result = top / secondToTop;
				inputStack.push(result);
			}
			
			else if(operator.equals("%")) {
				result = top % secondToTop;
				inputStack.push(result);
			}
		}
		return result;
	}
	
	//Ensure input is an accepted operator
	public static boolean validateToken(String operator) {
	    if ((operator.equals("+") || operator.equals("-") || operator.equals("*")
	            || operator.equals("/") || operator.equals("%") || operator.equals("^")
	            || operator.equals("<<") || operator.equals("\\"))) {

	        return true;
	    } else {
	        return false;
	    }
	}
	
	//Prompt for and accept input from console
	public static String takeInput() {
		Scanner console = new Scanner(System.in);
		
		System.out.println("Enter the expression:");
		String inputLine = console.nextLine();
		
		return inputLine;	
	}
}
