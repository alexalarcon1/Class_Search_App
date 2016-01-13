package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dbHandler.FormHandler;
import dbHandler.SearchHandler;
import model.*;

/**
 * Servlet implementation class SearchServlet
 */

@WebServlet("")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FormHandler form = new FormHandler("test1", "test001", "Zicklin");	
		//Prepare the form for display
		Session querySession = new Session();
		Discipline queryDisc = new Discipline();
		Days queryDays = new Days();
		
		//Set the semester names
		form.executeQuery(querySession.getQuery());
		List<Session> sessions = form.getSessionList("SEMESTER_NAME");
		
		//Set the dicipline names
		form.executeQuery(queryDisc.getQuery());
		List<Discipline> disciplines = form.getDiscList("DISCIPLINE_NAME");
		
		//Set the meeting days
		form.executeQuery(queryDays.getQuery());
		List<Days> meetingDays = form.getDaysList("MEETING_DAYS");
				
		request.setAttribute("sessions", sessions);		  //Will be available as ${sessions} in index.jsp
		request.setAttribute("disciplines", disciplines); //Available as ${disciplines} in index.jsp
		request.setAttribute("meetingDays", meetingDays); 
		//Put all jsp pages in WEB-INF because it is more scure... google for more info.
		//Ex: request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response); 
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
		form.closeConnection();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Once user clicks submit...
		String action = request.getParameter("action");
		SearchHandler search = new SearchHandler(/*"test1", "test001", "Zicklin"*/);
		
		try{
		//Search for classes that meet specifications
		if(action.equals("doSearch")) {
			//Get user's parameter choices...
			String semester = request.getParameter("semester");
			List<String> keywords = new ArrayList<>();
			Time timeFilter = new Time();
			
			if(!semester.equals("Semester")) {
				keywords.add(semester); 
				semester = semester.toLowerCase();
				System.out.println("\nSemester: " + semester);
				
				if(semester.equals("january intersession"))
					search.queryBySemesterJS("%JAN%", "%JAN%");
				else if(semester.equals("summer all"))
					search.queryBySummerAll(true);
				else if(semester.equals("summer00"))
					search.queryBySemesterJS("%JUN%", "%AUG%");
				else if(semester.equals("summer01"))
					search.queryBySemesterJS("%JUN%", "%JUL%");
				else if(semester.equals("summer02"))
					search.queryBySemesterJS("%JUL%", "%AUG%");
				else if(semester.equals("fall") || semester.equals("spring")) {
					search.queryBySemesterFS(semester);
				}
					
				//Check discipline
				String discipline = request.getParameter("discipline");
				if(discipline.equals("Select All"))
					discipline = "Select All";
				if(!discipline.equals("Select All")) {
					search.queryByDisc(discipline);
					
				}
				keywords.add(discipline);
				
				//Check if the gradStatus division selected
				String[] gradStatus = request.getParameterValues("gradStatus");
				System.out.println("Grad Checkbox len: " + gradStatus.length);
				boolean uFlag = false;
				boolean gFlag = false;
				String gradLevel = null;
				
				if(gradStatus.length > 0) {
					for(int i=0; i < gradStatus.length; i++) {
						if(gradStatus[i].equals("U")) {
							uFlag = true;
						}
						if(gradStatus[i].equals("G")) {
							gFlag = true;
						}
					}
					System.out.println("Undergrad: " + uFlag);
					System.out.println("Grad: " + gFlag);
					if(uFlag && !gFlag) {
						gradLevel = "u";
						keywords.add("Undergraduate");
						System.out.println("GradLevel " + gradLevel);
						search.queryByGradLvl(gradLevel);
					}
					else if(gFlag && !uFlag) {
						gradLevel = "g";
						keywords.add("Graduate");
						search.queryByGradLvl(gradLevel);
					}
					else if(uFlag && gFlag) {
						gradLevel = "%";
						keywords.add("Select All");
						search.queryByGradLvl(gradLevel);
					}
					else if(!uFlag && !gFlag) {
						System.out.println("ERROR: Need to select grad division");
						//IMPLEMENT ERROR PAGE	
					}
				}	
				
				//Check course number
				String courseNumber = request.getParameter("number");
				keywords.add(courseNumber);
				if(!courseNumber.equals("")) {
					search.queryByCourse(courseNumber);
				}
				
				//Check days of the week 
				String week = request.getParameter("week");
				keywords.add(week);
				if(!week.equals("Select All")) {
					Week days = new Week();
					week = days.convertWeek(week);
					search.queryByDaysOfWeek(week);
					//System.out.println(week);
				}
				else if (week.equals("Select All")){
					Week days = new Week();
					week = days.convertWeek(week);
					search.queryByDaysOfWeek(week);
				}
				
				//Check Instructor
				String instructor = request.getParameter("prof");
				keywords.add(instructor);
				if(!instructor.equals(""))
					search.queryByInstructor(instructor);
				
				//Check Time
				String timeAB = request.getParameter("time_a_b");
				String time = request.getParameter("time");
				timeFilter.setTimeAB(timeAB);
				timeFilter.setTimeValue(time);
				System.out.println("Time constraint: " + timeFilter.getTimeAB() + " " + timeFilter.getTimeValue());
				
				//EXECUTE QUERY
				search.exQuery();
				
				//Create course list. Including time filter 
				//Sends course information to populate result table
				List<Course> courseList = search.getResultsList(timeFilter.getTimeAB(), timeFilter.getTimeValue());
					
				//Get department (based on disc) to display on top left table
				if(!discipline.equals("Select All")) {
					String department = courseList.get(0).getDept();
					keywords.add(department);
					System.out.println("Department: " + department);
				}
				else {
					keywords.add("Select All");
				}
				
				//Set search keywords to be displayed on top left table
				request.setAttribute("semester", keywords.get(0));
				request.setAttribute("disc", keywords.get(1));
				request.setAttribute("grad_div", keywords.get(2));
				request.setAttribute("courseNum", keywords.get(3));
				request.setAttribute("week", keywords.get(4));
				request.setAttribute("instructor", keywords.get(5));
				request.setAttribute("department", keywords.get(6));
				
				//Set list of courses 
				request.setAttribute("courseList", courseList);
				
				//Send to view and close db_connection
				request.getRequestDispatcher("/scheduleresults.jsp").forward(request, response);
				search.closeConnection();
			}
			
	/////////Error message not being displayed.////////////
			else if(semester.equals("Semester")){
				//request.setAttribute("invalidSearch", search.invalidMessage());
				request.getRequestDispatcher("/includes/errorpg.jsp").forward(request, response);
				search.closeConnection();
			}
		}	
	

}catch (NullPointerException e) {
	e.getMessage();
	System.out.print("Caught the NullPointerException");
}
    
}
}