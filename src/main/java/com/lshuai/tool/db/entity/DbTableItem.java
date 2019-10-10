package com.lshuai.tool.db.entity;

import lombok.Data;

@Data
public class DbTableItem {
    /**
     * 字段名
     */
    private String name;
    /**
     * 注释
     */
    private String comment;
    /**
     * 字段类型
     */
    private String type;
    /**
     * 字段长度
     */
    private String length;
    /**
     * 是否主键
     */
    private boolean pk;
    /**
     * 是否非空
     */
    private boolean notNul;
    /**
     * 是否自增
     */
    private boolean serial;
    /**
     * 是否唯一
     */
    private boolean unique;

    public DbTableItem(ExcelData data) {
        super();

        this.name = data.getItemName();

        this.comment = data.getComment();

        this.type = data.getItemType();

        this.length = data.getItemLength();

        this.pk = "y".equalsIgnoreCase(data.getPk())?true:false;

        this.notNul = "y".equalsIgnoreCase(data.getNotNul())?true:false;
    }
}
