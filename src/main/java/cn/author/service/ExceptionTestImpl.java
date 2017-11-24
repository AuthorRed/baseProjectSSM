package cn.author.service;

import org.springframework.stereotype.Service;

@Service
public class ExceptionTestImpl implements ExceptionTest {

	@Override
	public void throwAExcetion() {
		// TODO Auto-generated method stub
		throw new RuntimeException("这里是测试抛出异常！");
	}

}
