package com.lshuai.tool.db.util;

import com.lshuai.tool.db.entity.DbTableEntity;
import com.lshuai.tool.db.enumm.DbType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
public class DbUtils {
    private DbType type;
    private DataSource dataSource;
    private QueryRunner run;

    public void createTable(DbTableEntity table) throws SQLException {
        if (dataSource == null) {
            throw new RuntimeException("DataSource is empty");
        }
        String sql = null;
        switch (type) {
            case SQLSERVER:
            case ORACLE:
            case MYSQL:
            case POSTGRESQL:
                sql = transPostgresqlCreateSql(table);
                break;
        }
        createTable(sql);
    }

    private String transPostgresqlCreateSql(DbTableEntity table) {
        StringBuilder sbr = new StringBuilder();
        sbr.append("CREATE TABLE ");
        sbr.append(table.getTableName()).append("(").append("\r\n");
        StringBuilder pks = new StringBuilder();
        table.getItems().forEach(item -> {
            switch (item.getType()) {
                case "integer":
                    if (item.isSerial()) {
                        sbr.append("serial ");
                    } else {
                        sbr.append("integer ");
                    }
                    break;
                case "bigint":
                    if (item.isSerial()) {
                        sbr.append("bigserial ");
                    } else {
                        sbr.append("bigint ");
                    }
                    break;
                case "varchar":
                    sbr.append("varchar(").append(item.getLength()).append(") ");
                    break;
                case "date":
                    sbr.append("date ");
                    break;
                case "timestamp":
                    sbr.append("timestamp ");
                    break;
                default:
                    sbr.append(item.getType()).append(" ");
            }
            if (item.isUnique()) {
                sbr.append("unique ");
            }
            if (item.isNotNul()) {
                sbr.append("not null");
            }
            if (item.isPk()) {
                pks.append(item.getName()).append(",");
            }
            sbr.append(",");

        });
        sbr.append("constraint PK_").append(table.getTableName()).append(" primary key(\"")
                .append(pks.deleteCharAt(pks.length() - 1)).append("\")");
        sbr.append(");");
        return sbr.toString();
    }


    public void createTable(String sql) throws SQLException {
        if (dataSource == null) {
            throw new RuntimeException("DataSource is empty");
        }
        createTable(dataSource, sql);
    }

    public void createTable(DataSource dataSource, String sql) throws SQLException {
        if (dataSource == null) {
            throw new RuntimeException("DataSource is empty");
        }
        if (run == null) {
            run = new QueryRunner(dataSource);
            run.update(sql);
        }else{
            run.update(dataSource.getConnection(),sql);
        }
    }


}
