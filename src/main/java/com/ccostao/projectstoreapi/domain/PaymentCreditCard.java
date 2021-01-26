package com.ccostao.projectstoreapi.domain;

import javax.persistence.Entity;

import com.ccostao.projectstoreapi.domain.enums.PaymentStatus;

@Entity
public class PaymentCreditCard extends Payment {
	
	private static final long serialVersionUID = 1L;

	private Integer installments;

	public PaymentCreditCard() {
	}

	public PaymentCreditCard(Integer id, PaymentStatus status, Order order, Integer installments) {
		super(id, status, order);
		this.installments = installments;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}
	
}
