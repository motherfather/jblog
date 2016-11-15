package com.bit2016.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit2016.jblog.repository.UserDao;
import com.bit2016.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	public UserVo login(String id, String password) {
		UserVo userVo = userDao.login(id, password);
		return userVo;
	}
}
