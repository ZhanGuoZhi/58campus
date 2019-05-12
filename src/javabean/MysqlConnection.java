package javabean;

import java.sql.*;

public class MysqlConnection {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String DB_URL = "jdbc:mysql://47.102.140.230:3306/?" +
            "user=58campus&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";

    private static final String _DB_URL = "jdbc:mysql://dark-hole.picp.net:13306/?" +
            "user=58campus&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";

    private static final String USER = "58campus";
    private static final String PSWD = "58campus";

    private static Connection connection = null;

    public MysqlConnection() {

    }

    /**
     * 初始化MySQL数据库
     */
    private static void createMysqlConn() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(_DB_URL, USER, PSWD);
            System.out.println("初始化MySQL数据库成功\n");

        } catch (Exception ex) {
            System.out.print("初始化MySQL数据库失败：");
            System.out.println(ex.toString() + "\n");
        }
    }

    /**
     * 连接MySQL数据库
     *
     * @return MySQL连接
     */
    public static Connection getMysqlConn() {
        try {
            createMysqlConn();
            System.out.println("连接MySQL数据库成功\n");
            return connection;

        } catch (Exception ex) {
            System.out.print("连接MySQL数据库失败：");
            System.out.println(ex.toString() + "\n");
            return null;
        }
    }

    /**
     * 测试MySQL数据库
     *
     * @return 字符串
     */
    public static String testMysqlConn() {
        try {
            createMysqlConn();
            System.out.println("开始测试MySQL数据库");
            String sql = "select name from mytest.test";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            StringBuilder stringBuilder = new StringBuilder();
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getString("name"));
            }
            resultSet.close();
            statement.close();
            connection.close();
            System.out.println("测试MySQL数据库成功\n");
            return stringBuilder.toString();

        } catch (Exception ex) {
            System.out.print("测试MySQL数据库失败：");
            System.out.println(ex.toString() + "\n");
            return null;
        }
    }

    /**
     * 关闭MySQL数据库
     */
    public static void closeMysqlConn() {
        try {
            connection.close();
            System.out.println("关闭MySQL数据库成功\n");

        } catch (Exception ex) {
            System.out.print("关闭MySQL数据库失败：");
            System.out.println(ex.toString() + "\n");
        }
    }
}
