package com.sfa.service.impl.login;



import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sfa.dao.UserDao;
import com.sfa.model.sys.UserEntity;
import com.sfa.service.login.LoginService;

@Service(value = "loginServiceImpl")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserDao userDao;
	
	

	@Override
	public UserEntity queryUser(UserEntity userEntity) {
		String password=DigestUtils.md5Hex(userEntity.getUserPassword() + "");
		System.out.println("========="+password+"==========");
		userEntity.setUserPassword(password);
		UserEntity userdata = userDao.queryUserByPassword(userEntity);
		return userdata;
	}
}
