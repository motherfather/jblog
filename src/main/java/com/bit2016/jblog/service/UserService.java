package com.bit2016.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2016.jblog.repository.BlogDao;
import com.bit2016.jblog.repository.CategoryDao;
import com.bit2016.jblog.repository.UserDao;
import com.bit2016.jblog.vo.UserVo;

@Service
public class UserService {
	// controller가 여러 service를 사용하는 것보단 service가 여러 repository를 쓰는게 낫다!!!!!!!!!!!
	// service가 다른 service를 사용할 순 없다!!!!!!
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	public UserVo login(String id, String password) {
		UserVo userVo = userDao.login(id, password);
		return userVo;
	}
	
	public void join(UserVo vo) {
		userDao.join(vo);
		blogDao.createBlog(vo.getNo());
		categoryDao.createCategory(vo.getNo());
	}
	
	public boolean checkId(String id) {
		return userDao.checkId(id);
	}
}
