package com.lshuai.tool.db.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author LShuai
 * @date 2019-08-30 13:43
 */
@Data
public class ExcelData {
    @ExcelProperty("表名")
    private String tableName;
    @ExcelProperty("字段描述")
    private String comment;
    @ExcelProperty("字段名")
    private String itemName;
    @ExcelProperty("字段类型")
    private String itemType;
    @ExcelProperty("字段长度")
    private String itemLength;
    @ExcelProperty("是否主键")
    private String pk;
    @ExcelProperty("是否非空")
    private String notNul;

}
