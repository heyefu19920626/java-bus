/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2022-2022. All rights reserved.
 */

package com.tang.shop;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * @author h00620506
 * @version [SmartKit, 2022/1/30]
 * @since 2022/1/30
 */
@RunWith(PowerMockRunner.class) // 告诉JUnit使用PowerMockRunner进行测试
@PowerMockIgnore("javax.management.*") //为了解决使用powermock后，提示classloader错误
public class ShopUsePowerMockTest {
    @InjectMocks
    private Shop shop;

    @Test
    // 静态方法的模拟要使用原类
    @PrepareForTest(ItemDao.class)
    public void test_use_power_mock_static() {
        PowerMockito.mockStatic(ItemDao.class);
        // 只注释下面这一句不管用,参数不影响，该方法全部返回空
        PowerMockito.when(ItemDao.getItemById("0")).thenReturn(Optional.empty());
        Shop shop = new Shop();
        List<String> items = shop.getItems("1");
        Assert.assertEquals(items.size(), 0);
    }

    @Test
    // new 方法的模拟要使用调用类
    @PrepareForTest({Shop.class})
    public void test_mock_new() throws Exception {
        File file = Mockito.mock(File.class);
        PowerMockito.whenNew(File.class).withAnyArguments().thenReturn(file);
        PowerMockito.when(file.exists()).thenReturn(true);
        Assertions.assertTrue(shop.isExit("abcde"));
        Assertions.assertTrue(new Shop().isExit("abcde"));
    }
}