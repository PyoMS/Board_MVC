package com.ms.web.menu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ms.web.menu.model.MenuVO;
import com.ms.web.menu.service.MenuService;


// Spring 버전으로 인하여 RestController 대신 일반 controller에 ajax 처리구현.
@Controller
@RequestMapping("/menu")
public class MenuController {
	
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	@RequestMapping(value = "/getMenu", method = RequestMethod.GET)
	public String getMenuList(Model model) throws Exception{
		
		model.addAttribute("menuVO", new MenuVO());
		return "menu/menu";
	}
	
	//**** RestController 구현 부 ****
	
	@Inject
	private MenuService menuService;
	
	@RequestMapping(value="/getMenuList", method=RequestMethod.POST)
	public void getMenuList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("@getMenuList");
		try {
			List<MenuVO> menuVOlist = new ArrayList<>();
			menuVOlist = menuService.getMenuList();
			
			List<JSONObject> jsonlist = new ArrayList<>();
			for (int i = 0; i < menuVOlist.size(); i++) {
				JSONObject data = new JSONObject();
				data.put("mid", menuVOlist.get(i).getMid());
				data.put("code", menuVOlist.get(i).getCode());
				data.put("codename", menuVOlist.get(i).getCodename());
				data.put("sort_num", menuVOlist.get(i).getSort_num());
				data.put("comment", menuVOlist.get(i).getComment());
				data.put("reg_id", menuVOlist.get(i).getReg_id());
				data.put("reg_dt", menuVOlist.get(i).getReg_dt());
				
				jsonlist.add(data);
			}
			
			//TODO 2020.10.12 Test 할 것.
//			JSONObject result = new JSONObject();
//			result.put("menuList", menuService.getMenuList());
			
			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(jsonlist);
//			response.getWriter().print(menuService.getMenuList());
		} catch (Exception e) {
//			result.put("status", "False");
			e.printStackTrace();
			logger.info(e.getMessage());
			throw e;
		}
	}
	
	@RequestMapping(value="/saveMenu", method=RequestMethod.POST)
	public void saveMenu(MenuVO menuVO, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
//		logger.info("menuVO : " + menuVO.toString());
		try {
			// 2020.10.08 Spring에서는 해당 VO 클래스를 단순히 파라미터로 지정하므로써 자동적으로 처리할 수 있다.
//			MenuVO menuVO = new MenuVO();
//			menuVO.setCode(request.getParameter("code"));
//			menuVO.setCodename(request.getParameter("codename"));
//			menuVO.setSort_num(Integer.parseInt(request.getParameter("sort_num")));
//			menuVO.setComment(request.getParameter("comment"));
			
//			System.out.println("menuVO.getCode() : " + menuVO.getCode());
//			System.out.println("menuVO.getCodename() : "+menuVO.getCodename());
//			System.out.println("menuVO.getSort_num() : "+menuVO.getSort_num());
//			System.out.println("menuVO.getComment() : "+menuVO.getComment());
			logger.info("menuVO : " + menuVO.toString());
			menuService.saveMenu(menuVO);
//			result.put("status", "OK");
		}catch (Exception e) {
//			result.put("status", "False");
			e.printStackTrace();
			logger.info(e.getMessage());
			throw e;
		}
//		return result;
	}
	
	@RequestMapping(value="/updateMenu", method=RequestMethod.POST)
	public void updateMenu(MenuVO menuVO, HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
//			MenuVO menuVO = new MenuVO();
//			menuVO.setCode(request.getParameter("code"));
//			menuVO.setCodename(request.getParameter("codename"));
//			menuVO.setSort_num(Integer.parseInt(request.getParameter("sort_num")));
//			menuVO.setComment(request.getParameter("comment"));
			
			menuService.updateMenu(menuVO);
//			result.put("status", "OK");
		} catch (Exception e) {
//			result.put("status", "False");
			e.printStackTrace();
			logger.info(e.getMessage());
			throw e;
		}
//		return result;
	}
	
	@RequestMapping(value="/deleteMenu", method=RequestMethod.POST)
	public void deleteMenu(HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String code) throws Exception{
		try {
			menuService.deleteMenu(code);
//			result.put("status", "OK");
		} catch (Exception e) {
//			result.put("status", "False");
			e.printStackTrace();
			logger.info(e.getMessage());
			throw e;
		}
//		return result;
	}

}
