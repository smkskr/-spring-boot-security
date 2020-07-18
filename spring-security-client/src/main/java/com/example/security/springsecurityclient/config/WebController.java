package com.example.security.springsecurityclient.config;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	@RequestMapping("/secure")
	public String securedPage(Model model, Principal principal) {
		return "secure";
	}

	@RequestMapping("/")
	public String index(Model model, Principal principal) {
		return "index";
	}

}
