package com.heyefu.config;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            return initTable(getDbAndSql());
        } catch (ClassNotFoundException e) {
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

    public static boolean initTable(Map<String, List<String>> dbMap) {
        dbMap.forEach((id, sqls) -> {
            try {
                con = DriverManager.getConnection("jdbc:sqlite:data/" + id);
                Statement stat = con.createStatement();
                sqls.forEach(sql -> {
                    try {
                        stat.executeUpdate(sql);
                    } catch (SQLException e) {
                        log.error("create table error.", e);
                    }
                });
            } catch (SQLException e) {
                log.error("create db file error.", e);
            }
        });
        return true;
    }

    private static Map<String, List<String>> getDbAndSql() {
        Map<String, List<String>> map = new HashMap<>();
        List<String> utils = new ArrayList<>();
        String sql = "CREATE TABLE IF NOT EXISTS user(id INTEGER NOT NULL, name TEXT NOT NULL, password TEXT  NOT NULL, PRIMARY KEY (id))";
        utils.add(sql);
        map.put("util.db", utils);
        List<String> common = new ArrayList<>();
        sql = "CREATE TABLE IF NOT EXISTS common(id INTEGER NOT NULL, name TEXT NOT NULL, password TEXT  NOT NULL, PRIMARY KEY (id))";
        common.add(sql);
        map.put("common.db", common);
        return map;
    }
}