package com.ms.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ms.web.user.model.UserVO;


@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	
	public String loginForm(Model model) throws Exception{
		model.addAttribute("userVO", new UserVO());
		return "login/login";
	}
	
	@RequestMapping(value = "/signupForm", method = RequestMethod.GET)
	public String signupForm(Model model) throws Exception { 
		model.addAttribute("userVO", new UserVO()); 
		return "login/signupForm"; 
	}


}
