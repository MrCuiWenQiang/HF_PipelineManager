package com.zt.map.entity.db.system;

import org.litepal.crud.LitePalSupport;

/**
 * 获取时机
 */
public class Sys_HQSJ extends LitePalSupport {
    private long id;
    private String fatherCode;//父代号
    private String name;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFatherCode() {
        return fatherCode;
    }

    public void setFatherCode(String fatherCode) {
        this.fatherCode = fatherCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
