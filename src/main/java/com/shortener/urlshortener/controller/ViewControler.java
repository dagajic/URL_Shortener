package com.shortener.urlshortener.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewControler {
	
	@Value("${spring.application.name}")
    String applicationName;

	@GetMapping("/help")
	public String helpPage(Model model) {
		model.addAttribute("applicationName", applicationName);
		return "help";
	}
}
