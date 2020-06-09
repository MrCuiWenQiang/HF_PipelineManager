package com.zt.map.entity.db.system;

import org.litepal.crud.LitePalSupport;

//权属单位
public class Sys_qsdw extends LitePalSupport {
    private long id;
    private String value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
