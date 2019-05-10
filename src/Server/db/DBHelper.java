package Server.db;

//
import java.sql.*;


public class DBHelper {
    public static final String url = "jdbc:mysql://localhost:3306/icwy?useUnicode=true&characterEncoding=utf-8";
    private static final  String driver = "com.mysql.jdbc.Driver";
    public static final  String userName = "root";
    public static final String password = "123456";
   // private Connection connection;
    //private Statement statement;
    //private PreparedStatement preparedStatement;
    /**
     *链接数据库
     */
    public static Connection getConnection()throws SQLException
    {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url,userName,password);
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new SQLException();
        }
    }

}
