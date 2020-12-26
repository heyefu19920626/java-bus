package com.tang.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Base64;

/**
 * 加解密工具类
 *
 * @author he
 * @since 2020-12.26-21:24
 */
public class EncryptUtil {
    public static String encrypt(String plaintext) {
        if (StringUtils.isBlank(plaintext)) {
            return plaintext;
        }
        return Base64.getEncoder().encodeToString(plaintext.getBytes());
    }

    public static String decrypt(String ciphertext) {
        if (StringUtils.isBlank(ciphertext)) {
            return ciphertext;
        }
        return new String(Base64.getDecoder().decode(ciphertext));
    }
}
