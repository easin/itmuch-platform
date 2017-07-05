package com.itmuch.core.util;

import java.security.SecureRandom;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.Validate;

public class SaltUtil {
    public static byte[] genSaltBytes(int numBytes) {
        Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return bytes;
    }

    public static String genSaltString(byte[] saltBytes) {
        return Hex.encodeHexString(saltBytes);
    }

    public static String genSaltString(int numBytes) {
        return genSaltString(genSaltBytes(numBytes));
    }
}
