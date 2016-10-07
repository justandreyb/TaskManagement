package com.qulixpractice.TaskManagement.task;

import java.io.Serializable;
import java.util.ArrayList;

public class Model implements Serializable {

    private ArrayList<Task> taskList;
    private Task task;

    public Model() {

        setTask(null);
        setTaskList(null);
    }

    public ArrayList<Task> getTaskList() {

        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {

        this.taskList = taskList;
    }

    public Task getTask() {

        return task;
    }

    public void setTask(Task task) {

        this.task = task;
    }

}
