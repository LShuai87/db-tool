package com.lshuai.tool.db.test;

import com.alibaba.fastjson.JSON;
import com.lshuai.tool.db.entity.DbParam;
import com.lshuai.tool.db.enumm.DbType;
import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LShuai
 * @date 2019-09-02 16:59
 */
public class Test {
    public static void main(String[] args) {
        DbParam param = new DbParam();
        param.setName("local");
        param.setType(DbType.ORACLE);
        param.setDriver("oracle.jdbc.OracleDriver");
        param.setUrl("jdbc:oracle:thin:@//127.0.0.1:1521/helowinXDB");
        param.setUsername("ies_cmr");
        param.setPassword("ieslab");
        DbParam param1 = new DbParam();
        param1.setName("localPG");
        param1.setType(DbType.POSTGRESQL);
        param1.setDriver("org.postgresql.Driver");
        param1.setUrl("jdbc:postgresql://127.0.0.1:5432/test");
        param1.setUsername("postgres");
        param1.setPassword("postgres");
        List<DbParam> list = new ArrayList<>();
        list.add(param);
        list.add(param1);
        try (OutputStream os = new FileOutputStream("E:\\idea-workspace\\tool\\src\\main\\resources\\dbConfig.json")){
            IOUtils.write(JSON.toJSONString(list),os,"utf-8");

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
