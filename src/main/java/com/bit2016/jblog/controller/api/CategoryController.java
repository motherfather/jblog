package com.bit2016.jblog.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2016.dto.JSONResult;
import com.bit2016.jblog.service.CategoryService;
import com.bit2016.jblog.vo.CategoryVo;
import com.bit2016.jblog.vo.UserVo;
import com.bit2016.security.Auth;
import com.bit2016.security.AuthUser;

@Controller
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Auth
	@ResponseBody
	@RequestMapping("/add")
	public JSONResult add(@PathVariable String userId, @AuthUser UserVo authUser, 
			@ModelAttribute CategoryVo vo) {
		System.out.println(vo);
//		if (!authUser.getId().equals(userId)) {
//			return "";
//		}
		CategoryVo vo2 = categoryService.add(vo);
		return JSONResult.success(vo2);
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public JSONResult list(@RequestParam(value="blogNo", required=true, defaultValue="1") Long blogNo) {
		List<CategoryVo> list = categoryService.list(blogNo);
		return JSONResult.success(list);
	}

}
