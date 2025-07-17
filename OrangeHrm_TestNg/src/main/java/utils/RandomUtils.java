package utils;

import java.security.SecureRandom;

public class RandomUtils
{
    private RandomUtils(){

    }
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomNumberIsString(int length){
        String textNumber = "0123456789";
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i=0;i<length;i++){
            stringBuilder.append(textNumber.charAt(random.nextInt(textNumber.length())));
        }
        return stringBuilder.toString();
    }

    public static String generateRandomString(int length){
        String text = "ABCDEFGHIJKLMNOPQURSTVWXYZ";
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i=0;i<length;i++){
            stringBuilder.append(text.charAt(random.nextInt(text.length())));
        }
        return stringBuilder.toString();
    }
}
