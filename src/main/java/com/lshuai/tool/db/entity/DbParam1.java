package com.lshuai.tool.db.entity;

import com.lshuai.tool.db.enumm.DbType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Data;

/**
 * 数据库连接信息
 */
@Data
public class DbParam1 {
    private final  StringProperty  name;
    private final  StringProperty  type;
    private final  StringProperty  driver;
    private final  StringProperty  url;
    private final  StringProperty  username;
    private final  StringProperty  password;

   public DbParam1(DbParam param){
        this.name = new SimpleStringProperty(param.getName());
        this.type = new SimpleStringProperty(param.getType().toString());
        this.driver = new SimpleStringProperty(param.getDriver());
        this.url = new SimpleStringProperty(param.getUrl());
        this.username = new SimpleStringProperty(param.getUsername());
        this.password = new SimpleStringProperty(param.getPassword());

    }

}
