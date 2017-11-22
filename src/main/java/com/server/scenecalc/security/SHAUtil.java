package com.server.scenecalc.security;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by linjuntan on 2017/10/29.
 * email: ljt1343@gmail.com
 */
public class SHAUtil {
    private final String salt = "server!@#";

    public String encode(String code) {
        if (code != null && !code.equals("")) {
            return DigestUtils.sha1Hex(DigestUtils.sha1Hex(code) + salt);
        }
        return null;
    }
}
