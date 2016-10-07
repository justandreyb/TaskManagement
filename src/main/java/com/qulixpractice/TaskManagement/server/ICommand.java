package com.qulixpractice.TaskManagement.server;

import com.qulixpractice.TaskManagement.task.Model;

import java.sql.SQLException;

public interface ICommand {

    void execute(Model model);
}
