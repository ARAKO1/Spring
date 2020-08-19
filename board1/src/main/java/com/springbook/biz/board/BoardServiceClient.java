package com.springbook.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.BoardVO;

public class BoardServiceClient {

	public static void main(String[] args) {
		//1.Spring 컨테이너 구동
			AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		//2.Spring컨테이너로부터 BoardService얻기
			BoardService boardService = (BoardService)container.getBean("boardService");
		
		//3.글 등록기능 테스트
			BoardVO vo = new BoardVO();
			vo.setSeq(100);
			vo.setTitle("임시제목");
			vo.setWriter("홍길동");       
			vo.setContent("임시내용.....");
			boardService.insertBoard(vo);
		
		//4.글 목록검색기능 테스트
			List<BoardVO> boardList = boardService.getBoardList(vo); 
			for(BoardVO board:boardList) {
				System.out.println("---->"+board.toString());
			}
		//4-1.글 상세보기
			BoardVO b = new BoardVO();
			b.setSeq(1);
			
			BoardVO board = boardService.getBoard(b);
			System.out.println("글 상세조회:");
			System.out.println("-------->"+board);
		
		//4-2. 글 수정하기
			b.setSeq(2);
			b.setTitle("수정 제목....");
			b.setContent("수정 내용....");
			
			//수정처리
			boardService.updateBoard(b);
			
			//수정 내용
			board = boardService.getBoard(b);
			System.out.println("---->"+board);
		
		//4-3. 글 삭제하기
			vo.setSeq(3);
			boardService.deleteBoard(vo);
			System.out.println("글 삭제조회:");
			System.out.println("-------->"+board);
			
			
		//5.컨테이너 종료
				container.close();
	}

}
