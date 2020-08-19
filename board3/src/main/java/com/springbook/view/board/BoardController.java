package com.springbook.view.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.springbook.biz.BoardListVO;
import com.springbook.biz.BoardVO;
import com.springbook.biz.board.BoardService;

@Controller
@SessionAttributes("board")//null 업제이트 방지
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping(/dataTransformX.do)
	@ResponseBody
	public BoardListVO dataTransform(BoardVO vo){
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		
		List<BoardVO> boardList = boardService.getBoardList(vo);
		BoardListVO boardListVO = new BoardListVO();
		boardListVO.setBoardList(boardList);
		
		return boardListVO;
	}
	@RequestMapping("/dataTransform.do")
	@ResponseBody//
	public List<BoardVO> boardList = 
	public String insertBoard(BoardVO vo) throws IOException{
		//파일 업로드 처리
		MultipartFile uploadFile = vo.getUploadFile();
		if(!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("c:/upload/"+fileName));//지정경로로 파일 저장
		}
		boardService.insertBoard(vo);
		return "getBoardList.do";
	}
	//검색조건목록 설정
	//@ModelAttribute("속성명") 리턴값은 객체로 리턴
	//view페이지로 전달시 Model객체에 저장됨.
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap(){
		Map<String, String> conditionMap = new HashMap<>();
		conditionMap.put("전체", null);
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용","CONTENT");
		return conditionMap;
	}
	
	//글목록 검색
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, Model model) {//Model:view로 객체 전달
		if(vo.getSearchCondition()==null) vo.setSearchCondition("TITLE");
		if(vo.getSearchKeyword()==null) vo.setSearchKeyword("");
		
			model.addAttribute("boardList",boardService.getBoardList(vo));
			return "getBoardList.jsp";
	}
	
	//글 상세 조회
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model/*,HttpServletRequest request*/) {
		System.out.println("글번호:"+vo.getSeq());
		/* int seq = Integer.parseInt(request.getParameter("seq"));//자동으로 넘어와 필요없다. */
	    //vo.setImages("c:/upload/"+rs.getString("uploadFile"));
		model.addAttribute("board", boardService.getBoard(vo));//Model에 board정보 저장
		return "getBoard.jsp";//View명 리턴
	}
	
	//글 수정
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) throws IOException {//@ModelAttribute("board")하면 return "getBoardList.do"할 때 board정보 같이 넘어감.
		//파일 업로드 처리
		MultipartFile uploadFile = vo.getUploadFile();
		if(!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("c:/upload/"+ fileName));//지정경로로 파일 저장 		
		}
		boardService.updateBoard(vo);
		return "getBoardList.do";
	}
	
	//글 삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		boardService.deleteBoard(vo);
		return "getBoardList.do";
	}
}
