package com.qulixpractice.TaskManagement.server.Commands;

import com.qulixpractice.TaskManagement.database.DAOTask;
import com.qulixpractice.TaskManagement.server.ICommand;
import com.qulixpractice.TaskManagement.task.Model;

public class DeleteCommand implements ICommand {

    public void execute(Model model) {

        DAOTask database = new DAOTask();

        database.deleteTask(model.getTask().getTaskID());

    }
}
