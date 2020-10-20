package com.ms.web.user.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ms.encrypt.Encrypt;
import com.ms.web.user.model.UserVO;
import com.ms.web.user.service.UserService;

@Controller 
@RequestMapping(value = "/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class); 
	
	@RequestMapping(value = "/userList", method = RequestMethod.GET) 
	public String userList(Model model) throws Exception{ 
		System.out.println("@userList");
//		model.addAttribute("userList", userService.getUserList());
		model.addAttribute("userVO", new UserVO());
		return "user/user"; 
	}
	
	@Inject 
	private UserService userService;
	
	@RequestMapping(value = "/getUserList", method = RequestMethod.GET) 
	public String getUserList(Model model) throws Exception{ 
		logger.info("getUserList()...."); 
		model.addAttribute("userList", userService.getUserList()); 
		return "user/userList"; 
	}
	
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST) 
	public String insertUser(@ModelAttribute("userVO") UserVO userVO , RedirectAttributes rttr) throws Exception {
		// 암호화 추가.
		Encrypt enc = new Encrypt();
		userVO.setPwd(enc.encAES(userVO.getPwd()));
		
		userService.insertUser(userVO); 
		return "redirect:/user/getUserList"; 
	}

}
