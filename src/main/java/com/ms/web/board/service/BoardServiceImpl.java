package com.ms.web.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ms.web.board.dao.BoardDAO;
import com.ms.web.board.model.BoardVO;
import com.ms.web.error.controller.NotFoundException;

@Service
public class BoardServiceImpl implements BoardService{

	@Inject
	private BoardDAO boardDAO;
	
	@Override
	public List<BoardVO> getBoardList() throws Exception {
		return boardDAO.getBoardList();
	}

	@Override
	public void insertBoard(BoardVO boardVO) throws Exception {
		boardDAO.insertBoard(boardVO);
	}
	
/*
	@Override	//원본
	public BoardVO getBoardContent(int bid) throws Exception {
		boardDAO.updateViewCnt(bid); //조회수 ++
		return boardDAO.getBoardContent(bid);
	}
*/
	
	@Override
	public BoardVO getBoardContent(int bid) throws Exception{
		BoardVO boardVO = new BoardVO();
		boardDAO.updateViewCnt(bid);
	//	boardVO = boardDAO.getBoardContent(bid);
		try {
			boardVO.setBid(bid);
			boardVO.setCate_cd("1111111111111111111111111111111111111");
			boardDAO.updateBoard(boardVO);
		} catch (RuntimeException e) {
			throw new NotFoundException();
		}
		return boardVO;
	}
	
	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		boardDAO.updateBoard(boardVO);
	}

	@Override
	public void deleteBoard(int bid) throws Exception {
		boardDAO.deleteBoard(bid);
	}


}
