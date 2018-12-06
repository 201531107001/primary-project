package com.me.shiro.test3;

import java.util.Arrays;
import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * RolePermissionResolver 用于根据角色字符串来解析得到权限集合
 * 此处的实现很简单，如果用户拥有 role1，那么就返回一个“update*”的权限
 * @author 清明
 *
 */
public class MyRolePermissionResolver implements RolePermissionResolver {
	@Override
	public Collection<Permission> resolvePermissionsInRole(String roleString) {
		if ("role1".equals(roleString)) {
			return Arrays.asList((Permission) new WildcardPermission("update"));
		}
		return null;
	}
}
