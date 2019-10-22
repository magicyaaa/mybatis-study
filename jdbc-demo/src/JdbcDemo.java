
import java.sql.*;

/**
 * @program: mybatis-study
 * @description:
 * @author: Ya
 * @create: 
 **/
public class JdbcDemo {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            //加载数据驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 通过驱动管理类获取数据库链接connection = DriverManager
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8", "root", "root");

            // 定义sql语句 ?表示占位符
            String sql = "select * from user where username = ?";

            // 获取预处理 statement
            preparedStatement = connection.prepareStatement(sql);

            // 设置参数，第一个参数为 sql 语句中参数的序号(从 1 开始)，第二个参数为设置的
            preparedStatement.setString(1, "王五");

            // 向数据库发出 sql 执行查询，查询出结果集
            rs = preparedStatement.executeQuery();

            // 遍历查询结果集
            while (rs.next()) {
                System.out.println(rs.getString("id") + " " + rs.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block e.printStackTrace();
                }
            }
        }
    }

}
