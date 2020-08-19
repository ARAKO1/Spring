package com.springbook.biz.user.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserVO;

@Repository("userDAO")//userDAO를 userServiceImpl에서 사용
public class UserDAO {//DAO는 DB하고 연동하는 객체
	private Connection conn = null;
	private PreparedStatement pstmt=null;
	private ResultSet rs = null;
	
	private final String USER_GET ="select * from users where id=? and password=?";

	public UserVO getUser(UserVO vo) {
		UserVO user= null;
		try {
			System.out.println("===>jdbc로 getUser()기능 처리");
			conn=JDBCUtil.getConnection();
			pstmt=conn.prepareStatement(USER_GET);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				user=new UserVO();
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setRole(rs.getString("role"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs,pstmt, conn);	
		}
		return user;
	}
	
	
}
