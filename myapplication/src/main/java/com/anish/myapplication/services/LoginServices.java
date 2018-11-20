package com.anish.myapplication.services;

import com.anish.myapplication.modal.LoginModal;

public interface LoginServices {
	LoginModal validateLogin(LoginModal loginModel);
	LoginModal addNewUser(LoginModal loginModel);
}
