package com.smile.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ${DESCRIPTION}
 *
 * @author: ayuan
 * @create: 2019-09-06 14:43
 */
public class SubjectLogProxy implements InvocationHandler {
    private Subject subject;

    public SubjectLogProxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = null;
        if("print".equals(method.getName())){
            System.out.println("print before,invoke from proxy");
            obj = method.invoke(subject, args);
            System.out.println("method:" + method);
            System.out.println("print after,invoke from proxy");
        }else{
            System.out.println("method:" + method);
            obj = method.invoke(subject, args);
        }
        return obj;
    }

}
