package cn.Hlmove.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

/**
 * @author侯金宝
 */
/**
public class TestJDBC {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //使用JDBC方式，去访问数据库，完成数据维护
        //0、JDBC初始化
        Class.forName("com.mysql.cj.jdbc.Driver");

        //1、建立与数据库服务器的连接对象
        //String dbUrl = "jdbc:mysql://192.168.150.243:3306/shoppingdb?useUnicode=yes&characterEncoding=UTF-8";
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/shoppingdb?useUnicode=yes&characterEncoding=UTF-8";
        Connection connection = DriverManager.getConnection(dbUrl, "root", "2016270225");

        //2、向服务器下达指令（SQL）
        //String sql = "insert into t_news_class(className) values('疫情新闻')";
        //String sql = "update t_news_class set className='新值' where classid=1";
        String sql = "delete from t_news_class where classid=1";
        PreparedStatement statement = connection.prepareStatement(sql);
        int result = statement.executeUpdate();

        //3、回收资源
        connection.close();


    }
}
*/