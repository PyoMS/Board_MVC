package com.ms.web.board.dao;

import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.ms.web.board.model.BoardVO;
import com.ms.web.board.model.ReplyVO;
import com.ms.web.common.Search;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public List<BoardVO> getBoardList(Search search) throws Exception {
		return sqlSession.selectList("com.ms.web.board.boardMapper.getBoardList", search);
	}

	@Override
	public BoardVO getBoardContent(int bid) throws Exception {
		return sqlSession.selectOne("com.ms.web.board.boardMapper.getBoardContent", bid);
	}

	@Override
	public int insertBoard(BoardVO boardVO) throws Exception {
		return sqlSession.insert("com.ms.web.board.boardMapper.insertBoard", boardVO);
	}

	@Override
	public int updateBoard(BoardVO boardVO) throws Exception {
		return sqlSession.update("com.ms.web.board.boardMapper.updateBoard", boardVO);
	}

	@Override
	public int deleteBoard(int bid) throws Exception {
		return sqlSession.delete("com.ms.web.board.boardMapper.deleteBoard", bid);
	}

	@Override
	public int updateViewCnt(int bid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("com.ms.web.board.boardMapper.updateViewCnt", bid);
	}

	@Override
	public int getBoardListCnt(Search search) throws Exception {
		return sqlSession.selectOne("com.ms.web.board.boardMapper.getBoardListCnt", search);
	}
	
	//Reply List
	@Override
	public List<ReplyVO> getReplyList(int bid) throws Exception {
		return sqlSession.selectList("com.ms.web.board.replyMapper.getReplyList", bid);
	}

	@Override
	public int saveReply(ReplyVO replyVO) throws Exception {
		return sqlSession.insert("com.ms.web.board.replyMapper.saveReply", replyVO);
	}

	@Override
	public int updateReply(ReplyVO replyVO) throws Exception {
		return sqlSession.update("com.ms.web.board.replyMapper.updateReply", replyVO);
	}

	@Override
	public int deleteReply(int rid) throws Exception {
		return sqlSession.delete("com.ms.web.board.replyMapper.deleteReply", rid);
	}


}
