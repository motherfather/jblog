package com.bit2016.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2016.dto.JSONResult;
import com.bit2016.jblog.service.PostService;
import com.bit2016.jblog.vo.PostVo;
import com.bit2016.security.Auth;

@Controller
@RequestMapping("/{userId}")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@Auth
	@RequestMapping("/write")
	public String write(@ModelAttribute PostVo vo) {
		System.out.println(vo);
		if (vo.getCategoryNo() == 0) {
			return "redirect:/{userId}/post";
		}
		postService.write(vo);
		return "redirect:/{userId}";
	}
	

}
