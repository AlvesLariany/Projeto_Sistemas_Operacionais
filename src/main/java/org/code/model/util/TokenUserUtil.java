package org.code.model.util;

public class TokenUserUtil {
    private static String tokenUser = null;

    public static void setUserToken(String token) {
        tokenUser = token;
    }

    public static String getUserToken() {
        return tokenUser;
    }

}
