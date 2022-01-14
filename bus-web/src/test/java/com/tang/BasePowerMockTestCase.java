package com.tang;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.testng.PowerMockTestCase;

/**
 * @author he
 * @since 2022-01.13-00:15
 */
@RunWith(PowerMockRunner.class) // 告诉JUnit使用PowerMockRunner进行测试
@PowerMockIgnore("javax.management.*") //为了解决使用powermock后，提示classloader错误
// Springboot需要时可以添加下面两个注解
// @PowerMockRunnerDelegate(SpringRunner.class)
// @SpringBootTest
public class BasePowerMockTestCase extends PowerMockTestCase {
}
