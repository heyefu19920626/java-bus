package com.heyefu.config;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库初始化
 *
 * @author tangan
 * @since 2020/7/17
 */
@Slf4j
public class DbInit {
    /**
     * sqlLite驱动
     */
    private static final String SQLITE_DRIVER = "org.sqlite.JDBC";

    private static Connection con = null;

    public static boolean init() {
        try {
            Class.forName(SQLITE_DRIVER);
            con = DriverManager.getConnection("jdbc:sqlite:data/util.db");
            return initTable();
        } catch (ClassNotFoundException | SQLException e) {
            log.error("load sqlite driver fail.", e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("close sqlite connect error", e);
            }
        }
    }

    public static boolean initTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user(id INTEGER NOT NULL, name TEXT NOT NULL, password TEXT  NOT NULL, PRIMARY KEY (id))";
        try {
            Statement stat = con.createStatement();
            stat.executeUpdate(sql);
        } catch (SQLException e) {
            log.error("create table error", e);
        }
        return true;
    }
}
