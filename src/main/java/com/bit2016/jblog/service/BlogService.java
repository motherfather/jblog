package com.bit2016.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2016.jblog.repository.BlogDao;
import com.bit2016.jblog.repository.CategoryDao;
import com.bit2016.jblog.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	BlogDao blogDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	public String checkBlog(String userId) {
		return blogDao.checkBlog(userId);
	}
	
	public List<String> listCate(String userId) {
		return categoryDao.listCate(userId);
	}
	
	public BlogVo infoBlog(String userId) {
		return blogDao.infoBlog(userId);
	}
}
