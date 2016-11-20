package com.bit2016.jblog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2016.jblog.repository.CategoryDao;
import com.bit2016.jblog.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	public List<Map<String, Object>> categoryList(String userId) {
		return categoryDao.categoryList(userId);
	}
	
	public CategoryVo add(CategoryVo vo) {
		Long no = categoryDao.add(vo);
		return categoryDao.get(no);
	}
	
	public List<CategoryVo> list(Long blogNo) {
		return categoryDao.list(blogNo);
	}
}
