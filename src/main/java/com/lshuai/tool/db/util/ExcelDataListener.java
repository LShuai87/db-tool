package com.lshuai.tool.db.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.lshuai.tool.db.entity.DbTableEntity;
import com.lshuai.tool.db.entity.ExcelData;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExcelDataListener extends AnalysisEventListener<ExcelData> {
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;
    List<ExcelData> list = new ArrayList<ExcelData>();

    @Override
    public void invoke(ExcelData data, AnalysisContext context) {
        if(data.getItemName()==null||data.getItemName().isEmpty()){
            return;
        }
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        if (data.getTableName() != null && !data.getTableName().isEmpty()
                & !list.isEmpty()) {
            DbTableEntity table = DbTableEntity.transData(list);
            createTable(table);
        }

    }

    private void createTable(DbTableEntity table) {
        StringBuilder sbr = new StringBuilder();
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        log.info("存储数据库成功！");
    }
}
