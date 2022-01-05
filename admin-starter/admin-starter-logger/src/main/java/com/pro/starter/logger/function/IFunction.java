package com.pro.starter.logger.function;

/**
 * @author lijunping on 2022/1/5
 */
public interface IFunction {

    boolean beforeFunction(String functionName);

    String apply(String functionName, String value);
}
