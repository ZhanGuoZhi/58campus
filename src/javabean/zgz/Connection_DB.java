package javabean.zgz;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.*;
import java.util.Map;

public class Connection_DB implements Serializable {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://47.102.140.230:3306/?" +
            "user=58campus&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
    private static final String _DB_URL = "jdbc:mysql://dark-hole.picp.net:13306/?" +
            "user=58campus&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";

    private static final String USER = "58campus";
    private static final String PSWD = "58campus";

    public PreparedStatement pstmt = null;
    public Connection conn = null;
    public ResultSet rs = null;

    public Connection_DB() {
    }


    /*连接数据库*/
    public Connection getCoon() {

        try {// 抛出异常
            Class.forName(DRIVER);// 驱动数据库
            conn = DriverManager.getConnection(_DB_URL, USER, PSWD);
            System.out.println("连接成功。");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
        return conn;
    }




    /*查询方法*/
    public List getList(String sql, String[] params) {
        List list = new ArrayList();
        try {
            pstmt = getCoon().prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setString(i + 1, params[i]);
            }
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                Map m = new HashMap();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String colname = rsmd.getColumnName(i);
                    m.put(colname, rs.getString(colname));
                }
                System.out.println("查找成功");
                list.add(m);
            }
        } catch (SQLException e) {
            System.out.println("查找失败");
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    // 更新数据库调用的update方法
    public int update(String sql, String[] params) {
        int recno = 0; // 更新影响的行数
        try {
            pstmt = getCoon().prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setString(i + 1, params[i]);
            }
            recno = pstmt.executeUpdate();
            System.out.println("更新数据库操作成功。");
        } catch (Exception e) {
            System.out.println("更新数据库失败。" + e.toString());
        } finally {
            close();
        }
        return recno;
    }

    /*关闭数据库 */
    public void close() {
        try {
            if (rs != null)
                rs.close();
            if (conn != null)
                conn.close();
            if (pstmt != null)
                pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

}

