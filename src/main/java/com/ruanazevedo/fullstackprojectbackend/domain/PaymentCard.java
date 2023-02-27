package com.ruanazevedo.fullstackprojectbackend.domain;

import javax.persistence.Entity;

import com.ruanazevedo.fullstackprojectbackend.domain.enums.StatePayment;

@Entity
public class PaymentCard extends Payment {

	private static final long serialVersionUID = 1L;
	
	private Integer qtdInstallments;
	
	public PaymentCard() {}

	public PaymentCard(Integer id, StatePayment paymentType, Order order, Integer qtdInstallments) {
		super(id, paymentType, order);
		this.qtdInstallments = qtdInstallments;
	}

	public Integer getQtdInstallments() {
		return qtdInstallments;
	}

	public void setQtdInstallments(Integer qtdInstallments) {
		this.qtdInstallments = qtdInstallments;
	}
}
