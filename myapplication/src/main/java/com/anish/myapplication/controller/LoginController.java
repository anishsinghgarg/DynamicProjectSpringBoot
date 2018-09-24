package com.anish.myapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.anish.myapplication.modal.LoginModal;
import com.anish.myapplication.services.LoginServices;

@Controller
public class LoginController {

	@Autowired
	LoginServices loginServices;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		System.out.println("Welcome :-)");

		return "welcome";
	}

	@RequestMapping(value = "/validateUser", method = RequestMethod.POST)
	public ModelAndView validateUser(LoginModal model) {
		String viewJsp = "welcome";

		model = loginServices.valudateLogin(model);
		// System.out.println("User Name is ::" + model.getUserName());
		if (model != null)
			viewJsp = "home";
		return new ModelAndView(viewJsp, "model", model);
	}
}
