package cn.author.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.author.common.utils.SpringJunitTest;
import cn.author.entity.User;
import cn.author.entity.UserExample;
import cn.author.entity.UserExample.Criteria;
import cn.author.mapper.UserMapper;
import cn.author.service.ExceptionTest;

public class loggerTest extends SpringJunitTest{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ExceptionTest exceptionTest;
	@Test
	public void testException() throws IOException{
		try {
			exceptionTest.throwAExcetion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("这里是日志记录的异常堆栈信息:"+e.getStackTrace().toString());
			StringWriter sw = new StringWriter();   
			sw.close();
	        e.printStackTrace(new PrintWriter(sw, true));   
	        logger.debug("这里是日志记录的异常堆栈信息:"+sw.toString()+":这里是日志记录的异常堆栈信息");
		}
	}
	@Test
	public void testInsert(){
		User user = new User();
		user.setDate(new Date());
//		name长度为50
		user.setName("来个测试不通过看他报不报错;来个测试不通过看他报不报错;来个测试不通过看他报不报错;来个测试不通过看他报不报错;来个测试不通过看他报不报错;"
				+ "来个测试不通过看他报不报错;来个测试不通过看他报不报错;来个测试不通过看他报不报错;来个测试不通过看他报不报错;来个测试不通过看他报不报错;"
				+ "来个测试不通过看他报不报错;来个测试不通过看他报不报错;来个测试不通过看他报不报错;来个测试不通过看他报不报错;来个测试不通过看他报不报错;"
				+ "来个测试不通过看他报不报错;来个测试不通过看他报不报错;来个测试不通过看他报不报错;来个测试不通过看他报不报错;来个测试不通过看他报不报错;来个测试不通过看他报不报错;"
				+ "来个测试不通过看他报不报错;来个测试不通过看他报不报错;来个测试不通过看他报不报错;来个测试不通过看他报不报错;来个测试不通过看他报不报错;"
				+ "来个测试不通过看他报不报错;来个测试不通过看他报不报错;来个测试不通过看他报不报错;来个测试不通过看他报不报错;来个测试不通过看他报不报错;");
		
		try {
			userMapper.insertSelective(user);
		} catch (Exception e) {
			logger.debug("日志测试一下debug");
			logger.info("日志栈信息========================");
			//System.out.println(e.getStackTrace().toString());//打印出来是个对象
//			logger.error("error", e);
//			测试日志打印参数和异常栈信息
			logger.error(JSON.toJSONString(user),e);
			logger.info("日志栈信息==========================");
			logger.error("报错了：",e.getCause());
		}		
	}
}
