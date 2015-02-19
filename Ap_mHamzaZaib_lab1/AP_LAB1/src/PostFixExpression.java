
import java.util.ArrayList;
import java.util.Arrays;



/**
* This class converts a given infix expression to postfix and then evaluates it
* @author Hamza
*
*/
public class PostFixExpression {

	private String infix;
	public PostFixExpression(String s){
		infix=s;
	}
	
	//converts infix expression to postfix where number represent matrix number
	//priority order -> (),/,*,-,+
	
	public String getPostFix(){
		//TODO implement this method
		boolean first=true;
		StringBuilder postfix = new StringBuilder();
		int length = infix.length(),breakcode=0;
		StackArray exp = new StackArray(length);
		char current,current1,current2;
		for(int i=0;i<length;i++){
			if(!first){
				if(!Character.isDigit(infix.charAt(i))){
					postfix.append(".");
					first=true;
				}
			}
			current=infix.charAt(i);
			if(current==')'){
                             current1=exp.pop();
				while(current1!='(')
                {
					postfix.append(current1);
                    current1=exp.pop();
                }
			}
			else if(current=='+'||current=='-'||current=='*'||current=='/'){
				if(current=='/'){
					exp.push(current);
				}
				else if(current=='*'){
					if(exp.isEmpty()){
						exp.push(current);
					}
					else {
						while(!exp.isEmpty() && breakcode==0){
							current2=exp.pop();
							if(current2=='/'){
								postfix.append(current2);
							}
							else{
								exp.push(current2);
								exp.push(current);
								breakcode=1;
							}	
						}
						if(breakcode==0)
							exp.push(current);
					}
				}
				else if(current=='-'){
					if(exp.isEmpty()){
						exp.push(current);
					}
					else {
						while(!exp.isEmpty() && breakcode==0){
							current2=exp.pop();
							if(current2=='/'){
								postfix.append(current2);
							}
							else if(current2=='*')
								postfix.append(current2);
							else{
								exp.push(current2);
								exp.push(current);
								breakcode=1;
							}	
						}
						if(breakcode==0)
							exp.push(current);
					}
				}
				else if(current=='+'){
					if(exp.isEmpty()){
						exp.push(current);
					}
					else {
						while(!exp.isEmpty() && breakcode==0){
							current2=exp.pop();
							if(current2=='/'){
								postfix.append(current2);
							}
							else if(current2=='*')
								postfix.append(current2);
							else{
								exp.push(current2);
								exp.push(current);
								breakcode=1;
							}	
						}
						if(breakcode==0)
							exp.push(current);
					}
				}
				breakcode=0;
				
			}
			else if(current=='('){
                 exp.push(current);}
				
			else{
				if(first){
					first=false;
					postfix.append(".");
				}
				postfix.append(current);
				
			}
				
		}
		return postfix.toString();
	}
	
	//evaluates the given postfix expression for given array of matrices
	public void postFixValue(String s,ArrayList<matrix> matrixes){
		//TODO implement this method
		//String s=getPostFix();
		StackArray numStack= new StackArray(s.length());
		char current;
		int operand1,operand2;
		for(int i=0;i<s.length();i++){
			current= s.charAt(i);
			if(current=='.'){
				String value="";
				i++;
				while(Character.isDigit(s.charAt(i))){
					value+=s.charAt(i);
					i++;
				}
				numStack.push_num(Integer.parseInt(value));		
			}
			//if(Character.isDigit(current))
				//numStack.push_num((int)current-48);
			else {
				operand2=numStack.pop_num();
				operand1=numStack.pop_num();
				if(matrixes.size()<operand1){
					System.out.println("Cannot evaluate "+infix);
					System.out.println("Matrix "+operand1+" was not found");
					return;
				}
				if(matrixes.size()<operand2){
					System.out.println("Cannot evaluate "+infix);
					System.out.println("Matrix "+operand2+" was not found");
					return;
				}
				if(current=='+'){
					matrix temp=matrixes.get(operand1-1).add(matrixes.get(operand2-1));
					if(temp!=null){
						matrixes.set(operand1-1,temp);
						numStack.push_num(operand1);
					}
					else{
						System.out.println("Invalid. Matrix Not Compatible with operations");
					}
				}
				else if(current=='-'){
					matrix temp=matrixes.get(operand1-1).sub(matrixes.get(operand2-1));
					if(temp!=null){
						matrixes.set(operand1-1,temp);
						numStack.push_num(operand1);
					}
					else{
						System.out.println("Invalid. Matrix Not Compatible with operations");
					}
				}
				else if(current=='*'){
					matrix temp=matrixes.get(operand1-1).mul(matrixes.get(operand2-1));
					if(temp!=null){
						matrixes.set(operand1-1,temp);
						numStack.push_num(operand1);
					}
					else{
						System.out.println("Invalid. Matrix Not Compatible with operations");
					}
				}
				else if(current=='/'){
					System.out.println("Division Not Supported");
					return;
				}
			}
				
		}
		System.out.println(infix+"=");
		matrixes.get(numStack.pop_num()-1).print();
		return;
	}

	//stack for postfix evaluation
	public class StackArray {

		private char s[];
		private int top, top1, N, arr[];

		public StackArray(int num) {
			N=num;
			s = new char[N];
			arr=new int[N];
			top = top1= 0;
		}

		public void push(char item) {
			if (top == N) {
				System.out.println("cannot push");
			} else
				s[top++] = item;
		}
		public void push_num(int item) {
			if (top1 == N) {
				System.out.println("cannot push num");
			} else
				arr[top1++] = item;
		}

		public char pop() {
			if(isEmpty()){
				System.out.println("Canot pop, Stack Empty!");
				return ' ';
			} else {
				char temp = s[--top];
				s[top] = 0;
				return temp;
			}
		}
			
		public int pop_num() {
				if(isEmpty_num()){
					System.out.println("Canot pop num, Stack Empty!");
					return 1;
				} else {
					int temp = arr[--top1];
					arr[top1] = 0;

					return temp;
				}
		}

		public boolean isEmpty(){
			return top == 0;
		}
		
		public boolean isFull(){
			return top == N;
		}
		
		public boolean isEmpty_num(){
			return top1 == 0;
		}
		
		public boolean isFull_num(){
			return top1 == N;
		}
		@Override
		public String toString() {
			return "StackArray [s=" + Arrays.toString(s) + ", top=" + top + "]";
		}
}
}