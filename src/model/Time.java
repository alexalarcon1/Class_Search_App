package model;

public class Time {
	private String timeAB;
	private int timeValue;

	public int getTimeValue() {
		return timeValue;
	}

	public void setTimeValue(String time) {
		switch(time){
		case "0": this.timeValue = 0;
			break;
		case "7": this.timeValue = 7;
			break;
		case "8": this.timeValue = 8;
			break;
		case "9": this.timeValue = 9;
			break;
		case "10": this.timeValue = 10;
			break;
		case "11": this.timeValue = 11;
			break;
		case "12": this.timeValue = 12;
			break;
		case "13": this.timeValue = 13;
			break;
		case "14": this.timeValue = 14;
			break;
		case "15": this.timeValue = 15;
			break;
		case "16": this.timeValue = 16;
			break;
		case "17": this.timeValue = 17;
			break;
		case "18": this.timeValue = 18;
			break;
		case "19": this.timeValue = 19;
			break;
		case "20": this.timeValue = 20;
			break;
		case "21": this.timeValue = 21;
			break;
		
	}
		
	}
	public String getTimeAB() {
		return timeAB;
	}

	public void setTimeAB(String timeAB) {
		this.timeAB = timeAB;
	}
}
