Shiro 提供了 JSTL 标签用于在 JSP/GSP 页面进行权限控制，如根据登录用户显示相应的页
面按钮。
导 入 标签库<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

guest标签:用户没有身份验证时显示相应信息，即游客访问信息
<shiro:guest>
欢迎游客访问，<a href="${pageContext.request.contextPath}/login.jsp">登录</a>
</shiro:guest>

user标签:用户已经身份验证/记住我登录后显示相应的信息
<shiro:user>
欢迎[<shiro:principal/>]登录，<a href="${pageContext.request.contextPath}/logout">退出</a>
</shiro:user>

authenticated标签:用户已经身份验证通过，即 Subject.login 登录成功，不是记住我登录的
<shiro:authenticated>
用户[<shiro:principal/>]已身份验证通过
</shiro:authenticated>

notAuthenticated标签:用户已经身份验证通过，即没有调用 Subject.login 进行登录，包括记住我自动登录的也属于未进行身份验证。
<shiro:notAuthenticated>
未身份验证（包括记住我）
</shiro:notAuthenticated>

principal标签：显示用户身份信息
<shiro: principal/>

hasRole 标签：如果当前 Subject 有角色将显示 body 体内容
<shiro:hasRole name="admin">
用户[<shiro:principal/>]拥有角色 admin<br/>
</shiro:hasRole>

hasAnyRoles标签:如果当前 Subject 有任意一个角色（或的关系）将显示 body 体内容
<shiro:hasAnyRoles name="admin,user">
用户[<shiro:principal/>]拥有角色 admin 或 user<br/>
</shiro:hasAnyRoles>

lacksRole标签：如果当前 Subject 没有角色将显示 body 体内容
<shiro:lacksRole name="abc">
用户[<shiro:principal/>]没有角色 abc<br/>
</shiro:lacksRole>

hasPermission 标签
<shiro:hasPermission name="user:create">
用户[<shiro:principal/>]拥有权限 user:create<br/>
</shiro:hasPermission>

lacksPermission 标签
<shiro:lacksPermission name="org:create">
用户[<shiro:principal/>]没有权限 org:create<br/>
</shiro:lacksPermission>




