package com.tang.shop;

import com.alibaba.testable.core.annotation.MockInvoke;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Optional;

/**
 * @author he
 * @since 2022-01.12-23:01
 */
public class ShopTest {
    public static class Mock {

        private static String s;

        static {
            s = "my2";
        }

        @MockInvoke(targetClass = ItemDao.class)
        public static Optional<String> getItemById(String id) {
            return Optional.ofNullable(s);
        }
    }

    @Test
    public void test_testable() {
        List<String> tt = new Shop().getItems("1");
        Assertions.assertEquals(1, tt.size());
        Assertions.assertEquals("my", tt.get(0));
    }

    @Test
    public void test_use_test_mock() throws InterruptedException {
        Shop shop = new Shop();
        final List<String> items = shop.getItems("1");
        Assert.assertEquals(items.size(), 1);
    }
}