package com.bupt.log.Service;

import com.bupt.log.Bean.MobileLog;
import com.bupt.log.util.CommonUtil;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("mobileLogService")
public class MobileLogService {
    @Resource
    MongoTemplate mongoTemplate;
    @Resource
    CommonUtil commonUtil;
    //查询所有日志
    public List<MobileLog> queryAll(){
        return mongoTemplate.findAll(MobileLog.class);
    }
    //根据uuid查找日志
    public List<MobileLog> queryByUuid(String uuid){
        Query query=new Query(Criteria.where("uuid").is(uuid));
        if (mongoTemplate.findOne(query,MobileLog.class)==null){
            System.out.println("无查询结果");
            return null;
        }
        else
            return mongoTemplate.find(query,MobileLog.class);
    }
    //根据日期范围查找日志
    public List<MobileLog> queryByDate(String start,String end) throws Exception{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date s = sdf.parse(start);
        Date e = sdf.parse(end);
        Query query=new Query(new Criteria().andOperator(Criteria.where("date").gte(s),Criteria.where("date").lte(e)));
        if (mongoTemplate.findOne(query,MobileLog.class)==null){
            System.out.println("无查询结果");
            return null;
        }
        else
            return mongoTemplate.find(query,MobileLog.class);
    }
    //根据ip地址查找日志
    public List<MobileLog> queryByIp(String ip){
        Query query=new Query(Criteria.where("ip").is(ip));
        if (mongoTemplate.findOne(query,MobileLog.class)==null){
            System.out.println("无查询结果");
            return null;
        }
        else
            return mongoTemplate.find(query,MobileLog.class);
    }
    //将指定文件中的所有日志插入数据库中
    public void insertAll(String path){
        List<MobileLog> l=new ArrayList();
        l=commonUtil.ReadMobileLogFromDirectory(path);
        mongoTemplate.insertAll(l);
    }
    //根据uuid删除一条日志
    public void deleteByUuid(String uuid){
        if(queryByUuid(uuid)==null){
            System.out.println("无对应uuid，删除失败");
            return;
        }
        else{
            Query query=new Query(Criteria.where("uuid").is(uuid));
            mongoTemplate.remove(query,MobileLog.class);
        }
    }
}
