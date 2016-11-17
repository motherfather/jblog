package com.bit2016.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void createCategory(Long no) {
		sqlSession.insert("category.createCategory", no);
	}
	
	public List<String> listCate(String userId) {
		return sqlSession.selectList("category.listCate", userId);
	}
}
