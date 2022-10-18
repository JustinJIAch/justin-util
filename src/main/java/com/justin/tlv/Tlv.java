package com.justin.tlv;


/**
 * @author shiqiang
 * @date 2022/9/1 10:35
 */
public class Tlv {

    private String tag;
    private int length;
    private String value;

    public Tlv(String tag, int length, String value) {
        this.tag = tag;
        this.length = length;
        this.value = value;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
