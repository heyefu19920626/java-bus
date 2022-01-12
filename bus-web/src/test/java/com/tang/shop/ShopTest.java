package com.tang.shop;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;
import java.util.Optional;

/**
 * @author he
 * @since 2022-01.12-23:01
 */
@RunWith(PowerMockRunner.class) // 告诉JUnit使用PowerMockRunner进行测试
@PrepareForTest({ItemDao.class}) // 所有需要测试的类列在此处，适用于模拟final类或有final, private, static, native方法的类
@PowerMockIgnore("javax.management.*") //为了解决使用powermock后，提示classloader错误
public class ShopTest {
    @InjectMocks
    private Shop shop;

    @Test
    public void test_use_power_mock_static() {
        System.out.println(shop);
        PowerMockito.mockStatic(ItemDao.class);
        // 只注释下面这一句不管用,参数不影响，该方法全部返回空
        PowerMockito.when(ItemDao.getItemById("0")).thenReturn(Optional.empty());
        Shop shop = new Shop();
        List<String> items = shop.getItems("1");
        Assert.assertEquals(items.size(), 0);
    }


    public static class Mock {

        // @MockInvoke(targetClass = ItemDao.class)
        // public static Optional<String> getItemById(String id) {
        //     return Optional.empty();
        // }
    }

    @Test
    public void test_use_test_mock() {
        Shop shop = new Shop();
        final List<String> items = shop.getItems("1");
        Assert.assertEquals(items.size(), 0);
    }
}
