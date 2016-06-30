package utils;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

/**
 * Created by Vlad on 24.06.2016.
 */

public class Utils {
    private static final Logger log = Logger.getLogger(Utils.class);

    public static String md5Custom(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(toUTF8Request(st).getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            log.error("Cannot create md5 hex"+e.getMessage());
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }

    public static String toUTF8Request(String st){
        byte[] bytes = st.getBytes(StandardCharsets.ISO_8859_1);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static long getCurrentTimeLong() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime().getTime();
    }

    public static String attributeFormat(String st){

        if(st.length()==2||st.length()==3) {
            return st.toUpperCase();
        }

        String letter = st.substring(0, 1).toUpperCase();
        String firstCapLetter = letter  + st.substring(1);

        if(firstCapLetter.contains("_")) {
            return firstCapLetter.replace("_"," ");
        }

        return firstCapLetter;
    }
}
