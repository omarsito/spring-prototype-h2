package com.piolin.spring.prototype.pojo;

import com.piolin.spring.prototype.util.Util;

public class Root {

    private String msg;
    private String version;

    private Root(){}

    public Root(String msg, String version){
        this.msg = msg;
        this.version = version;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString(){
        return new Util().convertObject2Json(this);
    }

}