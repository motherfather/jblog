package com.bit2016.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public List<Map<String, Object>> blogMain(String userId) {
//		List<Map> map = sqlSession.selectList("blog.ch", userId);
		return sqlSession.selectList("blog.blogMain", userId);
	}
	
	public List<Map<String, Object>> blogMain1(String userId, Object cNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("cNo", cNo);
		
		return sqlSession.selectList("blog.blogMain1", map);
	}
	
	public List<Map<String, Object>> blogMain2(String userId) {
		return sqlSession.selectList("blog.blogMain2", userId);
	}
	
	public List<Map<String, Object>> mainPost(String userId) {
		return sqlSession.selectList("blog.mainPost", userId);
	}
	
	public List<Map<String, Object>> CPNo(String userId, Long cNo, Long pNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("cNo", cNo);
		map.put("pNo", pNo);
		
		return sqlSession.selectList("blog.CPNo", map);
	}
	
	public List<Map<String, Object>> PNo(String userId, Long pNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("pNo", pNo);
		
		return sqlSession.selectList("blog.PNo", map);
	}
	
	public Long PPerC(String userId, Long cNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("cNo", cNo);

		return sqlSession.selectOne("blog.PPerC", map);
	}
	
	public void modify(BlogVo vo) {
		sqlSession.update("blog.modify", vo);
	}
}
