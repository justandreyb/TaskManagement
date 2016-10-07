package com.qulixpractice.TaskManagement.database;
import com.qulixpractice.TaskManagement.task.Task;

import java.util.ArrayList;
import java.util.logging.Logger;

public class DAOTask {
    private static Logger log = Logger.getLogger(DAOTask.class.getName());

    private DBConnector database;

    public DAOTask() {
        try {
            database = DBConnector.getInstance();
        } catch (NullPointerException ex) {
            log.severe(ex.getMessage());
        }
    }

    public Task getTask(int task_id) {
        try {
            return database.taskCreator(task_id);
        } catch (NullPointerException ex) {
            log.severe(ex.getMessage());
            return null;
        }
    }

    public ArrayList<Task> getTasks() {
        try {
            return database.tasksCreator();
        } catch (NullPointerException ex) {
            log.severe(ex.getMessage());
            return null;
        }
    }

    public boolean updateTask(Task task) {
        try {
            database.executeUpdate("UPDATE " + database.getTable() + " " +
                    "SET task_name = '" + task.getTaskName() + "', " +
                    "project_name = '" + task.getProjectName() + "', " +
                    "work = " + task.getWork() + ", " +
                    "start_date = '" + task.getStartDate() + "', " +
                    "finish_date = '" + task.getFinishDate() + "', " +
                    "progress = '" + task.getProgress() + "', " +
                    "executor = '" + task.getExecutor() + "' " +
                    "WHERE task_id = " + task.getTaskID());

            return true;
        }
        catch (NullPointerException ex) {
            log.severe(ex.getMessage());
            return false;
        }
    }

    public boolean deleteTask(int task_id) {
        try {
            database.executeUpdate("DELETE FROM " + database.getTable() + " WHERE task_id = " + task_id);

            return true;
        }
        catch (NullPointerException ex) {
            log.severe(ex.getMessage());
            return false;
        }
    }

    public boolean addTask(Task task) {
        try {
            database.executeUpdate("INSERT INTO " + database.getTable() +
                    "(task_name, project_name, work, start_date, finish_date, progress, executor) VALUES(" +
                    "'" + task.getTaskName() + "'," +
                    "'" + task.getProjectName() + "', " +
                    task.getWork() + ", " +
                    "'" + task.getStartDate() + "', " +
                    "'" + task.getFinishDate() + "', " +
                    "'" + task.getProgress() + "', " +
                    "'" + task.getExecutor() + "'" +
                    ")");

            return true;
        }
        catch (NullPointerException ex) {
            log.severe(ex.getMessage());
            return false;
        }
    }

}

/*
* UPDATE task SET task_name = 'TaskManager', project_name = 'qulixpractice', work = 168, start_date = '2016-06-06', finish_date = '2016-06-06', progress = 'Don't Start', executor = 'Andrey' WHERE task_id = 22
* */