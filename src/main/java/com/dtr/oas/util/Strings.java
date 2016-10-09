package com.dtr.oas.util;

import org.springframework.util.StringUtils;

/**
 * Created by safayat on 12/12/15.
 */
public class Strings extends StringUtils {

    public static boolean isNotEmpty(Object str){
        return !isEmpty(str);
    }
}
