

import java.util.LinkedList;
import java.util.Stack;


public class ExactCoverSolver {	
	
	static Stack<LinkedList<Integer>> solution = new Stack<LinkedList<Integer>>();
	static int limit = 100;
	public static int counter = 0;
	
	public static int getMinColumn(QuadLinkedList.Node header){
		
		QuadLinkedList.Node temp1 = header.right;
		QuadLinkedList.Node temp2;
		int temp3;
		int colNumber = 0;
		int minColNumber = -1;
		int min = 2147483640;
		while( temp1 != null ){
			
			temp2 = temp1;
			temp3 = 0;
			while( temp2.down != null ){
				temp2 = temp2.down;
				temp3++;
			}
			if(temp3 == 0){
				return -1;
			}
			if(temp3 < min){
				min = temp3;
				minColNumber = colNumber;
			}
			temp1 = temp1.right;
			colNumber++;
		}
		return minColNumber;
		
	}
	
	public static Stack<QuadLinkedList.Node> step1(QuadLinkedList.Node header, int colNumber){
		
		Stack<QuadLinkedList.Node> stack1 = new Stack<QuadLinkedList.Node>();
		QuadLinkedList.Node temp = header.right;
		for( int i = 0; i < colNumber; i++ ){
			temp = temp.right;
		}
		temp = temp.down;
		while( temp != null ){		
			stack1.push( temp );
			temp = temp.down;
		}
		return stack1;
		
	}
	
	public static Stack<QuadLinkedList.Node> step2(QuadLinkedList.Node temp){
		
		Stack<QuadLinkedList.Node> stack2 = new Stack<QuadLinkedList.Node>();
		QuadLinkedList.Node temp1 = temp.left;
		QuadLinkedList.Node temp2 = temp.right;
		while( temp1 != null ){
			stack2.push( temp1 );
			temp1 = temp1.left;
		}
		while( temp2 != null ){
			stack2.push( temp2 );
			temp2 = temp2.right;
		}
		stack2.push( temp );
		
		return stack2;
	}
	
	public static Stack<QuadLinkedList.Node> step4(QuadLinkedList.Node temp){
		
		Stack<QuadLinkedList.Node> stack3 = new Stack<QuadLinkedList.Node>();
		QuadLinkedList.Node temp1 = temp.up;
		QuadLinkedList.Node temp2 = temp.down;
		while( temp1 != null ){
			if( temp1.getRowNumber() > 0 ){
				stack3.push( temp1 );	
			}
			temp1 = temp1.up;
		}
		while( temp2 != null ){
			stack3.push( temp2 );
			temp2 = temp2.down;
		}
		
		return stack3;
	}
	
	public static Stack<QuadLinkedList.Node> step3(QuadLinkedList.Node temp){
		QuadLinkedList.Node temp3 = temp;
		Stack<QuadLinkedList.Node> stack_temp = new Stack<QuadLinkedList.Node>();
		while( temp3.up != null ){
			temp3 = temp3.up;
		}
		stack_temp.push( temp3 );
		temp3.delete();
		stack_temp.push( temp );
		temp.delete();
		return stack_temp;
	}
	
	public static Stack<QuadLinkedList.Node> delete(QuadLinkedList.Node temp){
		Stack<QuadLinkedList.Node> stack4 = new Stack<QuadLinkedList.Node>();
		QuadLinkedList.Node temp1 = temp.left;
		QuadLinkedList.Node temp2 = temp.right;
		while( temp1 != null ){
			stack4.push( temp1 );
			temp1.delete();
			temp1 = temp1.left;
		}
		while( temp2 != null ){
			stack4.push( temp2 );
			temp2.delete();
			temp2 = temp2.right;
		}
		stack4.push( temp );
		temp.delete();
		return stack4;
	}
	
	public static int solve(QuadLinkedList<QuadLinkedList.Node> header, LinkedList<Integer> prev){
		
		if( header.getHeader().right == null ){
			solution.push(prev);
			counter++;
			return 1;
		}
		
		if( getMinColumn( header.getHeader()) == -1 ){
			return 0;
		}
				
		int sum = 0;
		Stack<QuadLinkedList.Node> stack1 = step1(header.getHeader(), getMinColumn( header.getHeader() ) );
		Stack<QuadLinkedList.Node> stack2, stack3, temp1, temp2;
		Stack<QuadLinkedList.Node> stack4 = new Stack<QuadLinkedList.Node>();
		
		while( !stack1.isEmpty() ){
			
			QuadLinkedList.Node row = stack1.pop();
			stack2 = step2(row);
			
			while(!stack2.isEmpty()){
				
				QuadLinkedList.Node col = stack2.pop();
				
				temp1 = step3(col);
				
				while( !temp1.isEmpty() ){
					stack4.push( temp1.pop() );
				}
				
				 stack3 = step4(col);
				
					while( !stack3.isEmpty() ){						
						temp2 = delete( stack3.pop() );
						while( !temp2.isEmpty() ){
							stack4.push( temp2.pop() );
						}
					}				

			}
			
			prev.add(row.getRowNumber());
			
			LinkedList<Integer> next = new LinkedList<Integer>();
			for(int i = 0; i < prev.size(); i++){
				next.add( prev.get(i) );
			}
			
			//sum += solve( header, next );
			if( counter < limit ){
				solve(header,next);
			}
			
			prev.removeLast();
			
			while( !stack4.isEmpty() ){
				stack4.pop().recover();
			}
			
		}		
		return 0;
		//return sum;
		
	}
	
	public static void main(String[] args){
		
	}
	
}