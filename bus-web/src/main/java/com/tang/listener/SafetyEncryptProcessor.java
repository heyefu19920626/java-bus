package com.tang.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;

/**
 * 自定义environment
 * <p>
 * https://docs.spring.io/spring-boot/docs/2.3.2.RELEASE/reference/htmlsingle/#howto-customize-the-environment-or-application-context
 * <p>
 * 一个SpringApplication 拥有用于对上下文或环境应用自定义的ApplicationListeners 和ApplicationContextInitializers。
 * Spring boot引导会从META-INF/ Spring .factories中加载大量这样的定制，以供内部使用。
 * 这里有多种注册其他自定义的方法：
 * <p>
 * 1. 编程的方式，通过在SpringApplication 启动之前调用addListeners 和addInitializers方法进行注册。
 * <p>
 * 2. 声明式，通过设置context.initializer.classes or context.listener.classes的配置。
 * <p>
 * 3. 声明式，通过添加一个META-INF/spring.factories文件 ，打包成jar让应用程序当作库调用。
 * SpringApplication 发送一个事件ApplicationEvents去监听(在上下文创建之前)，然后为ApplicationContext发布的事件注册侦听器。
 * 还可以通过EnvironmentPostProcessor在刷新应用程序上下文之前定制Environment 。
 * 每个实现都应该注册到文件META-INF/spring.factories中
 *
 * @author tang
 * @since 2020-11.15-15:01
 */
public class SafetyEncryptProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        HashMap<String, Object> map = new HashMap<>();
        for (PropertySource<?> ps : environment.getPropertySources()) {
            if (ps instanceof OriginTrackedMapPropertySource) {
                OriginTrackedMapPropertySource source = (OriginTrackedMapPropertySource) ps;
                for (String name : source.getPropertyNames()) {
                    Object value = source.getProperty(name);
                    if (value instanceof String) {
                        String str = (String) value;
                        //需要处理的value的前缀
                        if (str.startsWith("encrypt:")) {
                            map.put(name, decrypt(str));
                        }
                    }
                }
            }
        }
        // 将处理的数据放入环境变量，并处于第一优先级上 (这里一定要注意，覆盖其他配置)
        if (!map.isEmpty()) {
            environment.getPropertySources().addFirst(new MapPropertySource("prefixer", map));
        }
    }

    private String decrypt(String ciphertext) {
        return ciphertext.substring(ciphertext.indexOf(":") + 1);
    }
}