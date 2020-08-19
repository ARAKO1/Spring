package com.springbook.biz.board.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.springbook.biz.BoardVO;
import com.springbook.biz.common.JDBCUtil;

//@Component("boardDAO")
/*@Repository("boardDAO")//@Repository는 db처리하는 용도의 component*/
public class BoardDAO {
	//JDBC관련 변수
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs=null;
	
	//SQL명령어
	private final String BOARD_INSERT="insert into board(seq,title,writer,content) values((select nvl(max(seq),0)+1 from board),?,?,?)";
	private final String BOARD_UPDATE="update board set title=?, content=? where seq=?";
	private final String BOARD_DELETE="delete from board where seq=?";
	private final String BOARD_GET="select * from board where seq=?";
	private final String BOARD_LIST="select * from board order by seq desc";
	private final String BOARD_CNT="update board set cnt = nvl(cnt,0)+1 where seq=?";
	
	//글등록 메소드
	public void insertBoard(BoardVO vo) {
		System.out.println("===>jdbc로 insertBoard()기능 처리");
		try {
			conn=JDBCUtil.getConnection();
			pstmt= conn.prepareStatement(BOARD_INSERT);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3,vo.getContent());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);   
		}
	}
	
	//글목록 조회
	public List<BoardVO> getBoardList(BoardVO vo){
		System.out.println("===>jdbc로 getBoardList()기능처리");
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			conn=JDBCUtil.getConnection();
			pstmt=conn.prepareStatement(BOARD_LIST);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getDate("regDate"));
				board.setCnt(rs.getInt("cnt"));
				
				boardList.add(board);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return boardList;
	}

	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===>JDBC로 getBoard()기능 처리");
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO board = new BoardVO();	
		//조회수 증가 메소드 호출
		updateBoardCnt(vo);
		try {
			conn = JDBCUtil.getConnection();
			pstmt=conn.prepareStatement(BOARD_GET);
			pstmt.setInt(1, vo.getSeq());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				board.setSeq(vo.getSeq());
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getDate("regDate"));
				board.setCnt(rs.getInt("cnt"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return board;
	}

	public void updateBoard(BoardVO vo) {
		System.out.println("===>JDBC로 updateBoard()기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_UPDATE);
			pstmt.setNString(1, vo.getTitle());
			pstmt.setString(2,vo.getContent());
			pstmt.setInt(3,  vo.getSeq());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);//update니까 두개만 자원처리 
		}
	}

	public void deleteBoard(BoardVO vo) {
		System.out.println("===>JDBC로 deleteBoard()기능처리");
		try {
			conn=JDBCUtil.getConnection();
			pstmt=conn.prepareStatement(BOARD_DELETE);
			pstmt.setInt(1, vo.getSeq());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}	
	}
	
	public void updateBoardCnt(BoardVO vo) {
		System.out.println("===>JDBC로 updateBoardCnt()기능처리"+vo.getSeq());
		try {
			conn=JDBCUtil.getConnection();
			pstmt=conn.prepareStatement(BOARD_CNT);
			pstmt.setInt(1, vo.getSeq());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}	
	}
	
	
	
}
