package model;

public class Week {
	private String days;
	
	public String convertWeek(String week) {
		String w = null;
		switch(week) {
		case "Mon-Wed":w = "MW";
			break;
		case "Wed":w = "W";
			break;
		case "TBA":w = "HTBA";
			break;
		case "Tue-Fri":w = "TF";
			break;
		case "Sun":w = "SU";
			break;
		case "Mon-Tue-Wed-Thu":w = "MTWTH";
			break;
		case "Mon-Wed-Thu":w = "MWTH";
			break;	
		case "Mon-Tue-Wed":w = "MTW";
			break;
		case "Tue-Thu":w = "TTH";
			break;
		case "Mon":w = "M";
			break;
		case "Mon-Tue-Thu":w = "MTTH";
			break;
		case "Tue-Wed-Thu":w = "TWTH";
			break;
		case "Tue":w = "T";
			break;
		case "Mon-Thu":w = "MTH";
			break;
		case "Mon-Tue":w = "MT";
			break;
		case "Wed-Fri":w = "WF";
			break;
		case "Thu":w = "TH";
			break;
		case "Sat-Sun":w = "SSU";
			break;
		case "Sat":w = "S";
			break;
		case "Fri":w = "F";
			break;
		case "Tu-Thu":w = "TTH";
			break;
		case "Select All":w = "%";
			break;
		default: 
			w = "%";
	}	
		return w;
	}
	
	public void setDays(String days){
		this.days = days;
	}
	public String getDays(){
		return days;
	}
	
	
}
