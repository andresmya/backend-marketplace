package com.andresmya.backendmarketplace.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordUtil {

    public static boolean passwordIsStrong(String password){
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,})";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(password);
        return m.find();
    }
}
