package com.carmold.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Implementation of PasswordEncoder using message digest. Can accept any
 * message digest that the JDK can accept, including MD5 and SHA1. Returns the
 * equivalent Hash you would get from a Perl digest.
 *
 * @author Scott Battaglia
 * @author Stephen More
 * @version $Revision: 19533 $ $Date: 2009-12-14 23:33:36 -0500 (Mon, 14 Dec 2009) $
 * @since 3.1
 */
public final class MD5 {

    final static Log log = LogFactory.getLog(MD5.class);

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private final static String HEX = "0123456789ABCDEF";

    private final static String encodingAlgorithm = "MD5";

    public static String getMD5(String str) {
        // 生成一个MD5加密计算摘要
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 计算md5函数
        md.update(str.getBytes());
        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
        return new BigInteger(1, md.digest()).toString(16);
    }

    private static void appendHex(StringBuffer sb, byte b) {
        if (sb != null) {
            sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
        }
    }

    public static String encode(final String text) {
        if (text == null) {
            return null;
        }

        try {
            MessageDigest messageDigest = MessageDigest
                    .getInstance(MD5.encodingAlgorithm);
            messageDigest.update(text.getBytes());
            final byte[] digest = messageDigest.digest();
            return getFormattedText(digest);
        } catch (final NoSuchAlgorithmException e) {
            throw new SecurityException(e);
        }
    }

    public static String encrypt(File file) {
        String hash = null;

        if (file.exists()) {
            try {
                hash = encrypt(new FileInputStream(file));
            } catch (Exception e) {
                log.error(e);
            }
        }

        return hash;
    }

    public static String encrypt(InputStream stream) {
        String hash = null;

        if (stream != null) {
            byte[] buffer = new byte[2048];
            BufferedInputStream in = null;
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                in = new BufferedInputStream(stream);
                int numRead = 0;
                while ((numRead = in.read(buffer)) > 0) {
                    md5.update(buffer, 0, numRead);
                }
                hash = toHex(md5.digest());
            } catch (Exception e) {
                log.error(e);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (Exception e) {
                        log.error(e);
                    }
                }
            }
        }

        return hash;
    }

    /**
     * Takes the raw bytes from the digest and formats them correct.
     *
     * @param bytes the raw bytes from the digest.
     * @return the formatted bytes.
     */
    private static String getFormattedText(byte[] bytes) {
        final StringBuilder buf = new StringBuilder(bytes.length * 2);

        for (int j = 0; j < bytes.length; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    public static String toHex(byte[] buf) {
        String strHex = null;
        if (buf != null) {
            StringBuffer result = new StringBuffer(2 * buf.length);
            for (int i = 0; i < buf.length; i++) {
                appendHex(result, buf[i]);
            }
            strHex = result.toString();
        }
        return strHex;
    }
}
