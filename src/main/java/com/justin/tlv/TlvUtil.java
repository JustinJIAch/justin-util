package com.justin.tlv;

import com.justin.common.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TLV数据工具类
 * @author shiqiang
 * @date 2022/9/1 10:36
 */
public class TlvUtil {

    /**
     * 将16进制字符串转换为TLV对象MAP
     * @param hexString
     * @return
     */
    public static Map<String, Tlv> builderTlvMap(String hexString) {

        Map<String, Tlv> tlvs = new HashMap<String, Tlv>();
        int position = 0;
        while (position != hexString.length()) {
            String _hexTag = getTag(hexString, position);
            position += _hexTag.length();
            LPosition l_position = getLengthAndPosition(hexString, position);
            int _vl = l_position.getVl();
            position = l_position.getPosition();
            String _value = hexString.substring(position, position + _vl * 2);
            position = position + _value.length();

            tlvs.put(_hexTag, new Tlv(_hexTag, _vl, _value));
        }
        return tlvs;
    }
    /**
     * 16进制tlv数据转换为对象
     * @param hex
     * @return
     */
    public static List<Tlv> buildTlvList(String hex) {
        List<Tlv> tlvs = new ArrayList<>();
        if(StringUtil.isNotEmpty(hex)) {
            int position = 0;
            while (position != hex.length()) {
                String hexTag = getTag(hex, position);
                position += hexTag.length();
                LPosition lPosition = getLengthAndPosition(hex, position);
                int vl = lPosition.getVl();
                position = lPosition.getPosition();
                String value = hex.substring(position, position + vl * 2);
                position += value.length();
                System.out.println("hexTag[" +hexTag+ "] vl[" +vl+ "] value[" +value+ "]");
                tlvs.add(new Tlv(hexTag, vl, value));
            }
        }
        return tlvs;
    }

    /**
     * 取得子域标签
     * @param hex
     * @param position
     * @return
     */
    private static String getTag(String hex, int position) {
        String firstByte = hex.substring(position, position + 2);
        int i = Integer.parseInt(firstByte, 16);
        if((i & 0x1f) == 0x1f) {
            return hex.substring(position, position + 4);
        }
        return hex.substring(position, position + 2);
    }

    /**
     * 返回最后的value长度
     * @param hex
     * @param position
     * @return
     */
    private static LPosition getLengthAndPosition(String hex, int position) {
        String hexLength = hex.substring(position, position + 4);
        position += 4;
        return new LPosition(Integer.parseInt(hexLength, 16), position);
    }

}
