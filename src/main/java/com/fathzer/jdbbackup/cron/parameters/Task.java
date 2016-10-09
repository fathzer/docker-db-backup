package com.fathzer.jdbbackup.cron.parameters;

public class Task {
	private DataBase database;
	private Backup[] backups;

	public DataBase getDatabase() {
		return database;
	}
	public void setDatabase(DataBase database) {
		this.database = database;
	}
	public Backup[] getBackups() {
		return backups;
	}
	public void setBackups(Backup[] backups) {
		this.backups = backups;
	}
}
