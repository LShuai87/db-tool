package com.lshuai.tool.db.util;

import ch.qos.logback.classic.util.ContextInitializer;
import com.alibaba.excel.EasyExcel;
import com.lshuai.tool.db.entity.ExcelData;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ExcelUtil {
    public static void main(String[] args) {
        System.setProperty(ContextInitializer.AUTOCONFIG_FILE,"E:\\idea-workspace\\tool\\logback.xml");
        String fileName = "E:\\表设计.xlsx";
        new ExcelUtil().read(fileName);
    }
    public void read(String excelFile){
        EasyExcel.read(excelFile, ExcelData.class, new ExcelDataListener()).sheet().doRead();
    }

}
