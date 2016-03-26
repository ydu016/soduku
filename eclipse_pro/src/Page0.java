import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Page0 extends HttpServlet{
	
	static int i = 0;
	static int j = 0;
	static int index = 0;
	
	public void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
	
		PrintWriter out = arg1.getWriter();
		
		int[][] matrix = {
				{0, 0, 0, 0, 0, 0, 0, 5, 0},
                {0, 0, 0, 0, 0, 5, 6, 0, 0},
                {2, 0, 0, 0, 0, 0, 0, 0, 1},
                {8, 0, 0, 4, 0, 7, 0, 0, 6},
                {0, 0, 6, 0, 0, 0, 3, 0, 4},
                {0, 5, 0, 9, 0, 1, 0, 0, 7},
                {0, 0, 0, 0, 0, 0, 0, 0, 3},
                {0, 0, 7, 2, 1, 6, 9, 0, 5},
                {0, 0, 0, 0, 0, 0, 0, 0, 8}
                };
		
		boolean[][] mm = new boolean[9][9];
		for(int i = 0; i<9; i++){
			for(int j=0; j<9;j++){
				if(matrix[i][j] == 0){
					mm[i][j] = false;
				}else{
					mm[i][j] = true;
				}
			}
		}

		HttpSession session = arg0.getSession(true);
		session.setAttribute("array", matrix);
		session.setAttribute("arrayy", mm);
		head(out, matrix, arg0);
		
	}
	
	void head(PrintWriter out, int[][] matrix, ServletRequest arg0){

		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset = \"utf-8\">");
		out.println("<title>\"Sudoku\"</title>");
		
		out.println("<style type=\"text/css\">");
		out.println("div#container{width:1000px}");
		out.println("div#header {background-color:#99bbbb;}");
		out.println("div#menu {background-color:#ffff99;height:600px;width:500px;float:left;}");
		out.println("div#content {background-color:#EEEEEE;height:600px;width:500px;float:left;}");
		out.println("h1 {margin-bottom:0;}");
		out.println("td{ height:5px;}</style>");
		
		out.println("<body>");
		
		out.println("<div id=\"container\"><div id=\"header\"><h1>Sudoku</h1></div>");
		
		out.println("<div id=\"menu\">");
		
		sudoku(out,matrix,arg0);
		
		out.println("</div>");
		
		out.println("<div id=\"content\"><p>Please enter a number</p></div>");

		out.println("</div>");
		
		out.println("</body>");
		
		out.println("<script type=\"text/javascript\">");
		out.println("function submitForm(formId){form = document.getElementById(formId);form.submit();}");
		out.println("function isNum(para){if(para!=null){var r,re;re = /[1-9]/;r = para.match(re);return (r==para)?true:false;} return false;}");
		out.println("function autoSubmit(formId,id) {var para = document.getElementById(id);if(para.value == \"\"){}else if(isNum(para.value) == true){submitForm(formId);}else{alert(\"Invalid Input!\");}}");
		out.println("</script>");
		out.println("</head>");

		showSolution(out);
		out.println("</html>");
		index = 0;
		out.close();
	}
	
	void sudoku(PrintWriter out, int[][] matrix,ServletRequest arg0){
		out.println("<table border = \"1\">");
		out.println("<tbody>");
		
		for(int i = 0; i < 9;i++ ){
			this.i=i;
			out.println("<tr>");			
			for(int j = 0;j < 9; j++ ){
				this.j=j;
				if(matrix[i][j] == 0){
					cell(out,i,j);
				}else{
					cell(out,matrix[i][j]);
				}
			}
			out.println("</tr>");
		}
		
		out.println("</tbody>");
		out.println("</table>");
	}
	
	void cell(PrintWriter out, int value){
		out.println("<td style=\"vertical-align:top;width:35px \">"+value+"</td>");
	}
	
	void cell(PrintWriter out, int i,int j){
		out.println("<td style=\"vertical-align:bottom;width:35px \"><form id=\"test"+index+"\" name=\"test\" action=\"MyServlet\"><input type=\"text\" name=\""+i+j+"\" id=\""+index+"\" <td style=\"vertical-align:bottom;width:20px \" onKeyup=\"autoSubmit('test"+index+"','"+index+"')\" /></form>");
		index++;
	}
	
	void showSolution(PrintWriter out){
		
	}




	
	/**
	void sudoku(PrintWriter out, int[][] matrix){

		out.println("<tr>");
		out.println("<td style=\"vertical-align:top;width:35px \">1</td><td style=\"vertical-align:top;width:35px \">1</td><td style=\"vertical-align:top;width:35px \">1</td><td style=\"vertical-align:top;width:35px \">1</td><td style=\"vertical-align:top;width:35px \">1</td><td style=\"vertical-align:top;width:35px \">1</td><td style=\"vertical-align:top;width:35px \">1</td><td style=\"vertical-align:top;width:35px \">1</td>");
		out.println("<td style=\"vertical-align:bottom;width:35px \"><form id=\"test1\" name=\"test\" action=\"MyServlet\"><input type=\"text\" name=\"word1\" id=\"word1\" <td style=\"vertical-align:bottom;width:20px \" onKeyup=\"autoSubmit('test1','word1')\" /></form>");
		out.println("</td>");
		out.println("</tr>");
		
	}
	**/
	
}
