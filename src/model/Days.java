package model;

public class Days {
	private String meetingDays;
	private static final String query = "select distinct meeting_days " + 
										"from CRS_SEC_SR " +
										"where meeting_days <> 'ONLINE' " +
										"and meeting_days <> 'null'";

	public String getMeetingDays() {
		return meetingDays;
	}
	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}
	public String getQuery() {
		return query;
	}
}
