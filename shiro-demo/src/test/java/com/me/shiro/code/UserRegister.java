package com.me.shiro.code;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 生成盐和加密密码
 * @author 清明
 *
 */
public class UserRegister {
    public static void main(String[] args) {
        String algorithmName = "md5";
        String username = "gqm";
        String password = "990219";
        String salt1 = username;
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 2;
        SimpleHash hash = new SimpleHash(algorithmName, password, salt1+salt2,hashIterations);
        System.out.println("密码："+hash);
        System.out.println("salt2:"+salt2);
    }
}
//密码：a42853d4f89daa0563b051f6c8723e67
//salt2:12069d28be356fef413d32ea7a85d0cc