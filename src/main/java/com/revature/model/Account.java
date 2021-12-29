package com.revature.model;

import java.util.Objects;

public class Account {
private int id;
private float balance;

public Account() {
	super();
	// TODO Auto-generated constructor stub
}

public Account(int id, float balance) {
	super();
	this.id = id;
	this.balance = balance;
}


@Override
public String toString() {
	return "Account [id=" + id + ", balance=" + balance + "]";
}

@Override
public int hashCode() {
	return Objects.hash(balance, id);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Account other = (Account) obj;
	return Float.floatToIntBits(balance) == Float.floatToIntBits(other.balance) && id == other.id;
}

public Account(float balance) {
	super();
	this.balance = balance;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public float getBalance() {
	return balance;
}
public void setBalance(float balance) {
	this.balance = balance;
}
}
