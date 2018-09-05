package com.edu.login.constant;

import com.edu.login.cache.CatchDto;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-8-30
 * Time: 上午11:42
 * To change this template use File | Settings | File Templates.
 */
public enum EduApiUrl {
    REGISTER_INS(CatchDto.API_PATH + "/api/edu/e100101"),  //注册接口
    REGISTER_SELECT_NICKNAME(CatchDto.API_PATH + "/api/edu/e100102"),  //根据昵称查询用户
    REGISTER_SELECT_PSAAWORD(CatchDto.API_PATH + "/api/edu/e100103");  //根据昵称查询用户


    private String url;//中文描述
    /**
     * 私有构造,防止被外部调用
     * @param url
     */
    private EduApiUrl(String url){
        this.url=url;
    }

    /**
     * 定义方法,返回描述,跟常规类的定义没区别
     * @return
     */
    public String getUrl(){
        return url;
    }
}
