package com.tang.login;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * 测试命令行
 *
 * @author tang
 * @since 2020-08.19-23:17
 */
public class CommandTest {
    @Test
    public void test() {
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("user.home"));
    }

    @Test
    public void command() throws IOException {
        // 实际的命令是/bin/bash -c "echo $PATH"
        // String commands[] = {"/bin/bash", "-c", "echo $PATH"};
        // 直接使用不能获取正确值，因为java会进行分割
        String commands = "/bin/bash -c echo $PATH";
        Process process;
        process = Runtime.getRuntime().exec(commands);
        try (InputStream correct = process.getInputStream();
             InputStream error = process.getErrorStream()) {
            byte[] temp = new byte[1024];
            StringBuilder result = new StringBuilder();
            int len;
            while ((len = correct.read(temp)) != -1) {
                result.append(new String(temp, 0, len, StandardCharsets.UTF_8));
            }
            System.out.println("correct: " + result);
            System.out.println("=====================");
            result = new StringBuilder();
            while ((len = error.read(temp)) != -1) {
                result.append(new String(temp, 0, len));
            }
            System.out.println("error: " + result);
            System.out.println("-----------------");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            process.destroy();
        }
    }

    private String writeInScript(String... commands) throws IOException {
        if (commands == null) {
            return null;
        }
        String line = System.getProperty("line.separator");
        StringBuilder script = new StringBuilder("#!/bin/bash");
        for (String command : commands) {
            script.append(line).append(command);
        }
        String tempFile =
                System.getProperty("user.dir") + File.separator + "tmp" + File.separator + System.currentTimeMillis() + "_tmp.sh";
        FileUtils.write(new File(tempFile), script.toString(), StandardCharsets.UTF_8);
        return tempFile;
    }
}