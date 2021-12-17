package com.revature.designpatterns;

import java.io.Serializable;
import java.util.Objects;

/*
A JavaBean is a Java class that follows a standard design pattern. A design pattern is a convention;
note that frameworks that you rely on (as well as other developers) generally exoected for you to follow
 The design features of a Java Bean include:
 -private fields
 - a no args constructor
 -A constructor using the fields
 -a toString method (overridden from the Object) class
 getters and setter methods (accessors and mutators)
 -A hashCode and equals method (overriden from the Object class)
 -Technically, a JavaBean should implement Serializable*/
public class JavaBean implements Serializable {
    private int fieldone = 20;
    private boolean fieldTwo;

    public JavaBean() {
        super();
    }

    public int getFieldone (){
        return fieldone;
    }

    /*
    You typically want to abstract this sanitation of the data out of this method, but the point still
    remains: setters and getters allow for indirect access. This is safer than direct access as you can see below as
    it's not possible to ever set fieldOne to a value that is less than 0.
     */
    public void setFieldone(int fieldone) {
        if(fieldone < 0) return;
        this.fieldone = fieldone;
    }

    public JavaBean(int fieldOne, boolean fieldTwo) {
        this.fieldone = fieldOne;
        this.fieldTwo = fieldTwo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JavaBean javaBean = (JavaBean) o;
        return fieldone == javaBean.fieldone && fieldTwo == javaBean.fieldTwo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldone, fieldTwo);
    }

    @Override
    public String toString() {
        return "JavaBean{" + "fieldone=" + fieldone + ", fieldTwo=" + fieldTwo + '}';
    }
}

/*
We've made many POJOs (Plain Old Java Objects) over the last couple of weeks
 */
class POJO {
    private int fieldOne;
    private boolean fieldTwo;

    public POJO() {

    }
}
