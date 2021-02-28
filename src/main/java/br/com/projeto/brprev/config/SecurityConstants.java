package br.com.projeto.brprev.config;

import java.util.concurrent.TimeUnit;

public class SecurityConstants {
    //Authorization Bearer hashhashhash
    static final String SECRET = "Florescer";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final String SIGN_UP_URL = "/users/sign-up";
    static final long EXPIRATION_TIME = 86400000l;
}
