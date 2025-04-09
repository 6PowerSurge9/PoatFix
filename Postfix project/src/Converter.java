import java.util.Scanner;
import java.util.Stack;

public class Converter {
	public static void main(String args[]) {
		Scanner scnr = new Scanner(System.in);
		String x;
		System.out.println("Please enter a math eqation: ");
		x = scnr.nextLine();
		System.out.print(converter(x));
	}

	private static String converter( String x) {
		x = x + " ";
		Stack<Character> charstack = new Stack<>();
		StringBuilder stringBuilder = new StringBuilder();
		
		for(int i = 0; i < x.length() - 1; i++) {
			
			if (Character.isDigit(x.charAt(i)) || x.charAt(i) == '.' ) {     
				int intnum = i;// first implement ignore spaces
				while(Character.isDigit(x.charAt(i)) || x.charAt(i) == '.') {
					i++;
				}
				
				stringBuilder.append(x.substring(intnum, i)).append(" ");
				i--;
			}
			else {
				
				if(x.charAt(i) == '(') {
					charstack.push(x.charAt(i));
				}
				else if (x.charAt(i) == '*' || x.charAt(i) ==  '/' || x.charAt(i) ==  '+' || x.charAt(i) == '-') {
					while(!charstack.isEmpty() && charstack.peek() != '(' && hasPrecedence(charstack.peek(), x.charAt(i))) {
						
						stringBuilder.append(charstack.pop().toString()).append(' ');//write it to operator
					}
					charstack.push(x.charAt(i));

				}
				else if(x.charAt(i) == ')') {
					while(!charstack.isEmpty() && charstack.peek() != '(') {
						stringBuilder.append(charstack.pop().toString()).append(' ');//write it to the operator
					}
					charstack.pop();
				}
			}
			
		}
		while(!charstack.isEmpty()) {
			stringBuilder.append(charstack.pop().toString()).append(' ');
		}
		return stringBuilder.toString();
	}
	
	public static boolean hasPrecedence(char c1, char c2){
		if(c1 == '*' || c1 == '/') {
			return true;
		}
		
		if((c1 == '+' || c1 == '-') && (c2 == '+' || c2 == '-')) {
			return true;
		}
		
		return false;
		
	}
}
