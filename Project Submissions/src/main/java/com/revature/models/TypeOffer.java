package com.revature.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "offers")
public class TypeOffer {
	@Id
	@Column(name = "offer_id")
	@GeneratedValue(generator = "offer_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1, name = "offer_id_seq")
	private int offerId;
	@Column(name = "fullName")
	private String fullName;
	@Column(name = "amount", columnDefinition = "NUMERIC")
	private double amount;
	@Column(name = "status")
	private String status;
	@Column(name = "message")
	private String message;
	
	
	
	
public TypeOffer( String fullName, double amount, String status) {
	this.offerId = offerId;
	this.fullName = fullName;
	this.amount = amount;
	this.message = message;
	this.status = status;
}









public int retreiveOfferId() {
	return offerId;
}

public void establishOfferId(int offerId) {
	this.offerId = offerId;
}

public double retreiveAmount() {
	return amount;
}

public void establishAmount(double amount) {
	this.amount = amount;
}

public String retreiveMessage() {
	return message;
}

public void establishMessage(String message) {
	this.message = message;
}

public String retreiveFullName() {
	return fullName;
}

public void establishFullName(String fullName) {
	this.fullName = fullName;
}

public String retreiveStatus() {
	return status;
}

public void establishStatus(String status) {
	this.status = status;
}


@Override
public int hashCode() {
    return Objects.hash(offerId,status,fullName,message,amount);
}

@Override
public String toString() {
    return "{\"TypeOffer\":{"
            + "\"offerId\":\"" + offerId + "\""
            + ", \"status\":\"" + status + "\""
             + ", \"fullName\":\"" + fullName + "\""
              + ", \"message\":\"" + message + "\""
               + ", \"amount\":\"" + amount + "\""
              
            + "}}";
}


@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TypeOffer)) return false;
  TypeOffer offer = (TypeOffer) o;
    return Objects.equals(offerId, offer.offerId) && Objects.equals(status, offer.status) && Objects.equals(amount, offer.amount
    		) && Objects.equals(fullName, offer.fullName) && Objects.equals(message, offer.message);
}


}
