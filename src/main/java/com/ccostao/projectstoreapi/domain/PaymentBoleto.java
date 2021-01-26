package com.ccostao.projectstoreapi.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.ccostao.projectstoreapi.domain.enums.PaymentStatus;

@Entity
public class PaymentBoleto extends Payment {
	
	private static final long serialVersionUID = 1L;

	private Date dueDate;
	private Date payDate;

	public PaymentBoleto() {
	}

	public PaymentBoleto(Integer id, PaymentStatus status, Order order, Date dueDate, Date payDate) {
		super(id, status, order);
		this.payDate = payDate;
		this.dueDate = dueDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDateo(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}	
}
