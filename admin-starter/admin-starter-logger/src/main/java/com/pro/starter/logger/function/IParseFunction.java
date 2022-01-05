package com.pro.starter.logger.function;

/**
 * @author lijunping on 2022/1/5
 */
public interface IParseFunction {

    /**
     * executeBefore 函数代表了自定义函数是否在业务代码执行之前解析
     */
    default boolean executeBefore(){
        return false;
    }

    String functionName();

    String apply(String value);
}
