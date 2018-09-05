package com.edu.common.tool;

import com.edu.common.constant.LogLevel;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-9-4
 * Time: 下午4:17
 * To change this template use File | Settings | File Templates.
 */
public class CommonLog {
    public static void log(LogLevel lev,Class<?> getClass,String logInfo){
        Logger log = Logger.getLogger(getClass);
        if(lev.equals(LogLevel.ERR)){
            log.error(logInfo);
        }else{
            log.info(logInfo);
        }
    }
}
