package com.personmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class HomePageController {
	
	public String goToHomePage() {
		return "redirect:/index.html";
	}

}
