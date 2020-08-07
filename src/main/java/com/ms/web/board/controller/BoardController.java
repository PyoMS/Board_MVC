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
		return "board/index"; //return �Ǵ� ȭ���� �ּҰ�. (�ܼ� String x)
	}
	
	@RequestMapping("/boardForm")
	public String boardForm(@ModelAttribute("boardVO") BoardVO boardVO, Model model) {
		return "board/boardForm";
	}
	
	/* redirect ����ϴ� ����
	 * ���� ���� ���� ��ư�� ���� ����Ʈ ȭ����� ���̴� �ܰ踦 ������ ����
		���� '�ڷΰ���' ��ư�� Ŭ���ϸ� �ٽ� �� �� '�����ϱ�' �ܰ�� ���� �˴ϴ�.
		�� ���� �ϸ� '�Խù� ����'�� ������ ���� �� �� �ִ� ���� �˴ϴ�.
		�̿� ���� ���� �����ϱ� ���� RedirectAttributes�� �ϰ� �Ǹ� '����ܰ�' �� ���� �۾��� ������ ���� ���� �˴ϴ�.
		���� ���� �ڵ����� ������ �� ������ �� �� �ֽ��ϴ�.
	*/
	@RequestMapping(value = "/saveBoard", method = RequestMethod.POST)
	public String saveBoard(@ModelAttribute("BoardVO") BoardVO boardVO, //ȭ�鿡�� �Ѱ��ִ� ���� BoardVO �� ��Ī���� �����͸� �޾� �´�.
			@RequestParam("mode") String mode,
			RedirectAttributes rttr) throws Exception { //RedirectAttributes �� ����ϴ�  ������ �������� '�ڷΰ���' ��ư�� ���� ����å �Դϴ�.
			
		if(mode.equals("mode")){
			boardService.updateBoard(boardVO);
		}else{
			boardService.insertBoard(boardVO);
		}
		return "redirect:/board/getBoardList";
	}
	
	//����ȸ
	@RequestMapping(value = "/getBoardContent", method = RequestMethod.GET)
	public String getBoardContent(Model model, @RequestParam("bid") int bid) throws Exception{
		model.addAttribute("boardContent", boardService.getBoardContent(bid));
		return "board/boardContent";
	}
	
	@RequestMapping(value="/editForm", method = RequestMethod.GET)
	public String editForm(Model model, @RequestParam("bid") int bid, @RequestParam("mode") String mode) throws Exception{
		model.addAttribute("boardContent", boardService.getBoardContent(bid)); // 1. db�� �����ϴ� ���� data ��ȸ
		model.addAttribute("mode", mode); // mode �Ǵ�, edit
		model.addAttribute("boardVO", new BoardVO()); // data�� ���� ���� new BoardVO .
		return "board/boardForm";
	}
	
	@RequestMapping(value="/deleteBoard", method=RequestMethod.GET)
	public String deleteBoard(@RequestParam("bid") int bid, RedirectAttributes rttr) throws Exception{ // Q) param���� RedirectAttributes ���� ����? 
		boardService.deleteBoard(bid);
		return "redirect:/board/getBoardList";
	}

}
