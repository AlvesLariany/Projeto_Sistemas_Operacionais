package org.code.model.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
        public static String defineHash(String entry) {
            String stringInHash = null;
            try {
                MessageDigest algorithmSha256 = MessageDigest.getInstance("SHA-256");

                byte messageDigestHash[] = algorithmSha256.digest(entry.getBytes("UTF-8"));

                StringBuilder stringBuilder = new StringBuilder();

                for (byte itemByte : messageDigestHash) {
                    stringBuilder.append(String.format("%02X", 0xFF & itemByte));
                }

                stringInHash = stringBuilder.toString();

            } catch (NoSuchAlgorithmException | UnsupportedEncodingException error) {
                System.out.println("Erro ao criptografar: " + error.getMessage());
            }
            return stringInHash;
        }
}
