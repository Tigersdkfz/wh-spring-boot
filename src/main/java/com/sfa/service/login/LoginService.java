package com.sfa.service.login;

import java.util.Map;

import com.sfa.model.sys.UserEntity;

public interface LoginService {

	/**
	 * 通过账号密码查询用户
	 * 
	 * @param userEntity
	 * @return
	 */
	UserEntity queryUser(UserEntity userEntity);

	

}
