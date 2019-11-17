package log;

import java.sql.*;

public class DBManager {
    private static Connection mConnect;
    static {
        try {
            System.out.println("init---");
            Class.forName("com.mysql.cj.jdbc.Driver");
            mConnect=DriverManager.getConnection("jdbc:mysql://47.94.98.103:39001/semdrive", "root", "19970719");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return mConnect;

    }
    public static void  close() {
        try {
            mConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void main (String[] args) {
        DBManager.getConnection();
    }
}
