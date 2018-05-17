package cn.author.test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.author.common.utils.SpringJunitTest;
import cn.author.entity.User;
import cn.author.entity.UserExample;
import cn.author.entity.UserExample.Criteria;
import cn.author.mapper.UserMapper;

public class MybatisSRCDebug extends SpringJunitTest{
	@Autowired
	private UserMapper userMapper;
	
    @Test
    /**
     * 方法错误，拿不到session,禁用
     */
    public void findUserById() {
        SqlSession sqlSession = getSessionFactory().openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserExample userExample = new UserExample();
		Criteria userCriteria = userExample.createCriteria();
		List<User> userList = userMapper.selectByExample(userExample);
		for (User user : userList) {
			System.out.println("this is git update test"+user.toString());
			System.out.println("测试全局");
		}

    }
	 //Mybatis 通过SqlSessionFactory获取SqlSession, 然后才能通过SqlSession与数据库进行交互
    private static SqlSessionFactory getSessionFactory() {
        SqlSessionFactory sessionFactory = null;
        String resource = "config/mybatis.xml";
        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources
                    .getResourceAsReader(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
    
	@Test
	public void test1(){
		UserExample userExample = new UserExample();
		Criteria userCriteria = userExample.createCriteria();
		List<User> userList = userMapper.selectByExample(userExample);
		for (User user : userList) {
			System.out.println("this is git update test"+user.toString());
			System.out.println("测试全局");
		}
	}
	
	@Test
	public void testInsert(){
		User user = new User();
		user.setDate(new Date());
		user.setName("李四");
		userMapper.insertSelective(user);		
	}
}
