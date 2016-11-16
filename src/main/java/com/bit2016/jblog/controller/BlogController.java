package com.bit2016.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit2016.jblog.service.BlogService;

@Controller
@RequestMapping("/{userId}")
public class BlogController {

	@Autowired
	BlogService blogService;
	
	@RequestMapping("")
	public String index(@PathVariable String userId, Model model) {
		Object info = blogService.checkBlog(userId);
		
		if (info == null) {
			return "redirect:/";
		}
		model.addAttribute("info", info);
		return "blog/blog-main";
	}
	
	
}
