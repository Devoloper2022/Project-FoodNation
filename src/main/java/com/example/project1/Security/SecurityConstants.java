package com.example.project1.Security;

public class SecurityConstants {
    public static final String SignUpUrls ="/api/auth/**";
    public static final String SECRET ="SecretKeyGenJWT";
    public static final String TOKEN_PREFIX ="Bearer ";
    public static final String HEADER_STRING ="Authorization";
    public static final String CONTENT_TYPE ="application/json";
    public static final long EXPIRATION_TIME=600_000;//100 min
    // 60_000 1min
    // 600_000 10min
    // 3_600_000 1 hour
    // 43_200_000 1 day

}
