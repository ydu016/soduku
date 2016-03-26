

public class QuadLinkedList<Node> {
	
	private Node[] prev;
	private Node header;
	int rowNumber;
	
	QuadLinkedList( int size ){
		int[] headers = new int[size];
		for(int i = 0; i < size; i++){
			headers[i] = 1;
		}
		rowNumber = 0;
		prev = horizontalConnect( headers );
		header = new Node( rowNumber );
		prev[0].left = header;
		header.right = prev[0];
		rowNumber++;
	}
	
	public static class Node{
		
		Node left, right, up, down;
		private int rowNumber;
		
		
		Node( int rowNumber ){
			this.rowNumber = rowNumber;
		}
		
		public int getRowNumber(){
			return rowNumber;
		}
		
		public void delete(){
			
			Node left = this.left;
			Node right = this.right;
			Node up = this.up;
			Node down = this.down;
			if( left != null ){
				left.right = right;
			}
			if( right != null ){
				right.left = left;
			}
			if( down != null ){
				down.up = up;
			}
			if( up != null ){
				up.down = down;
			}
			
		}
		
		public void recover(){
			
			Node left = this.left;
			Node right = this.right;
			Node up = this.up;
			Node down = this.down;
			if( left != null ){
				left.right = this;
			}
			if( right != null ){
				right.left = this;
			}
			if( down != null ){
				down.up = this;
			}
			if( up != null ){
				up.down = this;
			}
			
		}
		
	}
	
	public Node getHeader(){
		return header;
	}
	
	public void addRow(int[] row){
		Node[] nodeRow = horizontalConnect( row );
		if(prev != null){
		    verticalConnect( nodeRow );
		}
		rowNumber++;
 	}
	
	public Node[] horizontalConnect( int[] row ){
		
		Node[] nodeRow = new Node[row.length];
		Node temp = null;
		for(int i = 0; i < row.length; i++){
			if( row[i] == 1 ){
				nodeRow[i] = new Node( rowNumber );
			}
			if( temp != null ){
				temp.right = nodeRow[i];
			}
			if( nodeRow[i] != null ){
				nodeRow[i].left = temp;
				temp = nodeRow[i];
			}
			
		}
		return nodeRow;
	}
	
	public void verticalConnect( Node[] nodeRow ){
		for(int i =0; i < nodeRow.length; i++){
			if( nodeRow[i] != null ){
				nodeRow[i].up = prev[i];
				prev[i].down = nodeRow[i];
				prev[i] = nodeRow[i];
			}
		}
	}
	
	public static void main(String args[]){
	}
	
}
