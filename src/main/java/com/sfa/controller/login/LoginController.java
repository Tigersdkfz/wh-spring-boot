package com.sfa.controller.login;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sfa.model.sys.UserEntity;
import com.sfa.service.login.LoginService;
import com.sfa.utils.Token;

@RestController
public class LoginController {


	@Resource(name = "loginServiceImpl")
	private LoginService loginService;

	@PostMapping("/login")
	public UserEntity loginAction(HttpServletRequest request, HttpServletResponse response,
			@RequestBody UserEntity userEntity) throws IOException {

		//**登录前的**//
		//**1.判断session中是否含有token信息如果含有则 抛出异常。2.如果不含有则走登录验证。**//
		if (Token.isTokenStringValid(DigestUtils.md5Hex(userEntity.getId() + ""), request.getSession())) {
			throw new UnapprovedClientAuthenticationException("账户已经登录过，请重新登录！");
		}
		else{//**登录验证**//
			String userCode = userEntity.getUserCode();
			String userPassword = userEntity.getUserPassword();
			if (userCode == null) {
				throw new UnapprovedClientAuthenticationException("请求时登录账户为空！");
			} else if (userPassword == null) {
				throw new UnapprovedClientAuthenticationException("请求时账户密码为空！");
			}
			
			UserEntity userData = loginService.queryUser(userEntity);
			if (userData == null) {//**验证登录的账户密码是否正确**//
				//**注销session**//
				Token.removeTokenString(DigestUtils.md5Hex(userEntity.getId() + ""), request.getSession());
				
				throw new UnapprovedClientAuthenticationException("账号密码不正确，请确认！");
			}
			
			//**验证成功后生成token 保存并返回在报文头中**//
			String token = DigestUtils.md5Hex(userEntity.getId() + "");
			Token.saveTokenString(token, request.getSession());
			response.setHeader("token", token);
			return userData;
		}
	}
}
