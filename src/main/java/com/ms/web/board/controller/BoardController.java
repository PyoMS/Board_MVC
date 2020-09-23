package com.ms.web.board.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ms.web.board.model.BoardVO;
import com.ms.web.board.model.ReplyVO;
import com.ms.web.board.service.BoardService;
import com.ms.web.common.Search;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	private JSONObject resultdata = null;
	
	@Inject
	private BoardService boardService;
	
	@RequestMapping(value = "/getBoardList", method = RequestMethod.GET)
	public String getBoardList(Model model, 
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String keyword) throws Exception {
		System.out.println("@getBoardList");

		Search search = new Search();
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		// ��ü �Խñ� ����
		int listCnt = boardService.getBoardListCnt(search);
		// Pagination ��ü����
//		Pagination pagination = new Pagination();
//		pagination.pageInfo(page, range, listCnt);
		search.pageInfo(page, range, listCnt);
		
		model.addAttribute("pagination", search);
		model.addAttribute("boardList", boardService.getBoardList(search));
		return "board/index"; // return �Ǵ� ȭ���� �ּҰ�. (�ܼ� String x)
	}
	
	@RequestMapping("/boardForm")
	public String boardForm(@ModelAttribute("boardVO") BoardVO boardVO, Model model) {
		System.out.println("boardForm");
		return "board/boardForm";
	}

	/*
	 * redirect ����ϴ� ���� ���� ���� ���� ��ư�� ���� ����Ʈ ȭ����� ���̴� �ܰ踦 ������ ���� ���� '�ڷΰ���' ��ư��
	 * Ŭ���ϸ� �ٽ� �� �� '�����ϱ�' �ܰ�� ���� �˴ϴ�. �� ���� �ϸ� '�Խù� ����'�� ������ ���� �� �� �ִ� ���� �˴ϴ�. �̿�
	 * ���� ���� �����ϱ� ���� RedirectAttributes�� �ϰ� �Ǹ� '����ܰ�' �� ���� �۾��� ������ ���� ���� �˴ϴ�.
	 * ���� ���� �ڵ����� ������ �� ������ �� �� �ֽ��ϴ�.
	 */
	
	@RequestMapping(value = "/saveBoard", method = RequestMethod.POST)
	public String saveBoard(@ModelAttribute("BoardVO") BoardVO boardVO, // ȭ�鿡�� �Ѱ��ִ� ���� BoardVO�� ��Ī���� �����͸� �޾� �´�.
			@RequestParam("mode") String mode, RedirectAttributes rttr) throws Exception { // RedirectAttributes�� ����ϴ� ������ �������� '�ڷΰ���'��ư�� ���� ����å.
		System.out.println("@saveBoard");
		if (mode.equals("edit")|| mode=="edit") {
			boardService.updateBoard(boardVO);
		} else {
			boardService.insertBoard(boardVO);
		}
		return "redirect:/board/getBoardList";
	}
	// ����ȸ
	@RequestMapping(value = "/getBoardContent", method = RequestMethod.GET)
	public String getBoardContent(Model model, @RequestParam("bid") int bid) throws Exception {
		System.out.println("@getBoardContent");
		model.addAttribute("boardContent", boardService.getBoardContent(bid));
		model.addAttribute("replyVO", new ReplyVO()); // ��۸���Ʈ
		return "board/boardContent";
	}

	@RequestMapping(value = "/editForm", method = RequestMethod.GET)
	public String editForm(Model model, @RequestParam("bid") int bid, @RequestParam("mode") String mode)
			throws Exception {
		System.out.println("@editForm");
		model.addAttribute("boardContent", boardService.getBoardContent(bid)); // 1.db�� �����ϴ� ���� data ��ȸ
		model.addAttribute("mode", mode); // mode �Ǵ�, edit
		model.addAttribute("boardVO", new BoardVO()); // data�� ���� ���� new BoardVO.
		return "board/boardForm";
	}

	@RequestMapping(value = "/deleteBoard", method = RequestMethod.GET)
	public String deleteBoard(@RequestParam("bid") int bid, RedirectAttributes rttr) throws Exception { // Q) param���� RedirectAttributes ���� ����?
		boardService.deleteBoard(bid);
		return "redirect:/board/getBoardList";
	}

	// @ResponseBody
	@ExceptionHandler({ SQLException.class, DataAccessException.class })
	public String exceptionHandler(Model model, Exception e) {
		logger.info("exception : " + e.getMessage());
		model.addAttribute("exception", e);
		return "error/exception";
	}
	
	
	@RequestMapping( value = "setPageRange.do", method=RequestMethod.POST)
	public void setPageRange(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("@@@ setPageRange");
		try {
			resultdata = new JSONObject();
			resultdata.put("bid", request.getParameter("bid"));
			resultdata.put("page", request.getParameter("page"));
			resultdata.put("range", request.getParameter("range"));
			
			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(resultdata);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@RequestMapping( value = "getPageRange.do", method=RequestMethod.POST)
	public void getPageRange(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("@@@ setPageRange");
		try {
			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(resultdata);
			resultdata.clear();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**	Reply Part 	*/
	
	@RequestMapping(value = "/getReplyList", method = RequestMethod.POST, produces = "application/json")
	public void getReplyList(int bid, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("@getReplyList");
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		list = boardService.getReplyList(bid);
		List<JSONObject> jsonlist = new ArrayList<>();
		
		System.out.println("list.size() : "+list.size());
		System.out.println("bid : " + bid);
		for (int i = 0; i < list.size(); i++) {
			JSONObject data = new JSONObject();
			System.out.println("rid : " + list.get(i).getRid());
			System.out.println("bid : " + list.get(i).getBid());
			System.out.println("content : " + list.get(i).getContent());
			System.out.println("reg_id : " + list.get(i).getReg_id());
			System.out.println("reg_dt : " + list.get(i).getReg_dt());
			System.out.println("edit_dt : " + list.get(i).getEdit_dt());
			
			data.put("rid", list.get(i).getRid());
			data.put("bid", list.get(i).getBid());
			data.put("content", list.get(i).getContent());
			data.put("reg_id", list.get(i).getReg_id());
			data.put("reg_dt", list.get(i).getReg_dt());
			data.put("edit_dt", list.get(i).getEdit_dt());
			
			jsonlist.add(data);
		}
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(jsonlist);
	}
	
	@RequestMapping(value = "/saveReply", method = RequestMethod.POST, produces = "application/json")	
	public void saveReply(HttpServletRequest request, HttpServletResponse response) throws Exception { // @RequestBody??
		try {
			System.out.println("@saveReply");
			
			ReplyVO data = new ReplyVO();
			System.out.println("request.getParameter(\"bid\") : "+ request.getParameter("bid"));
			System.out.println("request.getParameter(\"content\") : "+ request.getParameter("content"));
			System.out.println("request.getParameter(\"reg_id\") : "+ request.getParameter("reg_id"));
			
			data.setBid(Integer.parseInt(request.getParameter("bid")));
			data.setContent(request.getParameter("content"));
			data.setReg_id(request.getParameter("reg_id")); // reg_id
			boardService.saveReply(data);
			
//			response.setContentType("application/x-json; charset=UTF-8");
//			response.getWriter().print("");
//			result.put("status", "OK");
		} catch (Exception e) {
			e.printStackTrace();
//			result.put("status", "False");
		}
//		return result;
	}
	
	@RequestMapping(value = "/updateReply", method = RequestMethod.POST, produces = "application/json")	
	public void updateReply(HttpServletRequest request, HttpServletResponse response) throws Exception { // @RequestBody??
		try {
			System.out.println("@updateReply");
			
			ReplyVO data = new ReplyVO();
			System.out.println("request.getParameter(\"rid\") : "+ request.getParameter("rid"));
			System.out.println("request.getParameter(\"content\") : "+ request.getParameter("content"));
			
			data.setRid(Integer.parseInt(request.getParameter("rid")));
			data.setContent(request.getParameter("content"));
			boardService.updateReply(data);
//			response.setContentType("application/x-json; charset=UTF-8");
//			response.getWriter().print("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/deleteReply", method = RequestMethod.POST, produces = "application/json")	
	public void deleteReply(HttpServletRequest request, HttpServletResponse response) throws Exception { // @RequestBody??
		try {
			System.out.println("@deleteReply");
			
			int data = Integer.parseInt(request.getParameter("rid"));
			System.out.println("request.getParameter(\"rid\") : "+ request.getParameter("rid"));
			
			boardService.deleteReply(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
