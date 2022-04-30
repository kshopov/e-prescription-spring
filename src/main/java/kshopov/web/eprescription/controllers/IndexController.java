package kshopov.web.eprescription.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class IndexController implements WebMvcConfigurer{
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("successful_registration").setViewName("index/successful_registration");
	}
	
	@GetMapping({"", "/", "index", "index.html"})
	public String index() {
		return "index/index";
	}
	
	@GetMapping("/login")
	public String list() {
		return "loginPage";
	}
	
}
