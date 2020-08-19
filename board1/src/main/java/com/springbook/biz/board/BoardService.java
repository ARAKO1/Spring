package com.springbook.biz.board;

import java.util.List;

import com.springbook.biz.BoardVO;

public interface BoardService {
	//CURD 기능 메소드
	//글등록
	void insertBoard(BoardVO vo);
	
	//글 수정
	void updateBoard(BoardVO vo);
	
	//글 삭제
	void deleteBoard(BoardVO vo);
	
	//글 상세조회
	BoardVO getBoard(BoardVO vo);
	
	//글 목록 조회
	List<BoardVO> getBoardList(BoardVO vo);

}
