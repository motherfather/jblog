package com.bit2016.jblog.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bit2016.jblog.service.BlogService;
import com.bit2016.jblog.service.CategoryService;
import com.bit2016.jblog.vo.BlogVo;
import com.bit2016.jblog.vo.UserVo;
import com.bit2016.security.Auth;
import com.bit2016.security.AuthUser;

@Controller
@RequestMapping("/{userId:(?!assets).*}")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping({"", "/{path1}", "/{path1}/{path2}"}) // [참고] @PathVariable Optional<Long> no
	public String index(Model model, @PathVariable String userId, @PathVariable Optional<Long> path1, @PathVariable Optional<Long> path2) {
		
		List<Map<String, Object>> list = null;
		List<Map<String, Object>> list2 = blogService.blogMain2(userId);
		
		Long cNo = null;
		Long pNo = null;
		List<Map<String, Object>> mainPost = null;
		
		// path 값이 있느냐 없느냐에 따라 결정
		if (path2.isPresent()) {
			cNo = path1.get();
			pNo = path2.get();
			
			// 카테고리를 눌러서 이동할 때는 /categoryNo/0으로 이동 시킨 후
			// 해당 카테고리의 마지막 포스트의 no를 구해서 그 포스트로 리다이렉트
			if (pNo == 0) {
				pNo = blogService.PPerC(userId, cNo);
				
				// cNo와 pNo는 모델에 실려 있지 않아서 실어야 함
				model.addAttribute("cNo", cNo);
				model.addAttribute("pNo", pNo);
				return "redirect:/{userId}/{cNo}/{pNo}";
			} else {
				mainPost = blogService.CPNo(userId, cNo, pNo);
				list = blogService.blogMain1(userId, cNo);
			}
		} else if (path1.isPresent()) {
			pNo = path1.get();
			mainPost = blogService.PNo(userId, pNo);
			// mainPost.get(0).get("CNO")이 object 타입인데 (Long)을 변환이 안되서... Object타입을 받는걸로 변경.. 왜안되지?
			list = blogService.blogMain1(userId, mainPost.get(0).get("CNO"));
		} else {
			mainPost = blogService.mainPost(userId);
			list = blogService.blogMain(userId);
		}

		// 아이디가 없으면 list의 size는 0이 되고 메인으로 돌아감
		if (list.size()==0) {
			return "redirect:/";
		}
		
		model.addAttribute("list", list);
		model.addAttribute("mainPost", mainPost);
		
		model.addAttribute("userId", userId);
		model.addAttribute("list2", list2);
		return "blog/blog-main";
	}
	
	@Auth
	@RequestMapping("/admin")
	public String admin(@PathVariable String userId, @AuthUser UserVo authUser, Model model) {
		if (!authUser.getId().equals(userId)) {
			return "redirect:/{userId}";
		}
		List<Map<String, Object>> list2 = blogService.blogMain2(authUser.getId());
		
		model.addAttribute("authUser", authUser);
		model.addAttribute("list2", list2);
		return "blog/blog-admin-basic";
	}
	
	@Auth
	@RequestMapping("/setup")
	public String setup(@PathVariable String userId, @AuthUser UserVo authUser, @ModelAttribute BlogVo vo,
			@RequestParam(value="file") MultipartFile file) {
		if (!authUser.getId().equals(userId)) {
			return "redirect:/{userId}";
		}
		if (file.isEmpty()) {
			return "redirect:/{userId}/admin";
		}
		blogService.modify(authUser, vo, file);
		return "redirect:/{userId}/admin";
	}
	
	@Auth
	@RequestMapping("/category")
	public String category(@PathVariable String userId, Model model) {
		List<Map<String, Object>> list2 = blogService.blogMain2(userId);
		// 카테고리당 포스트수 구하는 쿼리는 먼저 group by 한 후 join을 해야한다
		List<Map<String, Object>> list3 = categoryService.categoryList(userId);
		
		model.addAttribute("userId", userId);
		model.addAttribute("list2", list2);
		model.addAttribute("list3", list3);
		return "blog/blog-admin-category";
 }

	@Auth
	@RequestMapping("/post")
	public String post(@PathVariable String userId, @AuthUser UserVo authUser, Model model) {
		List<Map<String, Object>> list2 = blogService.blogMain2(userId);
		
		model.addAttribute("userId", userId);
		model.addAttribute("list2", list2);
		return "blog/blog-admin-write";
	}
}
