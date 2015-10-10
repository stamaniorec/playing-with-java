import java.util.Stack;

public class ConvertToAndEvaluatePostfix {

	private String infixExpr;
	private String postfixExpr = "";
	
	/*
	 * RULES FOR CONVERSION
	 * print out numbers immediately
	 * never put a lighter item on a heavier item
	 * left parentheses go on the stack
	 * right parentheses pop off the stack until 
	 * 	you hit a left parentheses, and then remove the left parentheses
	 */
	
	public ConvertToAndEvaluatePostfix(String infix) {
		infixExpr = infix;
	}
	
	public void convertToPostfix() {
		Stack<Character> operators = new Stack<Character>();
		int curNum = 0;
		int tens = 1;
		for(int i = 0; i < infixExpr.length(); ++i) {
			if(infixExpr.charAt(i) >= '0' && infixExpr.charAt(i) <= '9') {
				curNum *= tens;
				curNum += (infixExpr.charAt(i) - '0');
				tens *= 10;
				if(i < infixExpr.length() - 1) {
					if(infixExpr.charAt(i+1) < '0' || infixExpr.charAt(i+1) > '9') {
						postfixExpr += Integer.toString(curNum) + " ";
						tens = 1;
						curNum = 0;
					} 
				} else {
					postfixExpr += Integer.toString(curNum) + " ";
				}
			} else if(infixExpr.charAt(i) == '+' || infixExpr.charAt(i) == '-' || 
					infixExpr.charAt(i) == '*' || infixExpr.charAt(i) == '/') {
				while(!operators.empty() && 
						precedence(infixExpr.charAt(i)) <= precedence(operators.peek())) {
					postfixExpr += operators.pop() + " ";
				}
				operators.add(infixExpr.charAt(i));
			} else if(infixExpr.charAt(i) == '(') {
				operators.add(infixExpr.charAt(i));
			} else if(infixExpr.charAt(i) == ')') {
				while(!operators.empty() && operators.peek() != '(') {
					postfixExpr += operators.pop() + " ";
				}
				operators.pop();
			}
		}
		while(!operators.empty()) {
			postfixExpr += operators.pop() + " ";
		}
		
		System.out.println("postfix = " + postfixExpr);
	}
	
	private int precedence(char operator) {
		if(operator == '(')
			return 0;
		if(operator == '+' || operator == '-') {
			return 1;
		}
		if(operator == '*' || operator == '/') {
			return 2;
		}
		return -1;
	}
	
	public int evaluateExpession() {
		Stack<Integer> s = new Stack<Integer>();
		
		int num = 0;
		int tens = 1;
		
		for(int i = 0; i < postfixExpr.length(); ++i) {
			if(postfixExpr.charAt(i) >= '0' && postfixExpr.charAt(i) <= '9') {
				num *= tens;
				num += postfixExpr.charAt(i) - '0';
				tens *= 10;
			} else if(postfixExpr.charAt(i) == ' ') {
				if(num != 0) { 
					s.add(num);
					num = 0;
					tens = 1;
				}
			} else if(postfixExpr.charAt(i) == '+') {
				s.add(s.pop() + s.pop());
			} else if(postfixExpr.charAt(i) == '-') {
				int a = s.pop();
				int b = s.pop();
				s.add(b - a);
			} else if(postfixExpr.charAt(i) == '*') {
				s.add(s.pop() * s.pop());
			} else if(postfixExpr.charAt(i) == '/') {
				int a = s.pop();
				int b = s.pop();
				s.add(b / a);
			}
			
		}
		
		return s.pop();
	}
	
	public static void main(String[] args) {
		// tests 
		// 5+3*4 -> 5 3 4 * +
		// 5*3+4 -> 5 3 * 4 -
		// 2*3-4/5 -> 2 3 * 4 5 / -
		// (((1 + 2) * 3) + 6) / (2 + 3) -> 1 2 + 3 * 6 + 2 3 + / 
		// 3+4*2/(1-5 )*2-3 -> 3 4 2 * 1 5 - / 2 * + 3 -
		// (52+1-2) -> 52 1 + 2 -
		ConvertToAndEvaluatePostfix obj = new ConvertToAndEvaluatePostfix("(((1 + 2) * 3) + 6) / (2 + 3)");
		obj.convertToPostfix();
		System.out.println("Value = " + obj.evaluateExpession());
	}
	
}
