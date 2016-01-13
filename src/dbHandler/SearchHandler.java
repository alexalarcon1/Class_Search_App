package dbHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class SearchHandler extends DatabaseHandler {
	
	private String query = 
					("SELECT DISTINCT crs_sr.title, dept.dept_name, crs.disc, crs.crs_num, crs.crs_sec, crs.crs_cd, crs.meeting_days, " +
					"crs.start_time, crs.stop_time, crs.AM_PM, crs.start_date, crs.end_date, crs.building, " +
					"crs.rm, crs.instructor_lname, crs.seats_avail, crs_sr.credithour, crs_sr.description, crs_sr.prereq, crs_comments1 " +
					"FROM CRS_SEC_SR crs " +
					"INNER JOIN DISCIPLINE_SR dis " +
					"ON crs.DISC = dis.DISC_ABBREVIATION " +
					"INNER JOIN DEPT_SR dept " +
					"ON dis.DEPT_ID = dept.DEPT_ID " +
					"INNER JOIN CRS_COMMENTS_SR com " +
					"ON crs.crs_cd = com.crs_cd " +
					"INNER JOIN COURSE_SR crs_sr " +
					"ON crs.crs_num = crs_sr.coursenumber AND crs.DISC = crs_sr.DISCIPLINE " +
					"WHERE ");
	
//Query all course by Semester
	//Fall, Spring
	public void queryBySemesterFS(String semester){
		query += "crs.semester = '" + semester + "' ";
		System.out.println("FS built");
	}
	
	//January Intersession, Summer00, Summer01, Summer02
	public void queryBySemesterJS(String start, String end){
		query += ("crs.start_date LIKE '" + start + "' " + 
				  "and crs.end_date LIKE '" + end + "' ");
		System.out.println("JS built");
	}
	//SummerAll
	public void queryBySummerAll(boolean isAllSummer) {
		query += ("EXTRACT(MONTH FROM TO_DATE(crs.start_date)) >= 6 " +
				 "AND EXTRACT(MONTH FROM TO_DATE(crs.start_date)) <= 8 " +
				 "AND EXTRACT(MONTH FROM TO_DATE(crs.end_date)) >= 6 " +
				 "AND EXTRACT(MONTH FROM TO_DATE(crs.end_date)) <= 8");
		
		System.out.println("Summer all built");
	}
	
	//Query by Discipline
	public void queryByDisc(String disc){
		query += "and dis.discipline_name = '" + disc + "' ";
		System.out.println("Disc built");
	}
	
	//Query by Grad division
	public void queryByGradLvl(String gradLvl) {
		query += "and crs_sr.level_div LIKE '" + gradLvl + "' ";
		System.out.println("Grad built");
	}
	
	//Query by Course Number
	public void queryByCourse(String crsNum) {
		query += "and crs.crs_num = '" + crsNum + "' ";
		System.out.println("Crs built");
	}
	
	//Query by days of the week
	public void queryByDaysOfWeek(String days) {
		query += "and crs.meeting_days LIKE '" + days +"' ";
		System.out.println("Days built");
	}
	
	//Query by Instructor
	public void queryByInstructor(String instructor) {
		query += "and crs.instructor_lname LIKE '" + instructor + "' ";
		System.out.println("Inst built");
	}
	//Query the meeting days for courseDetails.jsp
	public void queryGetMeetings(String courseDisc, String courseNum, String courseSec) {
		query += "and crs.disc = '" + courseDisc + "' " +
				 "and crs.crs_num = '" + courseNum + "' " +
				 "and crs.crs_sec = '" + courseSec + "' ";
	}
	
//FINAL EXECUTION OF QUERY
	public void exQuery() {
		//Add order by to query
		query += "order by crs.end_date";
		System.out.println("Query fully built");
		try {
			ps = this.conn.prepareStatement(query);
			this.results = ps.executeQuery();
			System.out.println("Query Successfully executed!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Course> getResultsList(String timeAB, int timeValue) {
		List<Course> courseList = new ArrayList<>();
		try {
			while(this.results.next()) {
				Course course = new Course();
				course.setTitle(this.results.getString("TITLE"));
				course.setDept(this.results.getString("DEPT_NAME"));
				course.setDisc(this.results.getString("DISC"));
				course.setCrsNum(this.results.getString("CRS_NUM"));
				course.setSection(this.results.getString("CRS_SEC"));
				course.setCode(this.results.getString("CRS_CD"));
				course.setMeetingDays(this.results.getString("MEETING_DAYS"));
				course.setStartTime(this.results.getString("START_TIME"));
				course.setStartTimeValue(course.getStartTime(), this.results.getString("AM_PM"));
				course.setEndTime(this.results.getString("STOP_TIME"));
				course.setStartDate(this.results.getString("START_DATE"));
				course.setAmPM(this.results.getString("AM_PM"));
				course.setEndDate(this.results.getString("END_DATE"));
				course.setBuilding(this.results.getString("BUILDING"));
				course.setRoom(this.results.getString("RM"));
				course.setInstructor(this.results.getString("INSTRUCTOR_LNAME"));
				course.setSeatsAvailable(this.results.getString("SEATS_AVAIL"));
				course.setCreditHours(this.results.getString("CREDITHOUR"));
				course.setDescription(this.results.getString("DESCRIPTION"));
				course.setPreReq(this.results.getString("PREREQ"));
				course.setComments(this.results.getString("CRS_COMMENTS1"));
				
				
				//Filter courses by time constraint
				if(timeAB.equals("Select All") && timeValue == 0)
					courseList.add(course);			
				else if(timeAB.equals("Select All") && course.getStartTimeValue() == timeValue)
					courseList.add(course);
				else if(timeAB.equals("before") && course.getStartTimeValue() <= timeValue) {
					//System.out.println("Course time: " + course.getStartTimeValue() + "  Time selected: " + timeValue);
					courseList.add(course);
				}
				else if(timeAB.equals("after") && course.getStartTimeValue() >= timeValue)
					courseList.add(course);
				else if(timeAB.equals("around") && course.getStartTimeValue() == timeValue 
					|| course.getStartTimeValue() == (timeValue + 1) 
					|| course.getStartTimeValue() == (timeValue - 1))
					courseList.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}
		return courseList;
	}
	
	public List<Course> getResultsList() {
		System.out.println("Getting result list...");
		List<Course> courseList = new ArrayList<>();
		try {
			while(this.results.next()) {
				Course course = new Course();
				course.setTitle(this.results.getString("TITLE"));
				course.setDept(this.results.getString("DEPT_NAME"));
				course.setDisc(this.results.getString("DISC"));
				course.setCrsNum(this.results.getString("CRS_NUM"));
				course.setSection(this.results.getString("CRS_SEC"));
				course.setCode(this.results.getString("CRS_CD"));
				course.setMeetingDays(this.results.getString("MEETING_DAYS"));
				course.setStartTime(this.results.getString("START_TIME"));
				course.setStartTimeValue(course.getStartTime(), this.results.getString("AM_PM"));
				course.setEndTime(this.results.getString("STOP_TIME"));
				course.setStartDate(this.results.getString("START_DATE"));
				course.setAmPM(this.results.getString("AM_PM"));
				course.setEndDate(this.results.getString("END_DATE"));
				course.setBuilding(this.results.getString("BUILDING"));
				course.setRoom(this.results.getString("RM"));
				course.setInstructor(this.results.getString("INSTRUCTOR_LNAME"));
				course.setSeatsAvailable(this.results.getString("SEATS_AVAIL"));
				course.setCreditHours(this.results.getString("CREDITHOUR"));
				course.setDescription(this.results.getString("DESCRIPTION"));
				course.setPreReq(this.results.getString("PREREQ"));
				course.setComments(this.results.getString("CRS_COMMENTS1"));
				
				courseList.add(course);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}
		System.out.println("Result list complete. Returning courseList...");
		return courseList;
	}
	
//Method for error message 
	public String invalidMessage() {
		return "<H1><font color=red>Invalid Search. Please try again.</font></H1>";
	}
	
}
