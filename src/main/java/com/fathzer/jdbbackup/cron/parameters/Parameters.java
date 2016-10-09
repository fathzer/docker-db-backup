package com.fathzer.jdbbackup.cron.parameters;

public class Parameters {
	private Proxy proxy;
	private Task[] tasks;
	public Proxy getProxy() {
		return proxy;
	}
	public Task[] getTasks() {
		return tasks;
	}
	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}
	public void setTasks(Task[] tasks) {
		this.tasks = tasks;
	}
}
