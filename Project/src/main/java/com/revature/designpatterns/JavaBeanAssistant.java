package com.revature.designpatterns;

public class JavaBeanAssistant {
    public static void main(String[] args) {
        JavaBean javaBean = new JavaBean();
        javaBean.getFieldone();
        int valueofFieldOne = javaBean.getFieldone();
        javaBean.setFieldone(20);
    }
}
