package com.ms.web.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.web.board.dao.BoardDAO;
import com.ms.web.board.model.BoardVO;
import com.ms.web.board.model.ReplyVO;
import com.ms.web.common.Pagination;
import com.ms.web.common.Search;
import com.ms.web.error.controller.NotFoundException;

@Service
public class BoardServiceImpl implements BoardService{

	@Inject
	private BoardDAO boardDAO;
	
	@Override
	public List<BoardVO> getBoardList(Search search) throws Exception {
		return boardDAO.getBoardList(search);
	}

	@Override
	public void insertBoard(BoardVO boardVO) throws Exception {
		boardDAO.insertBoard(boardVO);
	}
	
	@Override	//원본
	public BoardVO getBoardContent(int bid) throws Exception {
		boardDAO.updateViewCnt(bid); //조회수 ++
		return boardDAO.getBoardContent(bid);
	}
	
	/*
	@Transactional
	@Override
	public BoardVO getBoardContent(int bid) throws Exception{
		BoardVO boardVO = new BoardVO();
	//	boardVO = boardDAO.getBoardContent(bid);
		try {
			boardVO.setBid(bid);
			boardVO.setCate_cd("1111111111111111111111111111111111111");
			boardDAO.updateViewCnt(bid);
			boardDAO.updateBoard(boardVO);
		} catch (RuntimeException e) {
			throw new NotFoundException();
		}
		return boardVO;
	}
	*/
	
	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		boardDAO.updateBoard(boardVO);
	}

	@Override
	public void deleteBoard(int bid) throws Exception {
		boardDAO.deleteBoard(bid);
	}

	@Override
	public int getBoardListCnt(Search search) throws Exception {
		return boardDAO.getBoardListCnt(search);
	}
	
	// 댓글 리스트

	@Override
	public List<ReplyVO> getReplyList(int bid) throws Exception {
		return boardDAO.getReplyList(bid);
	}

	@Override
	public int saveReply(ReplyVO replyVO) throws Exception {
		return boardDAO.saveReply(replyVO);
	}

	@Override
	public int updateReply(ReplyVO replyVO) throws Exception {
		return boardDAO.updateReply(replyVO);
	}

	@Override
	public int deleteReply(int rid) throws Exception {
		return boardDAO.deleteReply(rid);
	}


}
