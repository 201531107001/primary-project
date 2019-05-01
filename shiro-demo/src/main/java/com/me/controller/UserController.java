package com.me.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;

import com.me.service.ShiroService;

@Controller
public class UserController {

	@Autowired
	private ShiroService shiroService;

	@GetMapping("/gologin")
	public String goLogin() {
		return "login";
	}

	@PostMapping("/login")
	public String login(String username, String password ,Model model) {
		try {
			shiroService.doLogin(username, password);
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return "error"; 
		}
		return "pIndex";
	}

	@GetMapping("/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 *  模拟不同的请求，在配置文件中对请求进行权限拦截     
	 */
	@RequestMapping("/do{act}")
	public String get(@PathVariable String act , Model model) {
		//简化代码，省略数据库操作
		//通过页面上显示的信息查看请求是否被拦截
		model.addAttribute("page", act);
		return act;
	}
	
	@RequiresAuthentication
	@RequiresPermissions("add")
	@RequestMapping("/hello")
	public String hello() {
	    return "hello";
	}
	
	/**
	 * 异常处理器
	 * @param request
	 * @param e
	 * @param model
	 * @return
	 */
	@ExceptionHandler({AuthorizationException.class})
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public  String  processUnauthenticatedException(NativeWebRequest  request,
	        AuthorizationException e,Model model) {
	    System.out.println(e.getClass().getName());
	    if(e instanceof UnauthenticatedException) {
	        return "login";
	    }
	    model.addAttribute("msg", e.getMessage());
    	return "error";
	}
}
