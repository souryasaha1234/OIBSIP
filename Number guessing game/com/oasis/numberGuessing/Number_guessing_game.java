package com.oasis.numberGuessing;

import java.io.IOException;
import java.io.PrintWriter;

// import javax.servlet.ServletContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSessionContext;

@WebServlet("/Number_guessing_game")
public class Number_guessing_game extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected static int result=0; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Number_guessing_game() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=\"UTF-8\"");
		PrintWriter servletOut=response.getWriter();
		HttpSession session=request.getSession(true);
		String msg=(String)session.getAttribute("redirect");
		System.out.println(msg);
		String extraMsg=null;
		if(msg!=null)
		{
			extraMsg="<h4>"+msg+"</h4>";
			
		}
		else{  extraMsg="";}
		servletOut.println(
				"<html>"
				+ "<head>"
				+ "<title>Number Guessing</title>"
				+ "<style>\r\n"
				+ "    body{\r\n"
				+ "        text-align: center;\r\n"
				+ "        font-family: 'Times New Roman', Times, serif;\r\n"
				+ "        font-size: 25px;\r\n"
				+ "        /* background: linear-gradient(lightgreen, green); */\r\n"
				+ "        background: linear-gradient(skyblue, whitesmoke);\r\n"
				+ "    }\r\n"
				+ "    input{\r\n"
				+ "        border: 1px solid black;\r\n"
				+ "        margin: 10px;\r\n"
				+ "        padding: 5px;\r\n"
				+ "        border-radius: 3px;\r\n"
				+ "        box-shadow: 0px 0px 3px 1px black;\r\n"
				+ "    }\r\n"
				+ "    input[type = text]{\r\n"
				+ "        background-color: lightgray;\r\n"
				+ "    }\r\n"
				+ "    input[type = submit]{\r\n"
				+ "        color: black;\r\n"
				+ "        background: linear-gradient(90deg, skyblue, white);\r\n"
				+ "    }\r\n"
				+ "</style>"
				+ "</head>"
				+ "<body>"
				+ extraMsg				
				+ "<h2>The correct number is any number between 1 and 100.</h2>"
				+ "<form method='post' action= '"+ response.encodeRedirectURL("Number_guessing_game")+"'>"
				+ "<label>Enter your guess</label>"
				+ "<input name='num' type=\"text\"></input>"
				+ "<br/><input type=\"submit\" name=\"submit\" value='Check number'>"
				+ "<p>Session will be invalid after 40 seconds</p>"
				+"</form>"
				+"</body>"
				+ "</html>"				
				);
		int result=(int)(100*Math.random());
		System.out.println(result);		
		session.setAttribute("result", result);
		session.setAttribute("redirect",null);
		servletOut.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=\"UTF-8\"");
		PrintWriter servletOut=response.getWriter();
		int guess=0;
		HttpSession session=request.getSession();		 
		if(session.isNew()){
			session.setAttribute("redirect", "It's invalid Session ID, let's try again");			
			response.sendRedirect(response.encodeRedirectURL("Number_guessing_game"));
			
		}
		else{
			result=(Integer)session.getAttribute("result");
			String guessname=request.getParameter("num");
			System.out.println(guessname);
			try{
			guess=Integer.parseInt(guessname);			
				session.setAttribute("num", guess );		
				System.out.println("guess:"+ guess +" and result: "+result);
				if(guess==result){
				printcongrats(servletOut);
				}
				else {
					if(guess>result) {printlarger(servletOut,guess);}
					else {printsmaller(servletOut,guess);}
				}
			}catch (NumberFormatException e) {
		        error(servletOut,"input type");
		    }
			}
		
		session.setMaxInactiveInterval(40);
		servletOut.close();
		
	}

	
	protected void printcongrats(PrintWriter servletOut){
		servletOut.println("<html><head>"
				+ "<title>Number Guessing</title>"
				+ "<style>\r\n"
				+ "    body{\r\n"
				+ "        text-align: center;\r\n"
				+ "        background: linear-gradient(lightgreen, green);\r\n"
				+ "        font-family: 'Times New Roman', Times, serif;\r\n"
				+ "        font-size: 25px;\r\n"
				+ "    }\r\n"
				+ "    input{\r\n"
				+ "        margin: 10px;\r\n"
				+ "    }\r\n"
				+ "</style><head><body><h1>Congratulations, you are correct!</h1></body></html>");
	}
	protected void printlarger(PrintWriter servletOut, int guess){
		servletOut.println("<html>"
				+ "<head>\r\n"
				+ "<title>Number Guessing</title>"
				+ "<style>\r\n"
				+ "    body{\r\n"
				+ "        text-align: center;\r\n"
				+ "        font-family: 'Times New Roman', Times, serif;\r\n"
				+ "        font-size: 25px;\r\n"
				+ "        /* background: linear-gradient(lightgreen, green); */\r\n"
				+ "        background: linear-gradient(skyblue, whitesmoke);\r\n"
				+ "    }\r\n"
				+ "    input{\r\n"
				+ "        border: 1px solid black;\r\n"
				+ "        margin: 10px;\r\n"
				+ "        padding: 5px;\r\n"
				+ "        border-radius: 3px;\r\n"
				+ "        box-shadow: 0px 0px 3px 1px black;\r\n"
				+ "    }\r\n"
				+ "    input[type = text]{\r\n"
				+ "        background-color: lightgray;\r\n"
				+ "    }\r\n"
				+ "    input[type = submit]{\r\n"
				+ "        color: black;\r\n"
				+ "        background: linear-gradient(90deg, skyblue, white);\r\n"
				+ "    }\r\n"
				+ "</style>\r\n"
				+ "</head>"
				+ "<body><h3>You are incorrect, "+
							guess +" is larger than the result.\n"
									+ "Please try again </h3>"
		+ "<form method='post'>"
		+ "<label>Enter your guess</label>"
		+ "<input name=\"num\" type=\"text\"></input>"
		+ "<input type=\"submit\" name=\"submit\"></form></body></html>");
	}
	
	protected void printsmaller(PrintWriter servletOut, int guess){
		servletOut.println(
				"<head>\r\n"
				+ "<title>Number Guessing</title>"
				+ "<style>\r\n"
				+ "    body{\r\n"
				+ "        text-align: center;\r\n"
				+ "        font-family: 'Times New Roman', Times, serif;\r\n"
				+ "        font-size: 25px;\r\n"
				+ "        /* background: linear-gradient(lightgreen, green); */\r\n"
				+ "        background: linear-gradient(skyblue, whitesmoke);\r\n"
				+ "    }\r\n"
				+ "    input{\r\n"
				+ "        border: 1px solid black;\r\n"
				+ "        margin: 10px;\r\n"
				+ "        padding: 5px;\r\n"
				+ "        border-radius: 3px;\r\n"
				+ "        box-shadow: 0px 0px 3px 1px black;\r\n"
				+ "    }\r\n"
				+ "    input[type = text]{\r\n"
				+ "        background-color: lightgray;\r\n"
				+ "    }\r\n"
				+ "    input[type = submit]{\r\n"
				+ "        color: black;\r\n"
				+ "        background: linear-gradient(90deg, skyblue, white);\r\n"
				+ "    }\r\n"
				+ "</style>"
				+ "</head>"
				+ "<body>"
				+"<h3>You are incorrect, "
				+guess 
				+" is smaller than the result.\n"
						+ "Please try again </h3>"
						+ "<form method=\"post\">"
						+ "<label>Enter your guess</label>"
						+ "<input name=\"num\" type=\"text\"></input>"
						+ "<input type=\"submit\" name=\"submit\" value='Check number'></form>"
				+ "</body>");

	}
	
	protected void error(PrintWriter servletOut, String msg){
		servletOut.println(
						"<head>\r\n"
						+ "<title>Number Guessing</title>"
						+ "<style>\r\n"
						+ "    body{\r\n"
						+ "        text-align: center;\r\n"
						+ "        font-family: 'Times New Roman', Times, serif;\r\n"
						+ "        font-size: 25px;\r\n"
						+ "        /* background: linear-gradient(lightgreen, green); */\r\n"
						+ "        background: linear-gradient(skyblue, whitesmoke);\r\n"
						+ "    }\r\n"
						+ "    input{\r\n"
						+ "        border: 1px solid black;\r\n"
						+ "        margin: 10px;\r\n"
						+ "        padding: 5px;\r\n"
						+ "        border-radius: 3px;\r\n"
						+ "        box-shadow: 0px 0px 3px 1px black;\r\n"
						+ "    }\r\n"
						+ "    input[type = text]{\r\n"
						+ "        background-color: lightgray;\r\n"
						+ "    }\r\n"
						+ "    input[type = submit]{\r\n"
						+ "        color: black;\r\n"
						+ "        background: linear-gradient(90deg, skyblue, white);\r\n"
						+ "    }\r\n"
						+ "</style>\r\n"
						+ "</head>"
						+ "<body>"
						  +"<h3>Error "
						+ " with " + msg + ".\n"
						+ "Please try again </h3>"
						+ "<form method=\"post\">"
						+ "<label>Enter your guess</label>"
						+ "<input name=\"num\" type=\"text\"></input>"
						+ "<input type=\"submit\" name=\"submit\" value='Check number'></form>"
						+ "</body>");
	}
	
	protected static boolean isNum(String strNum) {
	    boolean ret = true;
	    try {

	        Double.parseDouble(strNum);

	    }catch (NumberFormatException e) {
	        ret = false;
	    }
	    return ret;
	}
}
