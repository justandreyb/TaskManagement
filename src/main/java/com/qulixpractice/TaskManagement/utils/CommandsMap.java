package com.qulixpractice.TaskManagement.utils;

import com.qulixpractice.TaskManagement.server.Commands.AddCommand;
import com.qulixpractice.TaskManagement.server.Commands.DeleteCommand;
import com.qulixpractice.TaskManagement.server.Commands.EditCommand;
import com.qulixpractice.TaskManagement.server.ICommand;

import java.util.HashMap;
import java.util.Map;

public class CommandsMap {

    private Map<String, ICommand> Commands;

    public CommandsMap() {

        Commands = new HashMap<String, ICommand>(3);
        Commands.put("AddTask", new AddCommand());
        Commands.put("EditTask", new EditCommand());
        Commands.put("DeleteTask", new DeleteCommand());
    }

    public ICommand getExecutor(String action) {

        if (Commands.containsKey(action)) {
            return Commands.get(action);
        } else {
            return null;
        }
    }
}
