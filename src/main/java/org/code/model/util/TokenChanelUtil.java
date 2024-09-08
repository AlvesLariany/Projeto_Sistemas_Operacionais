package org.code.model.util;

public class TokenChanelUtil {

    //armazena o canal que está sendo visualizado
    private static Long tokenChanel;
    public static void setToken(Long id) {
        tokenChanel = id;
    }

    public static Long getToken() {
        return tokenChanel;
    }
}
