package com.pro.starter.logger.function.support;

import com.pro.starter.logger.factory.ParseFunctionFactory;
import com.pro.starter.logger.function.IFunction;
import com.pro.starter.logger.function.IParseFunction;

/**
 * DefaultFunction 的逻辑就是根据传入的函数名称 functionName 找到对应的 IParseFunction，
 *
 * 然后把参数传入到 IParseFunction 的 apply 方法上最后返回函数的值。
 *
 * @author lijunping on 2022/1/5
 */
public class DefaultFunction implements IFunction {

    private final ParseFunctionFactory parseFunctionFactory;

    public DefaultFunction(ParseFunctionFactory parseFunctionFactory) {
        this.parseFunctionFactory = parseFunctionFactory;
    }

    @Override
    public String apply(String functionName, String value) {
        IParseFunction function = parseFunctionFactory.getFunction(functionName);
        if (function == null) {
            return value;
        }
        return function.apply(value);
    }

    @Override
    public boolean beforeFunction(String functionName) {
        return parseFunctionFactory.isBeforeFunction(functionName);
    }
}
