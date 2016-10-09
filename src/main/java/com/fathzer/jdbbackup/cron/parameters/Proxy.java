package com.fathzer.jdbbackup.cron.parameters;

public class Proxy {
	private String host;
	private int port = 3128;
	private String user;
	private String pwd;
	
	public void setHost(String host) {
		this.host = host;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getHost() {
		return host;
	}
	public int getPort() {
		return port;
	}
	public String getUser() {
		return user;
	}
	public String getPwd() {
		return pwd;
	}
}
