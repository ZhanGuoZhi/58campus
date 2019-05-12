package javabean;

import redis.clients.jedis.Jedis;

public class RedisConnection {

    private static final String DB_URL = "47.102.140.230";

    private static Jedis jedis = null;

    public RedisConnection() {

    }

    /**
     * 初始化Redis数据库
     */
    private static void createRedisConn() {
        try {
            jedis = new Jedis(DB_URL);
            System.out.println("初始化Redis数据库成功\n");

        } catch (Exception ex) {
            System.out.print("初始化Redis数据库失败：");
            System.out.println(ex.toString() + "\n");
        }
    }

    /**
     * 连接Redis数据库
     *
     * @return Redis连接
     */
    public static Jedis getRedisConn() {
        try {
            createRedisConn();
            System.out.println("连接Redis数据库成功\n");
            return jedis;

        } catch (Exception ex) {
            System.out.print("连接Redis数据库失败：");
            System.out.println(ex.toString() + "\n");
            return null;
        }
    }

    /**
     * 测试Redis数据库
     *
     * @return 字符串
     */
    public static String testRedisConn() {
        try {
            createRedisConn();
            System.out.println("开始测试Redis数据库");
            String jedisString = jedis.ping();
            System.out.println("测试Redis数据库成功\n");
            return jedisString;

        } catch (Exception ex) {
            System.out.print("测试Redis数据库失败");
            System.out.println(ex.toString() + "\n");
            return null;
        }
    }

    /**
     * 关闭Redis数据库
     */
    public static void closeRedisConn() {
        try {
            jedis.close();
            System.out.println("关闭Redis数据库成功\n");

        } catch (Exception ex) {
            System.out.print("关闭Redis数据库失败：");
            System.out.println(ex.toString() + "\n");

        }
    }
}
