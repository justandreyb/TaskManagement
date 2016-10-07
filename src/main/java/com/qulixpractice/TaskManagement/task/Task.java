package com.qulixpractice.TaskManagement.task;

import java.io.Serializable;
import java.sql.Date;

public class Task implements Serializable {

    private int taskID;
    private String taskName;
    private String projectName;
    private String progress;
    private String executor;
    private int work;
    private Date startDate;
    private Date finishDate;

    public Task(String taskName, String projectName, int work, Date startDate, Date finishDate, String progress,
                String executor) {
        setTaskName(taskName);
        setProjectName(projectName);
        setWork(work);
        setStartDate(startDate);
        setFinishDate(finishDate);
        setProgress(progress);
        setExecutor(executor);
        setTaskID(0);
    }

    public Task(int taskID, String taskName, String projectName, int work, Date startDate, Date finishDate,
                String progress, String executor) {
        setTaskName(taskName);
        setProjectName(projectName);
        setWork(work);
        setStartDate(startDate);
        setFinishDate(finishDate);
        setProgress(progress);
        setExecutor(executor);
        setTaskID(taskID);
    }

    public String getTaskName() {
        return taskName;
    }

    private void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getWork() {
        return work;
    }

    private void setWork(int work) {
        this.work = work;
    }

    public Date getStartDate() {
        return startDate;
    }

    private void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    private void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getExecutor() {
        return executor;
    }

    private void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getProjectName() {
        return projectName;
    }

    private void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public static Task setEmptyTask() {
        try {
            Task task = new Task("", "", 0, Date.valueOf("2016-1-1"), Date.valueOf("2016-1-1"), "", "");
            task.setTaskID(0);
            return task;
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
