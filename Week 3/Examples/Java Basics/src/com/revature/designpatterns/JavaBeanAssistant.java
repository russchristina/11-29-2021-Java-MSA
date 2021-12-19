package com.revature.designpatterns;

public class JavaBeanAssistant {

	public static void main(String[] args) {
		
		JavaBean javaBean = new JavaBean();
		
		javaBean.setFieldOne(-89);
		
		System.out.println(javaBean.getFieldOne());
		
		
	}
}
