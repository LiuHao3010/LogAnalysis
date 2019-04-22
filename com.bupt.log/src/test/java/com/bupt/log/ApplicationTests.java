package com.bupt.log;

		import com.bupt.log.Bean.MobileLog;
		import com.bupt.log.Bean.people;
		import com.bupt.log.Service.LogService;
		import com.bupt.log.Service.MobileLogService;
		import com.mongodb.BasicDBObject;
		import org.junit.Test;
		import org.junit.runner.RunWith;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.boot.test.context.SpringBootTest;
		import org.springframework.data.mongodb.core.MongoTemplate;
		import org.springframework.test.context.junit4.SpringRunner;

		import javax.annotation.Resource;
		import java.text.SimpleDateFormat;
		import java.util.Date;
		import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	@Resource
	MongoTemplate mongoTemplate;
	@Autowired
	public MobileLogService mobilelogService;
	@Autowired
	public LogService logService;
	@Test
	public void contextLoads() throws Exception {
	logService.insertAll("C:\\Users\\LH\\Desktop\\Other\\201812");
	}

}
