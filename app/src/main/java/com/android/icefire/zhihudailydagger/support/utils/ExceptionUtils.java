package com.android.icefire.zhihudailydagger.support.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by Administrator on 2015/9/29.
 */
public class ExceptionUtils {
    /**
     * 获取异常详细信息
     * @param arg1
     * @return
     */
    public static String getErrorInfo(Throwable arg1){
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        arg1.printStackTrace(pw);
        pw.close();
        String error= writer.toString();
        return error;
    }
}
