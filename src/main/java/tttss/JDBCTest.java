package tttss;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Evan
 */
public class JDBCTest {
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet rs = null;

        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");
//            System.out.println("22");
            // 获取连接
            String url = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8&useSSL=true&serverTimezone=Asia";
            String user = "root";
            String password = "admin";
            connection = DriverManager.getConnection(url, user, password);
            // 获取statement，preparedStatement
            String sql = "select * from student where id=?";
            prepareStatement = connection.prepareStatement(sql);
            // 设置参数
            prepareStatement.setLong(1, 1l);
            // 执行查询
            rs = prepareStatement.executeQuery();
            // 处理结果集
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("sex"));
                System.out.println(rs.getInt("age"));
                System.out.println(rs.getInt("class_id"));
            }
        } finally {
            // 关闭连接，释放资源
            if (rs != null) {
                rs.close();
            }
            if (prepareStatement != null) {
                prepareStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}

