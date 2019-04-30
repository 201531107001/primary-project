                            缓存机制
Shiro 提供了类似于 Spring 的 Cache 抽象，即 Shiro 本身不实现 Cache，但是对 Cache 进行
了又抽象，方便更换不同的底层 Cache 实现。 

Shiro  提供的 的 Cache ：
public interface Cache<K, V> {
    //根据 Key 获取缓存中的值
    public V get(K key) throws CacheException;
    //往缓存中放入 key-value，返回缓存中之前的值
    public V put(K key, V value) throws CacheException;
    //移除缓存中 key 对应的值，返回该值
    public V remove(K key) throws CacheException;
    //清空整个缓存
    public void clear() throws CacheException;
    //返回缓存大小
    public int size();
    //获取缓存中所有的 key
    public Set<K> keys();
    //获取缓存中所有的 value
    public Collection<V> values();
}
Shiro  提供的 的 CacheManager  接口:
public interface CacheManager {
    //根据缓存名字获取一个 Cache
    public <K, V> Cache<K, V> getCache(String name) throws CacheException;
}
Shiro 还 还 提供了 了 CacheManagerAware 用 用入 于注入 CacheManager ：
public interface CacheManagerAware {
    //注入 CacheManager
    void setCacheManager(CacheManager cacheManager);
}
Shiro 内部相应的组件（DefaultSecurityManager）会自动检测相应的对象（如 Realm）是否
实现了 CacheManagerAware 并自动注入相应的 CacheManager。

                Realm 缓存
Shiro 提供了 CachingRealm，其实现了 CacheManagerAware 接口，提供了缓存的一些基础
实现；另外 AuthenticatingRealm 及 AuthorizingRealm 分别提供了对 AuthenticationInfo 和
AuthorizationInfo 信息的缓存。

ini配置：
    userRealm=com.github.zhangkaitao.shiro.chapter11.realm.UserRealm
    userRealm.credentialsMatcher=$credentialsMatcher
    userRealm.cachingEnabled=true
    userRealm.authenticationCachingEnabled=true
    userRealm.authenticationCacheName=authenticationCache
    userRealm.authorizationCachingEnabled=true
    userRealm.authorizationCacheName=authorizationCache
    securityManager.realms=$userRealm
    cacheManager=org.apache.shiro.cache.ehcache.EhCacheManager
    cacheManager.cacheManagerConfigFile=classpath:shiro-ehcache.xml
    securityManager.cacheManager=$cacheManager

userRealm.cachingEnabled：启用缓存，默认 false；
userRealm.authenticationCachingEnabled：启用身份验证缓存，即缓存 AuthenticationInfo 信息，默认 false；
userRealm.authenticationCacheName：缓存 AuthenticationInfo 信息的缓存名称；
userRealm. authorizationCachingEnabled：启用授权缓存，即缓存 AuthorizationInfo 信息，默认 false；
userRealm. authorizationCacheName：缓存 AuthorizationInfo 信息的缓存名称；
cacheManager：缓存管理器，此处使用 EhCacheManager，即 Ehcache 实现，需要导入相应的 Ehcache 依赖，









                          