package com.tj.myvolley.vo;

import java.util.ArrayList;

/**
 * Created by Lee on 2015/12/14.
 */
public class ListView_info {

    private int code;
    private ArrayList<ListView_info_userinfo> userinfo;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<ListView_info_userinfo> getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(ArrayList<ListView_info_userinfo> userinfo) {
        this.userinfo = userinfo;
    }
}
