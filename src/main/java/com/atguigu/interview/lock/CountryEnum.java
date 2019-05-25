package com.atguigu.interview.lock;

/**
 * @author rociss
 * @version 1.0, on 15:28 2019/5/25.
 */

/*
枚举就像一个内存数据库表：
ONE(1,"齐","shcema".....)后续可以跟很多字段信息
 */
public enum  CountryEnum {
    /*

     */
    ONE(1,"齐"),
    /*

     */
    TWO(2,"楚"),
    /*

     */
    THREE(3,"燕"),
    FOUR(4,"赵"),
    FIVE(5,"魏"),
    SIX(6,"韩");

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    /*
    retCode
     */
    private Integer retCode;

    /*
    retMessage
     */
    private String  retMessage;


    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }
    
    public static CountryEnum forEachCountryEnum(int index){
        CountryEnum[] myArray = CountryEnum.values();
        for (CountryEnum element:myArray) {
            if (index == element.getRetCode()){
                return element;
            }
        }
        return null;
    }
}
