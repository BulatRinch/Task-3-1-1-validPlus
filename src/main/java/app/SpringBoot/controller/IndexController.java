package app.SpringBoot.controller;

import app.SpringBoot.Start;
import app.SpringBoot.config.exception.LoginException;
import app.SpringBoot.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;


import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {
	private final AppService appService;
	private final Start start;

	@Autowired
	public IndexController(AppService appService, Start start) {
		this.appService = appService;
		this.start = start;

	}

	@GetMapping("")
	public String welcomePage(Model model, HttpSession session,
							  @SessionAttribute(required = false, name = "Authentication-Exception") LoginException authenticationException,
							  @SessionAttribute(required = false, name = "Authentication-Name") String authenticationName) {
		appService.authenticateOrLogout(model, session, authenticationException, authenticationName);

		return "index";
	}

}