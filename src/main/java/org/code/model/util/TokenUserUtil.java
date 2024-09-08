package org.code.model.util;

public class TokenUserUtil {
    private static String tokenUser = null;
    private static String currentemail = null;

    //armazena o email e o email criptografado do usuário logado

    public static void setUserToken(String token) {
        tokenUser = token;
    }

    public static String getUserToken() {
        return tokenUser;
    }

    public static String getCurrentemail() {
        return currentemail;
    }

    public static void setCurrentemail(String currentemail) {
        TokenUserUtil.currentemail = currentemail;
    }
}
