package com.me.shiro.code;

import java.util.Scanner;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * 用户名+随机数生成盐
 * @author 清明
 *
 */
public class JdbcMain {
    public static void main(String[] args) {
        Factory<SecurityManager> factory = 
                new IniSecurityManagerFactory("classpath:shiro-jdbc-hashedCredentialsMatcher.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        
        Scanner input = new Scanner(System.in);
        Subject subject = SecurityUtils.getSubject();
        
        while(true) {
            System.out.print("输入密码:");
            String password = input.nextLine();
            if(password.equals("exist")) {
                break;
            }
            
            UsernamePasswordToken token = 
                    new UsernamePasswordToken("gqm", password);
            try {
                subject.login(token);
            } catch (IncorrectCredentialsException e) {
                System.out.println("密码错误");
            } catch (ExcessiveAttemptsException e) {
                System.out.println("已错误登录5次");
            }
            
            if(subject.isAuthenticated()) {
                System.out.println("登录成功");
            }
        }
        
        subject.logout();
    }
}
