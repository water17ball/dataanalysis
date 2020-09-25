package cn.wp.bigdata.dataanalysis.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class JdbcUtil {
    //数据库用户名
    private String username;
    //数据库密码
    private String password;
    //驱动信息
    // private String driver;
    //数据库地址
    private String url;

    // 加载驱动
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            log.error("failed to load database driver.",e);
        }
    }

    public JdbcUtil(String username, String password, String url) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    public Connection getConnection() {
        try {
            // 获取连接
            return DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            log.error("get db connection failed.",e);
        }
        return null;
    }

    /**
     * 释放资源
     *
     * 查询的释放资源方法
     * @param conn
     * @param sta
     * @param rs
     */
    public void release(Connection conn,Statement sta,ResultSet rs){
        if (rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("resource release error.",e);
            }
            rs = null;
        }
        if (sta!=null) {
            try {
                sta.close();
            } catch (SQLException e) {
                log.error("resource release error.",e);
            }
            sta = null;
        }
        if (conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("resource release error.",e);
            }
            conn = null;
        }
    }

}
