package DBClient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pojo.Task;

public class DBClient {

    private static Connection connection;
    private static Statement statement;

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection("jdbc:h2:~/db");
        statement = connection.createStatement();
    }

    public static void disconnect() throws SQLException {
        statement.close();
        connection.close();
    }

    public static void initDb() throws SQLException {
        dropDB();
        statement.execute("create table task(id int auto_increment, title varchar(255), priority int)");
        addTask("Nourrir le poisson rouge", 1);
        addTask("Aller faire les courses", 2);
        addTask("Reserver un billet de train pour Paris", 3);
    }

    public static void dropDB() throws SQLException {
        statement.execute("drop table task");
    }

    public static Task getById(int id) throws SQLException{
        ResultSet rs;
        Task task = new Task();
        rs = DBClient.statement.executeQuery("select * from task where id = " + id);
        while (rs.next()) {
            task.setTitle(rs.getString("title"));
            task.setPriority(rs.getInt("priority"));
        }
        return task;
    }

    public static List<Task> getAll() throws SQLException{
        ResultSet rs;
        List<Task> tasks = new ArrayList();
        rs = DBClient.statement.executeQuery("select * from task");
        while (rs.next()) {
            Task task = new Task();
            task.setId(rs.getInt("id"));
            task.setTitle(rs.getString("title"));
            task.setPriority(rs.getInt("priority"));
            tasks.add(task);
        }
        return tasks;
    }

    public static boolean addTask(String title, int priority) {
        try {
            String query = "insert into task (title, priority) values('" + title + "', " + priority + ")";
            DBClient.statement.execute(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteTask(String id) {
        try{
            DBClient.statement.execute("delete from task where id = " + id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
