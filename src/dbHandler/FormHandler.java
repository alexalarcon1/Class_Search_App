package dbHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.*;


public class FormHandler extends DatabaseHandler {
		
	public void executeQuery(String query) {
		try {
			ps = this.conn.prepareStatement(query);
			this.results = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
//Create a list of semester sessions and return that list
	public List<Session> getSessionList(String column) {
		List<Session> sessList = new ArrayList<>();
		try {
			while(this.results.next()) {
				Session newSession = new Session(); //Create a new session object for each semester session
				newSession.setName(this.results.getString(column));
				sessList.add(newSession);
				//System.out.println("Session : " + newSession.getName()); //Test if data is being pulled from database 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sessList;
	}
	
//Create a list of disciplines and return that list
	public List<Discipline> getDiscList(String column) {
		List<Discipline> discList = new ArrayList<>();
		try {
			while(this.results.next()) {
				Discipline newDiscipline = new Discipline();
				newDiscipline.setName(this.results.getString(column));
				discList.add(newDiscipline);
				//System.out.println("Discipline: " + newDiscipline.getName()); //Test if data is being pulled from database 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return discList;
	}
//Create a list of days, format the days, and return that list
	public List<Days> getDaysList (String column) {
		List<Days> meetingsList = new ArrayList<>();
		try {
			while(this.results.next()) {
				Days newDay = new Days();
				newDay.setMeetingDays(this.results.getString(column));
				
				switch(newDay.getMeetingDays()) {
					case "MW":newDay.setMeetingDays("Mon-Wed");
						break;
					case "W":newDay.setMeetingDays("Wed");
						break;
					case "HTBA":newDay.setMeetingDays("TBA");
						break;
					case "TF":newDay.setMeetingDays("Tue-Fri");
						break;
					case "SU":newDay.setMeetingDays("Sun");
						break;
					case "MTWTH":newDay.setMeetingDays("Mon-Tue-Wed-Thu");
						break;
					case "MWTH":newDay.setMeetingDays("Mon-Wed-Thu");
						break;	
					case "MTW":newDay.setMeetingDays("Mon-Tue-Wed");
						break;
					case "TTH":newDay.setMeetingDays("Tue-Thu");
						break;
					case "M":newDay.setMeetingDays("Mon");
						break;
					case "MTTH":newDay.setMeetingDays("Mon-Tue-Thu");
						break;
					case "TWTH":newDay.setMeetingDays("Tue-Wed-Thu");
						break;
					case "T":newDay.setMeetingDays("Tue");
						break;
					case "MTH":newDay.setMeetingDays("Mon-Thu");
						break;
					case "MT":newDay.setMeetingDays("Mon-Tue");
						break;
					case "WF":newDay.setMeetingDays("Wed-Fri");
						break;
					case "TH":newDay.setMeetingDays("Thu");
						break;
					case "SSU":newDay.setMeetingDays("Sat-Sun");
						break;
					case "S":newDay.setMeetingDays("Sat");
						break;
					case "F":newDay.setMeetingDays("Fri");
						break;
					case "TTHF":newDay.setMeetingDays("Tue-Thu-Fri");
						break;
					default:newDay.setMeetingDays("TBA");
			    		break;
				}	
				
				meetingsList.add(newDay);
				//System.out.println("Meetings: " + newDay.getMeetingDays());		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return meetingsList;
	}
	


	
}
