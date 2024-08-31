package org.code.gui.util;

public class TokenUserUtil {
    private static String tokenUser = null;

    public static void setUserToken(String token) {
        tokenUser = token;
    }

    public static String getUserToken() {
        return tokenUser;
    }

}
