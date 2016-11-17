package com.bit2016.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit2016.jblog.service.BlogService;
import com.bit2016.jblog.vo.BlogVo;
import com.bit2016.jblog.vo.UserVo;
import com.bit2016.security.Auth;
import com.bit2016.security.AuthUser;

@Controller
@RequestMapping("/{userId}")
public class BlogController {

	@Autowired
	BlogService blogService;
	
	@RequestMapping({"", "/{postNo}"}) // [참고] @PathVariable Optional<Long> no
	public String index(@PathVariable String userId, Model model, @PathVariable Long postNo) {
		String info = blogService.checkBlog(userId);
		List<String> listCate = blogService.listCate(userId);
		BlogVo infoBlog = blogService.infoBlog(userId);
		Long no1 = postNo == null ? 0 : postNo;

		if (info == null) {
			return "redirect:/";
		}
		
		model.addAttribute("infoBlog", infoBlog);
		model.addAttribute("listCate", listCate);
		model.addAttribute("info", info);
		return "blog/blog-main";
	}
	
	@Auth
	@RequestMapping("/admin")
	public String admin(@PathVariable String userId, @AuthUser UserVo authUser, Model model) {
		BlogVo infoBlog = blogService.infoBlog(userId);

		model.addAttribute("infoBlog", infoBlog);
		return "blog/blog-admin-basic";
	}
	
	@Auth
	@RequestMapping("/setup")
	public String setup(@PathVariable String userId) {
		return "";
	}
	
}
