package com.example.csci217;

    
public class Time {
	private int hours;
	private int minutes;
	private int seconds;
	
	public Time() {
		
	}
	
	public Time(int hours, int minutes, int seconds){
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	public Time(String time) {
		String[] timeArray = time.split(":");
		this.hours = Integer.parseInt(timeArray[0]);
		this.minutes = Integer.parseInt(timeArray[1]);
		this.seconds = Integer.parseInt(timeArray[2]);
	}
	
	public String getTime() {
		String str_hours = String.valueOf(this.hours);
		if(this.hours < 10) {
			str_hours = "0" + this.hours;
		}
		String str_minutes = String.valueOf(this.minutes);
		if(this.minutes < 10) {
			str_minutes = "0" + this.minutes;
		}
		String str_seconds = String.valueOf(this.seconds);
		if(this.seconds < 10) {
			str_seconds = "0" + this.seconds;
		}
		return str_hours + ":" + str_minutes + ":" + str_seconds;
	}
	
	public void OneSecondBack() {
		if(hours == 0 && minutes == 0 && seconds == 0) {
			hours = 0;
			minutes = 0;
			seconds = 0;
		}else {
			if(seconds != 0) {
				seconds--;
			}else { //if seconds = 0
				if(minutes != 0) {
					minutes --;
					seconds = 59;
				}else { //if minutes = 0
					hours --;
					minutes = 59;
					seconds = 59;
				}
			}
		}
		
		
		
		
		
	}
}

    
