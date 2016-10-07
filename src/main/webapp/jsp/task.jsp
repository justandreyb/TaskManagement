<%@ page import="com.qulixpractice.TaskManagement.task.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="templates/header.jsp"%>

<%@ include file="templates/navbar.jsp"%>

<%

            Task task = Task.setEmptyTask();

            boolean newTask = true;
            try {
                String isEdit = (String) session.getAttribute("isEdit");
                Task currentTask = (Task) session.getAttribute("task");

                if (isEdit != null) {
                    if (isEdit.equals("true")) {
                        session.setAttribute("isEdit", "false");
                        newTask = false;
                    }
                }
                if (!newTask) {
                    if (currentTask != null) {
                        task = currentTask;
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                response.sendRedirect("/error.html");
            }
        %>

        <div class="container-table" style="margin-top: 70px;">
            <div class="row col-md-4 col-lg-4"></div>
            <div class="container col-md-4 col-lg-4">
                <form class="form-horizontal" id="taskForm" method="post" action="/userpanel" >

                    <div class="form-group">
                        <label class="control-label col-xs-3">Task ID</label>
                        <div class="col-xs-9">
                            <input name="taskID" value="<%=task.getTaskID()%>" hidden>
                            <p class="form-control-static" id="task_id">
                                <% if (task.getTaskID() != 0) {%>
                                <%=task.getTaskID()%>
                                <%} else {%>
                                New task
                                <%}%>
                            </p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-xs-3">Project</label>
                        <div class="col-xs-9">
                            <input type="input" name="projectName" id="project" value="<%=task.getProjectName()%>" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-xs-3">Task name</label>
                        <div class="col-xs-9">
                            <input type="input" name="taskName" id="taskName" value="<%=task.getTaskName()%>" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-xs-3">Work(Hours)</label>
                        <div class="col-xs-6">
                            <input type="number" size="3" name="work" min="1" max="168" value="<%=String.valueOf(task.getWork())%>" id="work" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-xs-3">Date start</label>
                        <div class="col-xs-9">
                            <input type="date" name="startDate" class="form-control" value="<%=String.valueOf(task.getStartDate())%>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-xs-3">Date finish</label>
                        <div class="col-xs-9">
                            <input type="date" name="finishDate" class="form-control" value="<%=String.valueOf(task.getStartDate())%>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-xs-3">Progress</label>
                        <div class="col-xs-9">
                            <%--TODO: Get values from list--%>
                            <select class="form-control input-md" name="progress" required>
                                <option value="Not started">Not started</option>
                                <option value="In process">In process</option>
                                <option value="Finished">Finished</option>
                                <option value="Postponed">Postponed</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-xs-3">Executor</label>
                        <div class="col-xs-9 col-md-9">
                            <input type="input" name="executor" value="<%=task.getExecutor()%>" class="form-control">
                        </div>
                    </div>

                    <hr>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="col-xs-offset-1 col-xs-10">
                                    <a class="btn btn-md btn-primary btn-block" href="/userpanel">CANCEL</a>
                                </div>
                            </div>
                            <div class="col-xs-6">
                                <div class="col-xs-offset-1 col-xs-10">
                                    <button class="btn btn-md btn-primary btn-block" type="submit" name="button" value="<%if(!newTask){%>EditTask<%}else{%>AddTask<%}%>" formmethod="post">SEND</button>
                                </div>
                            </div>
                        </div>
                    </div>

                </form>
            </div>
            <div class="row col-md-4 col-lg-4"></div>
        </div>

<%@ include file="templates/footer.jsp"%>