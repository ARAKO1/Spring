package com.springbook.biz;

import java.util.List;

@
@
public class BoardListVO {
	@XmlElement(name="board")
	private List<BoardVO>boardList;
	
	public List<BoardVO> getBoardList(){
	return boardList;
}
	public void setBoardList(List<BoardVO> boardList) {
		this.boardList = boardList;
	}
