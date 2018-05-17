package cn.author.mockmvc;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.author.common.utils.SpringJunitTest;
import cn.author.entity.User;
import cn.author.entity.UserExample;
import cn.author.entity.UserExample.Criteria;
import cn.author.mapper.UserMapper;
import cn.author.service.ExceptionTest;
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class MockTestStandalone{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@InjectMocks
	
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ExceptionTest exceptionTest;
	MockMvc mvc;
	MockHttpSession session;
	@Before
	public void before() {
	//可以对所有的controller来进行测试
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	//仅仅对单个Controller来进行测试
	// mockMvc = MockMvcBuilders.standaloneSetup(new MeunController()).build();
	}
	@Test
	public void testPerform() throws Exception{
		String uri = "/user/singleUser.do";
		MvcResult mvcResult=null;
		try {
			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(getTestJsonData()))
			        .andReturn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

        String content = mvcResult.getResponse().getContentAsString();
        int state =  mvcResult.getResponse().getStatus();
        System.out.println(state+" and result:"+content);
        //将Json转为对象
       // CcaiServiceResult result = objectMapper.readValue(content, CcaiServiceResult.class);
//        User parseObject = JSON.parseObject(content, User.class);
//        System.out.println(result);
	}
	public String getTestJsonData(){
		User user = new User();
		user.setDate(new Date());
		user.setName("Xu");
		return JSON.toJSONString(user);		
	}
	@Test
	public void test1(){
		UserExample userExample = new UserExample();
		Criteria userCriteria = userExample.createCriteria();
		List<User> userList = userMapper.selectByExample(userExample);
		for (User user : userList) {
			System.out.println("this is git update test"+JSON.toJSONString(user));
		}
	}
	@Test
	public void testException() throws IOException{
		try {
			exceptionTest.throwAExcetion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//logger.debug("这里是日志记录的异常堆栈信息:"+e.getStackTrace().toString());
			StringWriter sw = new StringWriter();   
			sw.close();
	        e.printStackTrace(new PrintWriter(sw, true));   
	        logger.debug("这里是日志记录的异常堆栈信息:"+sw.toString()+":这里是日志记录的异常堆栈信息");
		}
	}
	@Test
	public void testServiceInsert(){
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
			exceptionTest.testJDBCException(user);
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
