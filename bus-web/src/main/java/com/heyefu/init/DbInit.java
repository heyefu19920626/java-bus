package com.heyefu.init;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author tang
 * @since 2020/7/18
 */
@Order(1)
@Component
@Slf4j
public class DbInit implements CommandLineRunner {
    @Value("${bus.db.basic}")
    private List<String> dbList;

    @Resource
    private Environment environment;

    @Override
    public void run(String... args) throws Exception {
        if (init()) {
            log.info("init db success.");
        } else {
            log.error("init db error.");
            System.exit(0);
        }
    }

    /**
     * sqlLite驱动
     */
    private static final String SQLITE_DRIVER = "org.sqlite.JDBC";

    /**
     * 初始化数据库
     *
     * @return 初始化结果
     */
    public boolean init() {
        try {
            FileUtils.forceMkdir(new File("data/database"));
            Class.forName(SQLITE_DRIVER);
            return initTable(getDbAndSql());
        } catch (ClassNotFoundException | IOException e) {
            log.error("create db dir or load sqlite driver fail.", e);
            return false;
        }
    }

    public boolean initTable(Map<String, List<String>> dbMap) {
        dbMap.forEach((id, sqlList) -> {
            try (Connection con = DriverManager.getConnection("jdbc:sqlite:data/database/" + id)) {
                sqlList.forEach(sql -> {
                    try (PreparedStatement statement = con.prepareStatement(sql)) {
                        statement.execute();
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

    private Map<String, List<String>> getDbAndSql() {
        Map<String, List<String>> map = new HashMap<>(dbList.size());
        dbList.forEach(dbId -> {
            String[] tableFiles = Objects.requireNonNull(environment.getProperty("bus.db." + dbId)).split(",");
            List<String> tableList = new ArrayList<>(tableFiles.length);
            for (String tableFile : tableFiles) {
                try (InputStream inputStream = this.getClass().getResourceAsStream("/sql/" + tableFile);
                     BufferedReader bufferedReader = new BufferedReader(
                         new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                ) {
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result.append(line).append(" ");
                    }
                    tableList.add(result.toString());
                } catch (IOException e) {
                    log.error("read sql file error.", e);
                }
            }
            map.put(dbId + ".db", tableList);
        });
        return map;
    }
}