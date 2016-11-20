package com.bit2016.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2016.jblog.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo login(String id, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("password", password);
		
		return sqlSession.selectOne("user.login", map);
	}
	
	public boolean checkId(String id) {
		if (sqlSession.selectOne("user.checkId", id) != null) {
			return true; 
		}
		return false;
	}
	
	public Long join(UserVo vo) {
		sqlSession.insert("user.join", vo);
		return vo.getNo();
		// sqlSession.insert("blog.insert", vo.getNo()); // Dao마다 쿼리는 1개만 쏘는게 좋다!!!!!
	}
	
	public Long getNo(String userId) {
		return sqlSession.selectOne("user.getNo", userId);
	}
}
