package com.ms.web.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public String boardForm() {
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
			RedirectAttributes rttr //RedirectAttributes �� ����ϴ�  ������ �������� '�ڷΰ���' ��ư�� ���� ����å �Դϴ�.
			) throws Exception {
		boardService.insertBoard(boardVO);
		return "redirect:/board/getBoardList";
	}


}
