package cn.author.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.author.entity.User;
import cn.author.mapper.UserMapper;

@Service
public class ExceptionTestImpl implements ExceptionTest {
	@Autowired
	private UserMapper userMapper;

	@Override
	public void throwAExcetion() {
		// TODO Auto-generated method stub
		throw new RuntimeException("这里是测试抛出异常！");
	}

	@Override
	public void testJDBCException(User user) {
		userMapper.insertSelective(user);		
	}

}
