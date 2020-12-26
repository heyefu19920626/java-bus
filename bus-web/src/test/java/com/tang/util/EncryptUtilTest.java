package com.tang.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author he
 * @since 2020-12.26-21:29
 */
public class EncryptUtilTest {
    @Test
    public void test_encrypt() {
        String plaintext = "123456";
        String ciphertext = EncryptUtil.encrypt(plaintext);
        Assert.assertEquals(plaintext, EncryptUtil.decrypt(ciphertext));
    }
}
