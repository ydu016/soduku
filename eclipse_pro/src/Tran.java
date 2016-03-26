

import java.util.ArrayList;
import java.util.LinkedList;
import java.applet.Applet;
import java.awt.Graphics;

public class Tran extends Applet{
	
	public void start(){
		 int [][] k = {{8,0,0,6,0,0,9,0,5},{0,0,0,0,0,0,0,0,0},{0,0,0,0,2,0,3,1,0},
			      {0,0,7,3,1,8,0,6,0},{2,4,0,0,0,0,0,7,3},{0,0,0,0,0,0,0,0,0},
			      {0,0,2,7,9,0,1,0,0},{5,0,0,0,8,0,0,3,6},{0,0,3,0,0,0,0,0,0}};
	
	for(int a = 0; a < k.length; a++){
		for(int b = 0; b < k[0].length; b++){
			System.out.print(k[a][b] + " ");
		}
		System.out.println();
	}
	System.out.println();
	
	 }
	 
	  public void init() { }
	  
	  // This method is mandatory, but can be empty.(i.e.,have no actual code).
	  public void stop() { }
	  public void paint(Graphics g) {

			    g.drawString("Hello, world!", 20,10);
			 
			  // Draws a circle on the screen (x=40, y=30).
			    g.drawArc(40,30,20,20,0,360);
			  
			 int [][] k = {{8,0,0,6,0,0,9,0,5},{0,0,0,0,0,0,0,0,0},{0,0,0,0,2,0,3,1,0},
				      {0,0,7,3,1,8,0,6,0},{2,4,0,0,0,0,0,7,3},{0,0,0,0,0,0,0,0,0},
				      {0,0,2,7,9,0,1,0,0},{5,0,0,0,8,0,0,3,6},{0,0,3,0,0,0,0,0,0}};
		//(int a = 0; a < k.length; a++){
		//	for(int b = 0; b < k[0].length; b++){
		//		 g.drawString(String.valueOf(k[a][b]), (b+2)*30,(a+2)*30);
		//	}
		//	System.out.println();
		//}
		//System.out.println();
	}
	 
	public final static int size = 324;
	public final static int jump = 9;
	public final static int row_num_start = 0;
	public final static int row_num_end = row_num_start + jump * jump - 1;//80
	public final static int col_num_start = row_num_end + 1;//81
	public final static int col_num_end = col_num_start + jump * jump - 1;//161
	public final static int box_num_start = col_num_end + 1;//162
	public final static int box_num_end = box_num_start + jump * jump - 1;//242
	public final static int row_col_start = box_num_end + 1;//243
	public final static int row_col_end = row_col_start + jump * jump - 1;//323		
	
	public static int[] generateHeader( int [][] arraySudoku ){
		int [] head = new int[size];
		int row_num, col_num, box_num;
		for( int row = 0; row < arraySudoku.length; row++ ){
			for( int col = 0; col < arraySudoku[0].length; col++ ){
				if( arraySudoku[row][col] > 0 ){
					row_num = getRowIndex( row, arraySudoku[row][col] );
					col_num = getColIndex( col, arraySudoku[row][col] );
					box_num = getBoxIndex( row, col, arraySudoku[row][col]);
					if(row == 6 && arraySudoku[row][col] == 5){
					}
						if( head[row_num] == 1 || head[col_num] == 1 || head[box_num] == 1){
							return null;
						}
					head[row_num] = 1;
					head[col_num] = 1;
					head[box_num] = 1;
					head[getRowCol( row, col)] = 1;
				}
			}
		}
		return head;
	}
	
	public static int getRowIndex( int row, int value ){
		return row_num_start + jump * row + value - 1;
	}
	
	public static int getColIndex( int col, int value ){
		return col_num_start + jump * col + value - 1;
	}
	
	public static int getBoxIndex( int row, int col, int value ){
		if( 0 <= row && row < 3 && 0 <= col && col < 3 ){
			return box_num_start + jump * 0 + value - 1;
		} else if ( 0 <= row && row < 3 && 3 <= col && col < 6 ){
			return box_num_start + jump * 1 + value - 1;
		} else if ( 0 <= row && row < 3 && 6 <= col && col < 9 ){
			return box_num_start + jump * 2 + value - 1;
		} else if ( 3 <= row && row < 6 && 0 <= col && col < 3 ){
			return box_num_start + jump * 3 + value - 1; 
		} else if ( 3 <= row && row < 6 && 3 <= col && col < 6 ){
			return box_num_start + jump * 4 + value - 1;
		} else if ( 3 <= row && row < 6 && 6 <= col && col < 9 ){
			return box_num_start + jump * 5 + value - 1;
		} else if ( 6 <= row && row < 9 && 0 <= col && col < 3 ){
			return box_num_start + jump * 6 + value - 1;
		} else if ( 6 <= row && row < 9 && 3 <= col && col < 6 ){
			return box_num_start + jump * 7 + value - 1;
		} else if ( 6 <= row && row < 9 && 6 <= col && col < 9 ){
			return box_num_start + jump * 8 + value - 1;
		}
		return -1;
	}
	
	public static int getRowCol( int row, int col ){
		return row_col_start + jump * row + col;
	}
	
	public static boolean collision( int[] header, int[] row ){
		for(int i = 0; i < size; i++){
			if(header[i] == 1 && row[i] == 1){
				return true;
			}
		}
		return false;
	} 
	
	public static ArrayList<int []> tran( int [][] sudokuArray ){
		ArrayList<int []> sets = new ArrayList<int []>();
		int[] header = generateHeader(sudokuArray);
		if(header == null){
			return null;
		}
		sets.add( header );
		int[] row;
		int r,c;
		for(int i = row_col_start; i < header.length; i++){
			for(int value = 1; value < jump + 1; value ++){
				row = new int[size];
				r = (int) Math.floor( ( i - row_col_start ) / 9 );
				c = (i - row_col_start) % 9;
				row[getRowIndex(r,value)] = 1;
				row[getColIndex(c,value)] = 1;
				row[getBoxIndex(r,c,value)] = 1;
				row[i] = 1;
				if(!collision( header, row )){
					sets.add(row);
				}
			}
		}
		return sets;
	}
	
	public static int getRowIndex2( int i ){
		return (int)(Math.ceil(( i + 1 - row_num_start ) / 9.0) - 1);
	}
	
	public static int getColIndex2( int i ){
		return (int)(Math.ceil(( i + 1 - col_num_start ) / 9.0) - 1);
	}
	
	public static int getValue2( int i ){
		return ( i - row_num_start ) % 9 + 1;
	}
	
	public static int[] antiTran(int[] row){
		int [] temp = new int[3];
		int index = 0;
		for(int i = 0; i < row.length; i++){
			if( index == 1 && row[i] == 1 ){
				temp[index] = getColIndex2(i);
				break;
			}
			if( index == 0 && row[i] == 1 ){
				temp[index] = getRowIndex2(i);
				temp[2] = getValue2(i);
				index++;
			}
		}
		return temp;
	}
	
	public static StringBuffer temp(int[][] k){
		
		StringBuffer completion = new StringBuffer();
		//completion.append("<html><body>");
		ArrayList<int []> sets = tran(k);
		QuadLinkedList<QuadLinkedList.Node> x = new QuadLinkedList<QuadLinkedList.Node>( 324 );
		if(sets != null){
			for(int i = 0; i<sets.size(); i++){
				x.addRow(sets.get(i));
			}
		}
		ExactCoverSolver.solve(x, new LinkedList<Integer>());
		LinkedList<Integer> si;
		int index = 0;
		int ki[];
		while(!ExactCoverSolver.solution.isEmpty()){
			index++;
			completion.append("Completion#"+index+"<br>");
			si = ExactCoverSolver.solution.pop();
			for(int ii = 0; ii < si.size(); ii++){
				if(si.get(ii) != 1){
				ki = antiTran(sets.get(si.get(ii)-1));	
					k[ki[0]][ki[1]] = ki[2];
				}
			}

			for(int a = 0; a < k.length; a++){
				//completion.append("<p>");
				for(int b = 0; b < k[0].length; b++){
					//completion.append( k[a][b] + "&nbsp; &nbsp; &nbsp;");
					completion.append( k[a][b] + "    ");
				}
				//completion.append( "</p>" );
				completion.append("<br>");
			}
			completion.append("<br>");
			//completion.append( "<p>Completion#"+index+"</p><br>" );

		}
		if(ExactCoverSolver.counter == ExactCoverSolver.limit){
			completion.append( "More then "+ ExactCoverSolver.limit +" completions" );
		} else {
			completion.append( "There are " + ExactCoverSolver.counter + " completion(s) in total" );
		}
		//completion.append( "</body></html>" );
		ExactCoverSolver.counter = 0;
		return completion;
	}
	
	public static ArrayList<int []> getSets(int[][] sudoku){
		return tran(sudoku);
	}
	
	public static void main( String[] args ){
        int Default[][] = {{0, 0, 0, 0, 0, 0, 0, 2, 1},
                           {0, 0, 0, 0, 0, 0, 0, 0, 0},
                           {0, 0, 0, 0, 0, 0, 0, 0, 0},
                           {0, 0, 0, 0, 0, 0, 0, 0, 0},
                           {0, 0, 0, 0, 0, 0, 0, 0, 0},
                           {0, 0, 0, 0, 0, 0, 0, 0, 0},
                           {0, 0, 0, 0, 0, 0, 0, 0, 0},
                           {0, 0, 0, 0, 0, 0, 0, 0, 0},
                           {0, 0, 0, 0, 0, 0, 0, 0, 0}};
        System.out.println(temp(Default));
        //System.out.println(getBoxIndex(4,4,6));
	}
	
}
