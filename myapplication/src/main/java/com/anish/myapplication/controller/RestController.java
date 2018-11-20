package com.anish.myapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.anish.myapplication.modal.LoginModal;
import com.anish.myapplication.services.LoginServices;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

	@Autowired
	LoginServices loginServices;

	@RequestMapping(value = "/user/{userName}/{password}", method = RequestMethod.GET, consumes = "application/json")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public LoginModal getValidateUser(@PathVariable("userName") String userName,
			@PathVariable("password") String password) {
		LoginModal model = new LoginModal();
		model.setUserName(userName);
		model.setPassword(password);

		model = loginServices.validateLogin(model);
		return model;
	}

	@RequestMapping(value = "/user/", method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public LoginModal addValidateUser(@RequestBody LoginModal model) {
		System.out.println("A");
		return loginServices.addNewUser(model);
	}
}
