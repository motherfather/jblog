package com.bit2016.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2016.jblog.repository.BlogDao;

@Service
public class BlogService {

	@Autowired
	BlogDao blogDao;
	
	public String checkBlog(String userId) {
		return blogDao.checkBlog(userId);
	}
}
