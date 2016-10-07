package com.qulixpractice.TaskManagement.server.Commands;

import com.qulixpractice.TaskManagement.database.DAOTask;
import com.qulixpractice.TaskManagement.server.ICommand;
import com.qulixpractice.TaskManagement.task.Model;

public class GetCommand implements ICommand {

    private DAOTask database = new DAOTask();

    public void execute(Model model) {
    }

    public Model getModel() {
        Model model = new Model();
        model.setTaskList(database.getTasks());
        return model;
    }

    public Model getModel(int task_id) {
        Model model = new Model();
        model.setTask(database.getTask(task_id));
        return model;
    }

}
