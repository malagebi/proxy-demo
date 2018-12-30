package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {
    static interface Subject{
        void sayHi();
        void sayHello();
    }

    static class SubjectImpl implements Subject{

        @Override
        public void sayHi() {
            System.out.println("hi");
        }

        @Override
        public void sayHello() {
            System.out.println("hello");
        }
    }

    static class ProxyInvocationHandler implements InvocationHandler {
        private Subject target;
        public ProxyInvocationHandler(Subject target) {
            this.target=target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(method.getName());
            System.out.print("say:");
            Object result=method.invoke(target, args);
            System.out.println("haha");
            return result;
        }

    }

    public static void main(String[] args) {
        Subject subject=new SubjectImpl();
        Subject subjectProxy=(Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), subject.getClass().getInterfaces(), new ProxyInvocationHandler(subject));
        subjectProxy.sayHi();
        subjectProxy.sayHello();

    }
}

