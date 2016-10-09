package com.fathzer.jdbbackup;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.sauronsoftware.cron4j.Scheduler;

public class Main {
	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
	
	private boolean failed;
	
	private Main() {
		this.failed = false;
	}
	
	public static void main(String[] args) {
		try {
			System.setProperty("java.util.logging.SimpleFormatter.format", 
		            "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");
			new Main().doIt(args);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "unexpected exception", e);
		}
	}
	
	private void doIt(String[] args) throws IOException {
		LOGGER.log(Level.INFO,"Starting docker Db backup "+Messages.getString("version"));
		File file = getTasksFile(args);
		if (file!=null) {
			ObjectMapper mapper = new ObjectMapper();
			LOGGER.log(Level.INFO,"Loading tasks file");
			final Parameters params = mapper.readValue(file, Parameters.class);
			for (final TaskParameters task : params.getTasks()) {
				Scheduler scheduler = new Scheduler();
				scheduler.schedule(toCron4JSchedule(task.getSchedule()), new Runnable() {
					@Override
					public void run() {
						try {
							String message = new JDbBackup().backup(toOptions(params.getProxy(), task));
							LOGGER.log(Level.INFO, task.getName()+": "+message);
						} catch (Throwable e) {
							LOGGER.log(Level.SEVERE, task.getName()+" failed", e);
						}
					}
				});
				scheduler.start();
				LOGGER.log(Level.INFO,task.getName()+" is scheduled");
			}
		}
	}

	protected Options toOptions(ProxyParameters proxy, TaskParameters task) {
		Options options = new Options();
		if (proxy!=null) {
			options.setProxyHost(proxy.getHost());
			options.setProxyPort(proxy.getPort());
			options.setProxyUser(proxy.getUser());
			options.setProxyPwd(proxy.getPwd());
		}
		options.setDbHost(task.getHost());
		options.setDbPort(task.getPort());
		options.setDbName(task.getBase());
		options.setDbUser(task.getUser());
		options.setDbPwd(task.getPwd());
		options.setDestination(task.getDestination());
		return options;
	}

	public File getTasksFile(String[] args) {
		String filePath;
		if (args.length==0) {
			filePath = System.getenv("TASKS"); //$NON-NLS-1$
			if (filePath==null) {
				filePath = "tasks.json"; //$NON-NLS-1$
			}
		} else {
			filePath = args[0];
		}
		File file = new File(filePath);
		if (!file.exists()) {
			fail("File "+filePath+" does not exists");
		} else if (!file.isFile()) {
			fail("File "+filePath+" is not a file");
		} else if (!file.canRead()) {
			fail("Can't read file "+filePath);
		}
		return hasFailed() ? null : file;
	}
	
	private void fail(String message) {
		LOGGER.log(Level.SEVERE,message);
		this.failed = true;
	}
	
	private boolean hasFailed() {
		return this.failed;
	}
	
	private static String toCron4JSchedule(String schedule) {
		if ("@yearly".equals(schedule) || "@annually".equals(schedule)) { //$NON-NLS-1$ //$NON-NLS-2$
			return "0 0 1 1 *"; //$NON-NLS-1$
		} else if ("@monthly".equals(schedule)) { //$NON-NLS-1$
			return "0 0 1 * *"; //$NON-NLS-1$
		} else if ("@weekly".equals(schedule)) { //$NON-NLS-1$
			return "0 0 * * 0"; //$NON-NLS-1$
		} else if ("@daily".equals(schedule) || "@midnight".equals(schedule)) { //$NON-NLS-1$ //$NON-NLS-2$
			return "0 0 * * *"; //$NON-NLS-1$
		} else if ("@hourly".equals(schedule)) { //$NON-NLS-1$
			return "0 * * * *"; //$NON-NLS-1$
		} else {
			return schedule;
		}
	}
}
