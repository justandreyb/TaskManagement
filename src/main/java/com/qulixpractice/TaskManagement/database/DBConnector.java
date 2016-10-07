package com.qulixpractice.TaskManagement.database;

import com.qulixpractice.TaskManagement.task.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

class DBConnector {
    private static Logger log = Logger.getLogger(DBConnector.class.getName());

    private Connection connection;
    private Statement statement;
    private String driver;
    private String host;
    private String port;
    private String database;
    private String databaseName;
    private String username;
    private String password;
    private String table;

    private static DBConnector instance;

    static synchronized DBConnector getInstance() {
        if (instance == null) {
            instance = new DBConnector("org.postgresql.Driver",
                    "postgresql",
                    "localhost",
                    "5432",
                    "TaskManagementDB",
                    "postgres",
                    "123456");
        }
        return instance;
    }

    private DBConnector(String driver, String databaseName, String host, String port, String database, String username,
                        String password) {
        initialize(driver, databaseName, host, port, database, username, password);
        setTable("task");
    }

    private void initialize(String driver, String databaseName, String host, String port, String database,
                            String username, String password) {
        try {
            connection = null;
            statement = null;
            this.driver = driver;
            this.databaseName = databaseName;
            this.host = host;
            this.port = port;
            this.database = database;
            this.username = username;
            this.password = password;
        } catch (NullPointerException ex) {
            log.severe(ex.getMessage());
        }
    }

    private void openConnection() {
        try {
            Class.forName(driver);
            StringBuilder connectionInfo = new StringBuilder();
            connectionInfo.append("jdbc:");
            connectionInfo.append(databaseName);
            connectionInfo.append("://");
            connectionInfo.append(host);
            connectionInfo.append(":");
            connectionInfo.append(port);
            connectionInfo.append("/");
            connectionInfo.append(database);

            connection = DriverManager.getConnection(
                    connectionInfo.toString(), username, password);

            statement = connection.createStatement();

        }
        catch (SQLException ex) {
            log.severe(ex.getMessage());
        }
        catch (ClassNotFoundException ex) {
            log.severe(ex.getMessage());
        }
    }

    private void closeConnection() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        catch (SQLException ex) {
            log.severe(ex.getMessage());
        }
    }

    private boolean isConnected() {
        try {
            if (connection != null) {
                if (connection.isClosed()) {
                    return false;
                }
            }
            if (statement != null) {
                if (statement.isClosed()) {
                    return false;
                } else {
                    return true;
                }
            }
            return false;
        }
        catch (SQLException e) {
            log.severe(e.getMessage());
            return false;
        }
        catch (NullPointerException ex) {
            log.severe(ex.getMessage());
            return false;
        }
    }

    private ResultSet executeQuery(String query) {
        if (!isConnected()) {
            openConnection();
        }

        try {
            return statement.executeQuery(query);
        }
        catch (SQLException ex) {
            log.severe(ex.getMessage());
            closeConnection();
            return null;
        }
    }

    void executeUpdate(String query) {
        if (!isConnected()) {
            openConnection();
        }

        try {
            statement.executeUpdate(query);
            closeConnection();
        }
        catch (SQLException ex) {
            log.severe(ex.getMessage());
            closeConnection();
        }
    }

    private ResultSet selectTask(int task_id) {
        try {
            return executeQuery("SELECT * FROM task where task_id = " + task_id);
        }
        catch (NullPointerException ex) {
            log.info(ex.getMessage());
            return null;
        }

    }

    private ResultSet selectTasks() {
        try {
            return executeQuery("SELECT * FROM task");
        }
        catch (NullPointerException ex) {
            log.severe(ex.getMessage());
            return null;
        }
    }

    Task taskCreator(int task_id) {
        Task task = null;
        try {
            ResultSet result = selectTask(task_id);

            while (result.next()) {
                task = new Task(
                        result.getString("task_name"),
                        result.getString("project_name"),
                        result.getInt("work"),
                        result.getDate("start_date"),
                        result.getDate("finish_date"),
                        result.getString("progress"),
                        result.getString("executor"));
                task.setTaskID(result.getInt("task_id"));
            }
            return task;

        }
        catch (NullPointerException ex) {
            log.severe(ex.getMessage());
            closeConnection();
            return null;
        }
        catch (Exception ex) {
            log.severe(ex.getMessage());
            closeConnection();
            return null;
        }
        finally {
            closeConnection();
        }
    }

    ArrayList<Task> tasksCreator() {
        ArrayList<Task> list = new ArrayList<Task>();

        try {
            ResultSet result = selectTasks();
            if (result != null) {
                while (result.next()) {
                    list.add(new Task(
                            result.getInt("task_id"),
                            result.getString("task_name"),
                            result.getString("project_name"),
                            result.getInt("work"),
                            result.getDate("start_date"),
                            result.getDate("finish_date"),
                            result.getString("progress"),
                            result.getString("executor")
                    ));
                }

                return list;
            } else {
                log.info("Error in create list of tasks");
                closeConnection();
                return null;
            }
        }
        catch (Exception ex) {
            log.severe(ex.getMessage());
            closeConnection();
            return null;
        }
        finally {
            closeConnection();
        }
    }

    private void setTable(String table) {
        try {
            this.table = table;
        } catch (NullPointerException ex) {
            log.info(ex.getMessage());
        }
    }

    String getTable() {
        return table;
    }

}
