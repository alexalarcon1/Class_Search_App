package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbHandler.SearchHandler;
import model.Course;

/**
 * Servlet implementation class CourseDetails
 */
public class CourseDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String semester = request.getParameter("semester");
		String crsDis = request.getParameter("dis");
		String crsTitle = request.getParameter("title");
		String crsNum = request.getParameter("num");
		String crsCode = request.getParameter("code");
		String crsSec = request.getParameter("section");
		String crsDept = request.getParameter("dept");
		String crsDiv = request.getParameter("division");
		String crsStartDate = request.getParameter("startDate");
		String crsEndDate = request.getParameter("endDate");
		String crsSeats = request.getParameter("seats");
		String crsCreditHours = request.getParameter("creditHours");
		String crsDescription = request.getParameter("description");
		String crsPreReq = request.getParameter("preReq");
		String crsComments = request.getParameter("comments");
		
		//Get all meeting days, building, room and instructors based on course name and number
		SearchHandler searchMeetings = new SearchHandler();
		
		if(semester.equals("January Intersession"))
			searchMeetings.queryBySemesterJS("%JAN%", "%JAN%");
		else if(semester.equals("Summer ALL"))
			searchMeetings.queryBySummerAll(true);
		else if(semester.equals("Summer00"))
			searchMeetings.queryBySemesterJS("%JUN%", "%AUG%");
		else if(semester.equals("Summer01"))
			searchMeetings.queryBySemesterJS("%JUN%", "%JUL%");
		else if(semester.equals("Summer02"))
			searchMeetings.queryBySemesterJS("%JUL%", "%AUG%");
		else if(semester.equals("Fall") || semester.equals("Spring")) {
			searchMeetings.queryBySemesterFS(semester.toLowerCase());
		}
		
		searchMeetings.queryGetMeetings(crsDis, crsNum, crsSec);
		System.out.println(crsDis + " " + crsNum + " " + crsSec);
		searchMeetings.exQuery();
		List<Course> courseList = searchMeetings.getResultsList();
		System.out.println("Length of courseList: " + courseList.size());
		
		for(Course day: courseList) {
			System.out.println("Days: " + day.getMeetingDays());
		}
			
		request.setAttribute("semester", semester);
		request.setAttribute("disc", crsDis + crsNum + " - " + crsTitle );
		request.setAttribute("code", crsCode);
		request.setAttribute("section", crsSec);
		request.setAttribute("dept", crsDept);
		request.setAttribute("division", crsDiv); //GradLevel needs to be added to query
		request.setAttribute("dates", crsStartDate + " - " + crsEndDate);
		request.setAttribute("seats", crsSeats);
		request.setAttribute("creditHours", crsCreditHours);
		request.setAttribute("description", crsDescription);
		request.setAttribute("preReq", crsPreReq);
		request.setAttribute("comments", crsComments);
		request.setAttribute("courseMeetings", courseList);
		
		request.getRequestDispatcher("/coursedetails.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
