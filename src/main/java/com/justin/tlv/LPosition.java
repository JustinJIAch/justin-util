package com.justin.tlv;

/**
 *
 * @author shiqiang
 * @date 2022/9/1 10:33
 */
public class LPosition {

    private int vl;
    private int position;

    public LPosition(int vl, int position) {
        this.vl = vl;
        this.position = position;
    }

    public int getVl() {
        return vl;
    }

    public void setVl(int vl) {
        this.vl = vl;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
