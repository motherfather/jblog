package com.bit2016.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2016.jblog.repository.BlogDao;
import com.bit2016.jblog.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	BlogDao blogDao;
	
	public Object checkBlog(String userId) {
		return blogDao.checkBlog(userId);
	}
}
