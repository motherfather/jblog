package com.bit2016.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2016.dto.JSONResult;
import com.bit2016.jblog.service.UserService;

@Controller("userAPIController")
@RequestMapping("/user/api")
public class UserController {

	@Autowired
	UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkid")
	public JSONResult checkid(@RequestParam(value="id", required=true, defaultValue="") String id) {
		boolean result = userService.checkId(id);
		return JSONResult.success(result ? "exist" : "not exist");
	}
}
