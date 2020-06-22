package manager;

import db.DBConnectionProvider;
import models.Status;
import models.Task;
import util.DateUtil;

import java.sql.*;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

public class TaskManager {


    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    private UserManager userManager = new UserManager();


    public boolean add(Task task) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO task (name,description,deadline,status,user_id)Values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setString(3, DateUtil.convertDateToString(task.getDeadline()));
            preparedStatement.setString(4, (task.getStatus().name()));
            preparedStatement.setInt(5, task.getUserId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                task.setId(id);
            }
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new LinkedList<Task>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM task");
           tasks=getTasksFromResultset(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public List<Task> getAllTasksByUserId(int userId) {
        List<Task> tasks = new LinkedList<Task>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM task WHERE user_id=?");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            tasks = getTasksFromResultset(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }



    public void updateStatus(long id, String status) {
        String sql = "UPDATE task SET status = '" + status + "' WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(long id) {
        String sql = "DELETE FROM task WHERE id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    private List<Task> getTasksFromResultset(ResultSet resultSet) throws SQLException {
        List <Task> tasks=new LinkedList<Task>();
        while (resultSet.next()) {
            Task task = new Task();
            task.setId(resultSet.getInt(1));
            task.setName(resultSet.getString(2));
            task.setDescription(resultSet.getString(3));
            try {
                task.setDeadline(DateUtil.SDF.parse(resultSet.getString(4)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            task.setStatus(Status.valueOf(resultSet.getString(5)));
            task.setUserId(resultSet.getInt(6));
            task.setUser(userManager.getUserById(task.getUserId()));
            tasks.add(task);
        }
        return tasks;
    }



}
