package com.revature.designpatterns.practice;//package com.revature.practice;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//public class Challenge2Class {
//
//	int count = 0;
//	int testCount = 0;
//
//
//
//	protected int getArgs(int...varargs) {
//		for(int a : varargs){
//
//			count+= a;
//		}
//		return count;
//	}
//	int testingCode(int...varargs1) {
//
//		for(int a : varargs1){
//
//			this.testCount+= a;
//		}
//		return this.testCount;
//	}
//	@Test
//		public void testCode() {
//		this.testCount =	testingCode(1,2);
//		Assert.assertTrue("Something's off. The count tester returned a value less than or equal"
//
//				+ " to zero. Current count: " + this.testCount + ". revise code.", this.testCount > 0);
//
//	}
//}
