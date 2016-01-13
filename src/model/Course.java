package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Course {
	private String disc;
	private String title;
	private String dept;
	private String crsNum;
	private String code;
	private String section;
	private String meetingDays;
	private String startTime;
	private String endTime;
	private int startTimeValue;
	private String amPM;
	private String startDate;
	private String endDate;
	private String building;
	private String room;
	private String instructor;
	private String seatsAvailable;
	private String comments;
	private String lastUpdated;
	private String gradLevel;
	private String creditHours;
	private String description;
	private String preReq;
	
	public String getGradLevel() {
		return gradLevel;
	}
	public void setGradLevel(String gradLevel) {
		this.gradLevel = gradLevel;
	}
	public String getDisc() {
		return disc;
	}
	public void setDisc(String disc) {
		this.disc = disc;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getCrsNum() {
		return crsNum;
	}
	public void setCrsNum(String crsNum) {
		this.crsNum = crsNum;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getMeetingDays() {
		return meetingDays;
	}
	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}
	public String getStartTime() {
		return startTime;
	}
//Edit the course start time
	public void setStartTime(String startTime) {
		if(startTime.startsWith("0", 0)) {
			this.startTime = startTime.substring(1);
			return;
		}
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
//Edit the course end time
	public void setEndTime(String endTime) {
		if(endTime.startsWith("0", 0)) {
			this.endTime = endTime.substring(1);
			return; 	
		}
		this.endTime = endTime;
	}
//Convert start date format
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		String strTmp = startDate.substring(0, 10);
		Date dtTmp;
		try {
			dtTmp = new SimpleDateFormat("yyyy-MM-dd").parse(strTmp);
			String strOutDt = new SimpleDateFormat("MM/dd/yy").format(dtTmp);
			
			this.startDate = strOutDt;

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//Convert end date format
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		String strTmp = endDate.substring(0, 10);
		Date dtTmp;
		try {
			dtTmp = new SimpleDateFormat("yyyy-MM-dd").parse(strTmp);
			String strOutDt = new SimpleDateFormat("MM/dd/yy").format(dtTmp);
	
			this.endDate = strOutDt;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getSeatsAvailable() {
		return seatsAvailable;
	}
	public void setSeatsAvailable(String seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public String getAmPM() {
		return amPM;
	}
	public void setAmPM(String amPM) {
		this.amPM = amPM;
	}
	public int getStartTimeValue() {
		return startTimeValue;
	}
	public void setStartTimeValue(String startTime, String amPM) {
		if(startTime.substring(0, 1).equals("7") && amPM.equals("AM"))
			this.startTimeValue = 7; 
		else if(startTime.substring(0, 1).equals("8") && amPM.equals("AM"))
			this.startTimeValue = 8;
		else if(startTime.substring(0, 1).equals("9") && amPM.equals("AM"))
			this.startTimeValue = 9;
		else if(startTime.substring(0, 1).equals("10") && amPM.equals("AM"))
			this.startTimeValue = 10;
		else if(startTime.substring(0, 1).equals("11") && amPM.equals("AM"))
			this.startTimeValue = 11;
		else if(startTime.substring(0, 1).equals("12") && amPM.equals("PM"))
			this.startTimeValue = 12;
		else if(startTime.substring(0, 1).equals("1") && amPM.equals("PM"))
			this.startTimeValue = 13;
		else if(startTime.substring(0, 1).equals("2") && amPM.equals("PM"))
			this.startTimeValue = 14;
		else if(startTime.substring(0, 1).equals("3") && amPM.equals("PM"))
			this.startTimeValue = 15;
		else if(startTime.substring(0, 1).equals("4") && amPM.equals("PM"))
			this.startTimeValue = 16;
		else if(startTime.substring(0, 1).equals("5") && amPM.equals("PM"))
			this.startTimeValue = 17;
		else if(startTime.substring(0, 1).equals("6") && amPM.equals("PM"))
			this.startTimeValue = 18;
		else if(startTime.substring(0, 1).equals("7") && amPM.equals("PM"))
			this.startTimeValue = 19; 
		else if(startTime.substring(0, 1).equals("8") && amPM.equals("PM"))
			this.startTimeValue = 20; 
		else if(startTime.substring(0, 1).equals("9") && amPM.equals("PM"))
			this.startTimeValue = 21; 
	}
	public void setStartTimeValue(int startTimeValue) {
		this.startTimeValue = startTimeValue;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreditHours() {
		return creditHours;
	}
	public void setCreditHours(String creditHours) {
		this.creditHours = creditHours;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPreReq() {
		return preReq;
	}
	public void setPreReq(String preReq) {
		this.preReq = preReq;
	}
	
	
}
