package com.ms.web.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ms.web.board.service.BoardService;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	
	@Inject
	private BoardService boardService;
	
	@RequestMapping(value="/getBoardList", method=RequestMethod.GET)
	public String getBoardList(Model model) throws Exception {
		model.addAttribute("boardList", boardService.getBoardList());
		return "board/index"; //return 되는 화면의 주소값. (단순 String x)
	}
}
