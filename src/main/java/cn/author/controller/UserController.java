package cn.author.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.author.entity.User;
import cn.author.entity.UserExample;
import cn.author.entity.UserExample.Criteria;
import cn.author.mapper.UserMapper;
@Controller
@RequestMapping(value="user")
public class UserController {
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping(value="list")
	public String listUser(Model model){
		UserExample userExample = new UserExample();
		Criteria userCriteria = userExample.createCriteria();
		List<User> userList = userMapper.selectByExample(userExample);
		model.addAttribute("list", userList);
		return "user";
	}

}
