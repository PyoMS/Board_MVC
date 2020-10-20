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
import com.ms.web.user.service.UserService;


@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	@Inject
	private UserService userService;
	
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
	
	//ajax 이므로 HttpServlet으로 정보값을 대체한다. 
	@ResponseBody
	@RequestMapping(value = "test.do", method = RequestMethod.POST)
	public void duplicateID(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("duplicateID : "+ request.getParameter("uid"));
		JSONObject resultdata = new JSONObject();
		
		try {
			UserVO userVO_service = userService.getUserInfo(request.getParameter("uid"));
			System.out.println("Test : " + userService.getUserInfo(request.getParameter("uid")));
			if(userVO_service.getUid()!=null || userVO_service.getUid()!=""){
				resultdata.put("result", "0");
			}
				
		} 
		catch (NullPointerException e) { // null 값이 나온다 => 기존 데이터가 존재 하지 않는다.
			e.printStackTrace();
			resultdata.put("result", "1");
		} 
		catch(Exception e ){
			e.printStackTrace();
			throw e;
		}
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(resultdata);
				
	}
	@RequestMapping(value = "/failLogin", method = RequestMethod.GET)
	public String loginFail(Model model) throws Exception{
		model.addAttribute("userVO", new UserVO());
		return "login/failLogin";
	}

}
