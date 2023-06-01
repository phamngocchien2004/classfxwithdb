package database;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;

public class Connector {
    private final static String connection= "jdbc:mysql://localhost:3306/classrooms";
    private final static String user = "root";
    private final static String pwd = "";
    private Connection conn;
    public  Connector () throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        this.conn= (Connection) DriverManager.getConnection(connection,user,pwd);

    }

    public Connection getConn() {
        return conn;
    }
}
