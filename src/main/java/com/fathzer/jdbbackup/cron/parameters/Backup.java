package com.fathzer.jdbbackup.cron.parameters;

public class Backup {
	private String name;
	private String schedule;
	private String destination;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchedule() {
		return schedule;
	}
	public String getDestination() {
		return destination;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public void setDestination(String dest) {
		this.destination = dest;
	}
}
