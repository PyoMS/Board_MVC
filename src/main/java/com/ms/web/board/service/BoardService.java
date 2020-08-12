package com.ms.web.board.service;
import java.util.List;

import com.ms.web.board.model.BoardVO;
import com.ms.web.common.Search;

public interface BoardService {
	public List<BoardVO> getBoardList(Search search) throws Exception;
	public void insertBoard(BoardVO boardVO) throws Exception; //�� �ۼ�
	public BoardVO getBoardContent(int bid) throws Exception;
	public void updateBoard(BoardVO boardVO) throws Exception;
	public void deleteBoard(int bid) throws Exception;
	//�� �Խñ� ���� Ȯ��
	public int getBoardListCnt(Search search) throws Exception;
}
