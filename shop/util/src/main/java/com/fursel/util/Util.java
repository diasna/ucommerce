package com.fursel.util;

public class Util {

    public static final String getSubDomain(String domain) {
        domain.replace("www.", "");
        return domain.substring(0, domain.indexOf("."));
    }
}
