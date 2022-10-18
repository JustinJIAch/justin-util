package com.justin.common;

/**
 * @Descp 十六进制工具类
 * @Author shiqiang
 * @Date 2022/6/29 10:32
 * @Version 0,。1
 **/
public class HexUtils {
    public static final String HEX_PREFIX = "0x";

    /**
     * 添加0x前缀
     * @param hexStr
     * @return
     */
    public static String addHexPrefix(String hexStr) {
        if(hexStr == null || "".equals(hexStr)) {
            return HEX_PREFIX;
        }
        return hexStr.startsWith(HEX_PREFIX) ? hexStr : HEX_PREFIX + hexStr;
    }

    /**
     * 删除0x前缀
     * @param hexStr
     * @return
     */
    public static String delHexPrefix(String hexStr) {
        if(hexStr == null || "".equals(hexStr)) {
            return "";
        }
        return hexStr.startsWith(HEX_PREFIX) ? hexStr.substring(2) : hexStr;
    }
}
