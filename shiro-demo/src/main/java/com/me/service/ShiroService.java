package com.me.service;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.repository.ShiroRepository;

@Service
public class ShiroService {
	private static final Logger logger = LoggerFactory.getLogger(ShiroService.class);
	
	@Autowired
	private ShiroRepository userRepository;

	public void doLogin(String username, String password) throws Exception {
		logger.info("用户：{}登录，密码为：{}",username,password);
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			token.setRememberMe(true);// 是否记住用户
			try {
				currentUser.login(token);// 执行登录
			} catch (UnknownAccountException uae) {
				throw new Exception("账户不存在");
			} catch (IncorrectCredentialsException ice) {
				throw new Exception("密码不正确");
			} catch (LockedAccountException lae) {
				throw new Exception("用户被锁定了 ");
			} catch (AuthenticationException ae) {
				ae.printStackTrace();
				throw new Exception("未知错误");
			}
		}
	}

	/**
	 *  根据用户名查询密码     
	 */
	public String getPasswordByUserName(String username) {
		return userRepository.getPasswordByUsername(username);
	}

	/**
	 *  查询用户所有权限     
	 */
	public List<String> getPermissionByUserName(String username) {
		return userRepository.getPermissionByUserName(username);
	}
}
