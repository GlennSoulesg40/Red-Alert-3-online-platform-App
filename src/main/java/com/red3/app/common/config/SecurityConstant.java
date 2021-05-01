package com.red3.app.common.config;

public interface SecurityConstant {

    /**
     * token分割
     */
    String TOKEN_SPLIT = "red3 ";

    /**
     * JWT签名加密key
     */
    String JWT_SIGN_KEY = "f!UR4kT!zZE&Xmp!";

    /**
     * token参数头
     */
    String HEADER = "accessToken";

    /**
     * 权限参数头
     */
    String AUTHORITIES = "ROLE_";

    /**
     * 用户选择JWT保存时间参数头
     */
    String SAVE_LOGIN = "saveLogin";

    /**
     * 交互token前缀key
     */
    String TOKEN_PRE = "TTYY_TOKEN_PRE:";

    /**
     * 用户token前缀key 单点登录使用
     */
    String USER_TOKEN = "TTYY_USER_TOKEN:";

    /**
     * 过期时间
     */
    long EXPIRATION_TIME = 1000*60*30;
}