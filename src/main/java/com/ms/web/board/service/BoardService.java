package com.ms.web.board.service;
import java.util.List;

import com.ms.web.board.model.BoardVO;

public interface BoardService {
	public List<BoardVO> getBoardList() throws Exception;
	public void insertBoard(BoardVO boardVO) throws Exception; //±€ ¿€º∫
	public BoardVO getBoardContent(int bid) throws Exception;
	public void updateBoard(BoardVO boardVO) throws Exception;
	public void deleteBoard(int bid) throws Exception;
}
