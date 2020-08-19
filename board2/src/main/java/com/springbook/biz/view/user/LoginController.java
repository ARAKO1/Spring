package com.springbook.biz.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springbook.biz.user.Impl.UserDAO;

public class LoginController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("로그인 처리");
		
		//1.사용자 입력 정보 추출
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		
		ModelAndView mav = new ModelAndView();
		/* UserVO user=null; */
		
		//2.db연동처리
		UserVO vo= new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		
		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.getUser(vo);
		
		HttpSession session = request.getSession();
		
		//3.화면네비게이션
		if(user!=null) {
			session.setAttribute("user",user);//정보가 있을 때에만
			mav.setViewName("redirect:getBoardList.do");/*redirect: 는 view규칙이 아닌 url을 따른다.(=reponse.sendRedirect();-url이 바뀐다 ,forward가 아님-forward는 url은 그대로 존재 ,viewResolver는 forward에대한 규칙)*/
		}else {
			mav.setViewName("redirect:login");
		}
		return mav;
	}

}
