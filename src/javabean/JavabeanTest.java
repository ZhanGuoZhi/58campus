package javabean;


import java.sql.*;
import java.io.Serializable;

import redis.clients.jedis.Jedis;

public class JavabeanTest implements Serializable {

    // 每个JavaBean文件声明以下四个实例变量
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private Jedis jedis = null;

    // 通过无参构造函数获取数据库连接
    public JavabeanTest() {
        try {
            connection = MysqlConnection.getMysqlConn();
            jedis = RedisConnection.getRedisConn();
            System.out.println("------------------------------");
            System.out.println("初始化Javabean测试成功\n");

        } catch (Exception ex) {
            System.out.println("初始化Javabean测试失败：" + ex.toString() + "\n");
        }
    }

    /**
     * 内部测试
     */
    public void InternalTest() {
        try {
            System.out.println("开始内部测试\n");

            // 调用数据库连接类内部的测试方法
            String mysqlResult = MysqlConnection.testMysqlConn();
            String redisResult = RedisConnection.testRedisConn();
            System.out.println("内部测试成功：" + mysqlResult + " | " + redisResult + "\n");

        } catch (Exception ex) {
            System.out.println("内部测试失败：" + ex.toString() + "\n");
        }
    }

    /**
     * 外部测试
     */
    public void ExternalTest() {
        try {
            System.out.println("开始外部测试");

            // 自己编写的测试方法作为外部测试
            StringBuilder stringBuilder = new StringBuilder();
            String sql = "select name from mytest.test";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getString("name"));
            }
            String stringJedis = jedis.ping();
            System.out.println("外部测试成功：" +
                    stringBuilder.toString() + " | " + stringJedis + "\n");
        } catch (Exception ex) {
            System.out.println("外部测试失败：" + ex.toString() + "\n");
        }
    }

    /**
     * 清理资源
     * 在JSP最后必须调用这个方法清理资源
     */
    public void Cleanup() {
        try {
            // 在JSP使用JavaBean后必须调用这个方法清理资源
            resultSet.close();
            statement.close();
            connection.close();
            jedis.close();
            System.out.println("清理资源成功");
            System.out.println("------------------------------");
        } catch (Exception ex) {
            System.out.println("清理资源失败：" + ex.toString() + "\n");
        }
    }
}
