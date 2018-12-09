package com.me.shiro.authorize;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

public class BitAndWildPermissionResolver implements PermissionResolver {
	@Override
	public Permission resolvePermission(String permissionString) {
		System.out.println("resolvePermission:"+permissionString);
		// 以加号开头的权限为BitPermission
		if (permissionString.startsWith("+")) {
			return new BitPermission(permissionString);
		}
		return new WildcardPermission(permissionString);
	}
}
