                                            编码/解码
Shiro 提供了 base64 和 16 进制字符串编码/解码的 API 支持，方便一些编码解码操作。Shiro
内部的一些数据的存储/表示都使用了 base64 和 16 进制字符串

进行 base64 编码/解码操作
String str = "hello";
String base64Encoded = Base64.encodeToString(str.getBytes());
String str2 = Base64.decodeToString(base64Encoded);
Assert.assertEquals(str, str2);

进行 16 进制字符串编码/解码操作
String str = "hello";
String password = Hex.encodeToString(str.getBytes());
String str2 = new String(Hex.decode(password.getBytes()));
Assert.assertEquals(str, str2);

                散列算法
散列算法一般用于生成数据的摘要信息，是一种不可逆的算法，一般适合存储密码之类的
数据，常见的散列算法如 MD5、SHA 等。一般进行散列时最好提供一个 salt（盐），比如
加密密码“admin”，产生的散列值是“21232f297a57a5a743894a0e4a801fc3”，可以到一
些 md5 解密网站很容易的通过散列值得到密码“admin”，即如果直接对密码进行散列相
对来说破解更容易，此时我们可以加一些只有系统知道的干扰数据，如用户名和 ID（即盐）；
这样散列的对象是“密码+用户名+ID”，这样生成的散列值相对来说更难破解。

String str = "hello";
String salt = "123";
String md5 = new Md5Hash(str, salt).toString();//还可以转换为 toBase64()/toHex()
通过盐“123”MD5 散列“hello”。另外散列时还可以指定散列次数，如 2 次表
示：md5(md5(str))：“new Md5Hash(str, salt, 2).toString()”

使用 SHA256 算法生成相应的散列数据，另外还有如 SHA1、SHA512 算法
String str = "hello";
String salt = "123";
String sha1 = new Sha256Hash(str, salt).toString();

Shiro 还提供了通用的散列支持：
String str = "hello";
String salt = "123";
//内部使用 MessageDigest
String simpleHash = new SimpleHash("SHA-1", str, salt).toString();
通过调用 SimpleHash 时指定散列算法，其内部使用了 Java 的 MessageDigest 实现。

为了方便使用，Shiro 提供了 HashService，默认提供了 DefaultHashService 实现。
DefaultHashService hashService = new DefaultHashService(); //默认算法 SHA-512
hashService.setHashAlgorithmName("SHA-512");
hashService.setPrivateSalt(new SimpleByteSource("123")); //私盐，默认无
hashService.setGeneratePublicSalt(true);//是否生成公盐，默认 false
hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成
公盐。默认就这个
hashService.setHashIterations(1); //生成 Hash 值的迭代次数
HashRequest request = new HashRequest.Builder()
    .setAlgorithmName("MD5")
    .setSource(ByteSource.Util.bytes("hello"))
    .setSalt(ByteSource.Util.bytes("123"))
    .setIterations(2).build();
String hex = hashService.computeHash(request).toHex();
1、首先创建一个 DefaultHashService，默认使用 SHA-512 算法；
2、可以通过 hashAlgorithmName 属性修改算法；
3、可以通过 privateSalt 设置一个私盐，其在散列时自动与用户传入的公盐混合产生一个新盐；
4、可以通过 generatePublicSalt 属性在用户没有传入公盐的情况下是否生成公盐；
5、可以设置 randomNumberGenerator 用于生成公盐；
6、可以设置 hashIterations 属性来修改默认加密迭代次数；
7、需要构建一个 HashRequest，传入算法、数据、公盐、迭代次数。

                                            加密/解密
Shiro还提供对称式加密/解密算法的支持,如 AES、Blowfish 等；当前还没有提供对非对称加密/解密算法支持
AES 算法实现：
AesCipherService aesCipherService = new AesCipherService();
aesCipherService.setKeySize(128); //设置 key 长度
//生成 key
Key key = aesCipherService.generateNewKey();
String text = "hello";
//加密
String encrptText =
aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
//解密
String text2 =
new String(aesCipherService.decrypt(Hex.decode(encrptText),key.getEncoded()).getBytes());
Assert.assertEquals(text, text2);

PasswordService/CredentialsMatcher
Shiro 提供了 PasswordService 及 CredentialsMatcher 用于提供加密密码及验证密码服务。
public interface PasswordService {
    //输入明文密码得到密文密码
    String encryptPassword(Object plaintextPassword) throws IllegalArgumentException;
}
public interface CredentialsMatcher {
    //匹配用户输入的 token 的凭证（未加密）与系统提供的凭证（已加密）
    boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info);
}
Shiro 默认提供了 PasswordService 实现 DefaultPasswordService；CredentialsMatcher 实现
PasswordMatcher 及 HashedCredentialsMatcher（更强大）。
DefaultPasswordService  配合 PasswordMatcher 实 实 现 简单的密码加密与验证 服务
1、定义 Realm（com.github.zhangkaitao.shiro.chapter5.hash.realm.MyRealm）
    public class MyRealm extends AuthorizingRealm {
        private PasswordService passwordService;
        public void setPasswordService(PasswordService passwordService) {
            this.passwordService = passwordService;
        }
        //省略 doGetAuthorizationInfo，具体看代码
        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws
            AuthenticationException {
            return new SimpleAuthenticationInfo(
                "wu",
                passwordService.encryptPassword("123"),
                getName());
        }
    }
    为了方便，直接注入一个 passwordService 来加密密码，实际使用时需要在 Service 层使用passwordService 加密密码并存到数据库


