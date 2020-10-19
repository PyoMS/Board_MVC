package com.ms.web.login;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ms.web.user.dao.UserDAO;
import com.ms.web.user.model.UserVO;


@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	@Inject
	private UserDAO UserDao;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(Model model) throws Exception{
		model.addAttribute("userVO", new UserVO());
		return "login/login";
	}
	
	@RequestMapping(value = "/signupForm", method = RequestMethod.GET)
	public String signupForm(Model model) throws Exception { 
		model.addAttribute("userVO", new UserVO()); 
		return "login/signupForm"; 
	}
	
	@ResponseBody
	@RequestMapping(value = "test.do", method = RequestMethod.POST)
	public void duplicateID(Model model, @RequestParam("userVO") UserVO userVO  ,HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("duplicateID : "+ userVO.getUid());
		
		JSONObject resultdata = new JSONObject();
		
		try {
			System.out.println("Test : " + UserDao.getUserInfo(request.getParameter("uid")).getUid());
			if(UserDao.getUserInfo(userVO.getUid()).getUid()!=null || UserDao.getUserInfo(userVO.getUid()).getUid()!=""){
				resultdata.put("result", "0");
			}
				
		} 
		catch (NullPointerException e) { // null 값이 나온다 => 기존 데이터가 존재 하지 않는다.
			resultdata.put("result", "1");
		} 
		catch(Exception e ){
			e.printStackTrace();
			throw e;
		}
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(resultdata);
				
	}

}
