package Client.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DatabaseModel {
    private String url = "jdbc:mysql://localhost:3306/icwy?useUnicode=true&characterEncoding=utf-8";
    private final static String driver = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private String password = "123456";
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    public DatabaseModel()
    {

    }
    /**
     *链接数据库
     */
    public void connet()
    {
        try
        {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url,userName,password);
        }
        catch (Exception e)
        {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }

    }
    /**
     *
     */




}
