package com.fathzer.jdbbackup.cron.parameters;

public class DataBase {
	private String type="mySQL";
	private String base;
	private String user="root";
	private String pwd;
	private String host;
	private int port=3306;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBase() {
		return base;
	}
	public String getUser() {
		return user;
	}
	public String getPwd() {
		return pwd;
	}
	public String getHost() {
		return host;
	}
	public int getPort() {
		return port;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public void setPort(int port) {
		this.port = port;
	}
}
