package com.bit2016.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2016.jblog.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public void createBlog(Long no) {
		sqlSession.insert("blog.createBlog", no);
	}
	
	public Object checkBlog(String userId) {
		return sqlSession.selectOne("blog.checkBlog", userId);
	}
}
