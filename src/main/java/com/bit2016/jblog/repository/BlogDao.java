package com.bit2016.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public void createBlog(Long no) {
		sqlSession.insert("blog.createBlog", no);
	}
	
	public String checkBlog(String userId) {
		return sqlSession.selectOne("blog.checkBlog", userId);
	}
}
