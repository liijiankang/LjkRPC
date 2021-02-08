package org.rpc;

import lombok.Data;

import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * @author jiankang Li
 * @ClassName MethodParameter.java
 * @Description TODO
 * @createTime 2021年02月08日 10:55:00
 */
@Data
public class MethodParameter {
    String className;
    String methodName;
    Object[] arguments;
    Class<?>[] parameterTypes;

    public static MethodParameter convert(InputStream inputStream) {

        try {
            ObjectInputStream input = new ObjectInputStream(inputStream);
            String className = input.readUTF();
            String methodName = input.readUTF();
            Class<?>[] parameterTypes = (Class<?>[])input.readObject();
            Object[] arguments = (Object[])input.readObject();

            MethodParameter methodParameter = new MethodParameter();
            methodParameter.setClassName(className);
            methodParameter.setMethodName(methodName);
            methodParameter.setArguments(arguments);
            methodParameter.setParameterTypes(parameterTypes);

            return methodParameter;
        } catch (Exception e) {
            throw new RuntimeException("解析请求错误:" + e.getMessage());
        }
    }
}
