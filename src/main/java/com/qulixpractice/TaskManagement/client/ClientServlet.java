package com.qulixpractice.TaskManagement.client;

import com.qulixpractice.TaskManagement.server.Commands.GetCommand;
import com.qulixpractice.TaskManagement.server.ICommand;
import com.qulixpractice.TaskManagement.task.Model;
import com.qulixpractice.TaskManagement.task.Task;
import com.qulixpractice.TaskManagement.utils.CommandsMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.logging.Logger;

public class ClientServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(ClientServlet.class.getName());
    private CommandsMap commands = new CommandsMap();

    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        log.severe("here");
        try {
            ICommand command = commands.getExecutor(req.getParameter("button"));
            if (command != null) {
                Model model = new Model();
                Task task = new Task(
                        req.getParameter("taskName"),
                        req.getParameter("projectName"),
                        Integer.parseInt(req.getParameter("work")),
                        Date.valueOf(req.getParameter("startDate")),
                        Date.valueOf(req.getParameter("finishDate")),
                        req.getParameter("progress"),
                        req.getParameter("executor"));

                if (req.getParameter("taskID") != null) {
                    task.setTaskID(Integer.parseInt(req.getParameter("taskID")));
                }
                model.setTask(task);
                command.execute(model);

                openPage(req, resp);
            } else {
                log.info("Server can't do this action");
                try {
                    resp.sendRedirect("/error.html");
                } catch (IOException ex) {
                    log.severe(ex.getMessage());
                }
            }
        } catch (NullPointerException ex) {
            log.severe(ex.getMessage());
            openPage(req, resp);
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (req.getParameter("button") != null) {
                processingAction(req.getParameter("button"), req, resp);
            } else {
                openPage(req, resp);
            }
        } catch (NullPointerException ex) {
            log.severe(ex.getMessage());
        }
    }

    private void openPage(HttpServletRequest req, HttpServletResponse resp) {
        openPage(req.getRequestURI(), req, resp);
    }

    private void openPage(String URI, HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (req.getRequestURL() != null) {

                if (URI != null) {
                    getServletContext().getRequestDispatcher("/jsp/" + URI + ".jsp").forward(req, resp);
                } else {
                    getServletContext().getRequestDispatcher("index" + ".jsp").forward(req, resp);
                }
            } else {
                resp.sendRedirect("/error.html");
            }
        }
        catch (ServletException ex) {
            log.severe("SERVLET " + ex.getMessage());
            try {
                resp.sendRedirect("/error.html");
            } catch (IOException e) {
                log.severe(e.getMessage());
            }
        }
        catch (IOException ex) {
            log.severe("IO " + ex.getMessage());
            try {
                resp.sendRedirect("/error.html");
            } catch (IOException e) {
                log.severe(e.getMessage());
            }
        }
    }

    private void processingAction(String action, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        try {
            if (action.contains("ChangeTask")) {
                int taskID = getTaskID(request);
                session.setAttribute("task", getTask(taskID));
                session.setAttribute("isEdit", "true");

                openPage("task", request, response);
            } else if (action.contains("AddNewTask")) {
                session.setAttribute("isEdit", "false");

                openPage("task", request, response);
            } else {
                Model model = new Model();
                Task task = getTask(getTaskID(request));
                if (task != null) {
                    model.setTask(task);
                }
                commands.getExecutor(action).execute(model);
                openPage(request, response);
            }
        } catch (NullPointerException ex) {
            log.info(ex.getMessage());
            openPage(request, response);
        }
    }

    private int getTaskID(HttpServletRequest request) {

        try {
            int taskID;
            taskID = Integer.parseInt(request.getParameter("task_id"));
            if (taskID >= 0) {
                return taskID;
            } else {
                return 0;
            }
        }
        catch (NullPointerException ex) {
            log.severe(ex.getMessage());
            return 0;
        }
    }

    private Task getTask(int task_id) {
        try {
            GetCommand command = new GetCommand();
            return command.getModel(task_id).getTask();
        } catch (NullPointerException ex) {
            log.severe(ex.getMessage());
            return null;
        }
    }

}
