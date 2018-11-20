package com.anish.myapplication.dao;

import com.anish.myapplication.modal.LoginModal;

public interface LoginDao {
	LoginModal validateLoginUser(LoginModal loginModel);
	LoginModal addLoginUser(LoginModal loginModel);
}
