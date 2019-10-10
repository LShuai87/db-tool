package com.lshuai.tool.db.entity;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class DbTableEntity {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表名中文
     */
    private String tableNameCn;
    /**
     * 字段
     */
    private List<DbTableItem> items;

    public static DbTableEntity transData(List<ExcelData> list) {
        if (list == null || list.isEmpty() || list.get(0).getTableName() == null
                || list.get(0).getTableName().isEmpty()) {
            return null;
        }
        DbTableEntity table = new DbTableEntity();
        table.setTableName(list.get(0).getTableName());
        List<DbTableItem> items = list.stream().map(data -> new DbTableItem(data))
                .collect(Collectors.toList());
        table.setItems(items);
        return table;
    }
}
