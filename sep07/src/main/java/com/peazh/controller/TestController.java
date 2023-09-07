package com.peazh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.peazh.entity.Test;
import com.peazh.service.TestService;

@Controller
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@GetMapping("/test")
	public String test(Model model) {// 데이터 베이스의 값을 저장하고 타임리프로 보내주는 역할
		List<Test> list = testService.list();
		model.addAttribute("list",list);
		return "test";
	}

}
