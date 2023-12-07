package fit.iseeyou.common.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.SM4;
import icu.xuyijie.sm4utils.util.SM4Utils;

/**
 * 对称加密sm4加解密工具类
 */
public class Sm4Utils {
    /**
     * 和前端key一致
     */
    private static final String SECRET_KEY = "lN0dZ8uG4aY6iF4b";

    /**
     * 和前端iv一致
     */
    private static final String IV = "dA0jU4bN1sE5dB2v";

    private static final SM4 SM_4 = new SM4(Mode.CBC, Padding.PKCS5Padding,
            SECRET_KEY.getBytes(CharsetUtil.CHARSET_UTF_8),
            IV.getBytes(CharsetUtil.CHARSET_UTF_8));

    public static String encrypt(String content) {
        return SM4Utils.encryptData_CBC(content, SECRET_KEY, IV);
    }

    public static String decrypt(String ciphertext) {
        return SM4Utils.decryptData_CBC(ciphertext, SECRET_KEY, IV);
    }
}
