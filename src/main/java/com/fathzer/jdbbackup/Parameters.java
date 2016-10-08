package com.fathzer.jdbbackup;

public class Parameters {
	private ProxyParameters proxy;
	private TaskParameters[] tasks;
	public ProxyParameters getProxy() {
		return proxy;
	}
	public TaskParameters[] getTasks() {
		return tasks;
	}
	public void setProxy(ProxyParameters proxy) {
		this.proxy = proxy;
	}
	public void setTasks(TaskParameters[] tasks) {
		this.tasks = tasks;
	}
}
