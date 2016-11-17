package com.bit2016.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostDao {

	@Autowired
	SqlSession sqlSession;
	
	public Long totalPost(String userId) {
		return sqlSession.selectOne("blog.totalPost", userId);
	}
}
