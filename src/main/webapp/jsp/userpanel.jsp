<%@ page import="com.qulixpractice.TaskManagement.task.Task" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.qulixpractice.TaskManagement.server.Commands.GetCommand" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="templates/header.jsp"%>

<%@ include file="templates/navbar-button.jsp"%>

            <div class="container-table col-xs-12 col-sm-12 col-md-12 col-lg-12">

                <div style="margin-top: 70px;"></div>

                <div class="col-md-1 col-xs-1 col-lg-2"></div>
                <div class="col-md-10 col-xs-10 col-lg-8">
                    <div class="table-responsive">
                        <table class="table table-hover table-condensed table-responsive" id="table">
                            <thead>
                                <tr class="" style="color: #0F3F5E;">
                                    <th>ID</th>
                                    <th>Project</th>
                                    <th>Task name</th>
                                    <th>Start date</th>
                                    <th>Finish date</th>
                                    <th>Executor</th>
                                    <th>Progress</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    GetCommand command = new GetCommand();
                                    ArrayList<Task> tasks = command.getModel().getTaskList();
                                    try {
                                        if (tasks == null) {
                                            tasks = new ArrayList<>();
                                            tasks.add(Task.setEmptyTask());
                                        }
                                    } catch (NullPointerException ex) {
                                        ex.printStackTrace();
                                    }

                                %>

                                <% for (Task task : tasks) {%>
                                <tr>
                                    <form>
                                        <td><input name="task_id" value="<%=task.getTaskID()%>" hidden><%=task.getTaskID()%></td>
                                        <td><%=task.getProjectName()%></td>
                                        <td><%=task.getTaskName()%></td>
                                        <td><%=task.getStartDate().toString()%></td>
                                        <td><%=task.getFinishDate().toString()%></td>
                                        <td><%=task.getExecutor()%></td>
                                        <td><%=task.getProgress()%></td>
                                        <td>
                                            <div class="row">
                                                <div class="col-xs-6">
                                                    <button name="button" value="ChangeTask" formmethod="get"><i class="fa fa-pencil" aria-hidden="true"></i></button>
                                                </div>
                                                <div class="col-xs-6">
                                                    <button name="button" value="DeleteTask" formmethod="get"><i class="fa fa-eraser" aria-hidden="true"></i></button>
                                                </div>
                                            </div>
                                        </td>
                                    </form>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-md-1 col-xs-1 col-lg-2"></div>
            </div>

            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="height: 50px"></div>

<%@ include file="templates/footer.jsp"%>