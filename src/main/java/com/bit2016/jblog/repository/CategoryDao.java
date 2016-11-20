package com.bit2016.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit2016.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void createCategory(Long no) {
		sqlSession.insert("category.createCategory", no);
	}
	
	public List<Map<String, Object>> categoryList(String userId) {
		return sqlSession.selectList("category.categoryList", userId);
	}
	
	public Long add(CategoryVo vo) {
		sqlSession.insert("category.add", vo);
		return vo.getNo();
	}
	
	public CategoryVo get(Long no) {
		return sqlSession.selectOne("category.get", no);
	}
	
	public List<CategoryVo> list(Long blogNo) {
		return sqlSession.selectList("category.list", blogNo);
	}
}
