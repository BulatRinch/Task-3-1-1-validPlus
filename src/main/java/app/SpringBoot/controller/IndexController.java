package app.SpringBoot.controller;


import app.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



import javax.servlet.http.HttpSession;

@Controller

@RequestMapping("/11")
public class IndexController {

	@PostMapping("/")
	public String welcomePage() {
		return "index";
	}

}