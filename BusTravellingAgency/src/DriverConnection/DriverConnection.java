package DriverConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverConnection {
    //Driver load...
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
    //Creating conncetion for all files...
    public static Connection getConnection(){
        Connection connection=null;
        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/bustravellingagency","root","Spider-man");
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return connection;
    }
}
