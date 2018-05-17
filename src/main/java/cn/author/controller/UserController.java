package cn.author.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.author.entity.User;
import cn.author.entity.UserExample;
import cn.author.entity.UserExample.Criteria;
import cn.author.mapper.UserMapper;
@Controller
@RequestMapping(value="user")
public class UserController {
	@Autowired
	private UserMapper userMapper;
//	@ResponseBody
//	@RequestMapping(value="/singleUser")
	@RequestMapping(value="/singleUser",method = RequestMethod.GET,produces = {"application/json", "application/json;charset=UTF-8"})
	public List<User> singleUser(Model model){
		UserExample userExample = new UserExample();
		Criteria userCriteria = userExample.createCriteria();
		List<User> userList = userMapper.selectByExample(userExample);
		//return JSON.toJSONString(userList);
		return userList;
	}
	
	@RequestMapping(value="list")
	public String listUser(Model model){
		UserExample userExample = new UserExample();
		Criteria userCriteria = userExample.createCriteria();
		List<User> userList = userMapper.selectByExample(userExample);
		model.addAttribute("list", userList);
		return "user";
	}

}
