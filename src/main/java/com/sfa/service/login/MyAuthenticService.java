package com.sfa.service.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestBody;

import com.sfa.model.sys.UserEntity;

public interface MyAuthenticService {

	/**
	 * 生成token
	 * 
	 * @param userEntity
	 * @return
	 */
	OAuth2AccessToken createAccessToken(String userId ,String userName);

	

}
