package com.edu.login.constant;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-8-24
 * Time: 上午10:53
 * To change this template use File | Settings | File Templates.
 */
public enum EventBusName {
   RESTCLIENT("edu.restful.api");

    private String desc;//中文描述
    /**
     * 私有构造,防止被外部调用
     * @param desc
     */
    private EventBusName(String desc){
        this.desc=desc;
    }

    /**
     * 定义方法,返回描述,跟常规类的定义没区别
     * @return
     */
    public String getDesc(){
        return desc;
    }



}
