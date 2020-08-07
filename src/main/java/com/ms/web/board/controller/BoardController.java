package com.ms.web.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ms.web.board.model.BoardVO;
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
	
	@RequestMapping("/boardForm")
	public String boardForm(@ModelAttribute("boardVO") BoardVO boardVO, Model model) {
		return "board/boardForm";
	}
	
	/* redirect 사용하는 이유
	 * 글을 쓰고 저장 버튼을 눌러 리스트 화면까지 보이는 단계를 생각해 보면
		따라서 '뒤로가기' 버튼을 클릭하면 다시 한 번 '저장하기' 단계로 가게 됩니다.
		이 말은 일명 '게시물 도배'의 여지를 남겨 둘 수 있는 길이 됩니다.
		이와 같은 일을 방지하기 위해 RedirectAttributes를 하게 되면 '저장단계' 를 지나 글쓰기 폼으로 돌아 가게 됩니다.
		따라서 글을 자동으로 도배할 수 없도록 할 수 있습니다.
	*/
	@RequestMapping(value = "/saveBoard", method = RequestMethod.POST)
	public String saveBoard(@ModelAttribute("BoardVO") BoardVO boardVO, //화면에서 넘겨주는 값을 BoardVO 와 매칭시켜 데이터를 받아 온다.
			@RequestParam("mode") String mode,
			RedirectAttributes rttr) throws Exception { //RedirectAttributes 를 사용하는  이유는 브라우저의 '뒤로가기' 버튼에 대한 대응책 입니다.
			
		if(mode.equals("mode")){
			boardService.updateBoard(boardVO);
		}else{
			boardService.insertBoard(boardVO);
		}
		return "redirect:/board/getBoardList";
	}
	
	//상세조회
	@RequestMapping(value = "/getBoardContent", method = RequestMethod.GET)
	public String getBoardContent(Model model, @RequestParam("bid") int bid) throws Exception{
		model.addAttribute("boardContent", boardService.getBoardContent(bid));
		return "board/boardContent";
	}
	
	@RequestMapping(value="/editForm", method = RequestMethod.GET)
	public String editForm(Model model, @RequestParam("bid") int bid, @RequestParam("mode") String mode) throws Exception{
		model.addAttribute("boardContent", boardService.getBoardContent(bid)); // 1. db상에 존재하는 기존 data 조회
		model.addAttribute("mode", mode); // mode 판단, edit
		model.addAttribute("boardVO", new BoardVO()); // data를 새로 담을 new BoardVO .
		return "board/boardForm";
	}
	
	@RequestMapping(value="/deleteBoard", method=RequestMethod.GET)
	public String deleteBoard(@RequestParam("bid") int bid, RedirectAttributes rttr) throws Exception{ // Q) param으로 RedirectAttributes 쓰는 이유? 
		boardService.deleteBoard(bid);
		return "redirect:/board/getBoardList";
	}

}
