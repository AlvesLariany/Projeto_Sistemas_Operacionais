package org.code.model.util;

public class TokenChanelUtil {
    private static Long tokenChanel;
    public static void setToken(Long id) {
        tokenChanel = id;
    }

    public static Long getToken() {
        return tokenChanel;
    }
}
