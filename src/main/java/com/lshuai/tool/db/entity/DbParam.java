package com.lshuai.tool.db.entity;

import com.lshuai.tool.db.enumm.DbType;
import lombok.Data;

/**
 * 数据库连接信息
 */
@Data
public class DbParam {
    private String name;
    private DbType type;
    private String driver;
    private String url;
    private String username;
    private String password;

}
