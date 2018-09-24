package com.anish.myapplication.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anish.myapplication.dao.LoginDao;
import com.anish.myapplication.modal.LoginModal;
import com.anish.myapplication.services.LoginServices;

@Transactional
@Service
public class LoginServicesImpl implements LoginServices {

	@Autowired
	LoginDao loginDao;

	@Override
	public LoginModal valudateLogin(LoginModal loginModel) {
		// TODO Auto-generated method stub
		return loginDao.validateLoginUser(loginModel);
	}

}
