/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Random;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author Dong Long
 */
public class Util {

    public Util() {
    }

    public static String randomFileName(int countChars) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < countChars) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
    
    public static Date getDate(String dateStr) {
        try {
            return formatDate(dateStr);
        } catch (Exception e) {
            return null;
        }
    }
    public static Timestamp getTimestampFromDate(Date date) {
        return new Timestamp(date.getTime());
    }

    public static String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex);
    }

    public static Date formatDate(String dateStr) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = formatter.parse(dateStr);
        return new Date(date.getTime());
    }

    public static String getDateAsString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyymmdd");
        return formatter.format(date);
    }

    public static Timestamp formatDateTimestamp(String dateStr) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = formatter.parse(dateStr);
        return new Timestamp(date.getTime());
    }

    public static String displayDate(Timestamp time) {
        java.util.Date date = new java.util.Date(time.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        return formatter.format(date);
    }

    public static String encode(String key, String data) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
    }

    public static int getInt(String intValue, int defaultValue, int min) {
        int valueReturn = defaultValue;
        try {
            valueReturn = Integer.parseInt(intValue);
            if (valueReturn < min) {
                throw new Exception();
            }
        } catch (Exception e) {
            valueReturn = defaultValue;
        }
        return valueReturn;
    }
}
