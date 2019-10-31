package com.jjson.common.log.util;

import com.jjson.common.exception.instance.CommonBizException;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author jiangjunshen
 * @date 9:21 AM 2019/10/31
 */

public class LogUtils {

    private static final int STACK_LIMIT = 10;

    private static final String JDK_PACKAGE = "java";
    
    private static final String PROJECT_PACKAGE = "com.jjson";
    
    public String getImportantStack(CommonBizException e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        for (int i = 0; i < STACK_LIMIT; i++) {
            writer.println("\t" + stackTrace[i]);
        }

        for (int i = STACK_LIMIT; i < stackTrace.length; i++) {
            StackTraceElement element = stackTrace[i];
            if (element.getClassName().startsWith(JDK_PACKAGE)
                    || element.getClassName().startsWith(PROJECT_PACKAGE)) {
                writer.println("\t" + element);
            } else {
                writer.println("\t" + "...omit");
            }
        }
        return stringWriter.toString();
    }
}
