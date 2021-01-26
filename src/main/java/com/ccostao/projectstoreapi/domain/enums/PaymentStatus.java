package com.ccostao.projectstoreapi.domain.enums;

public enum PaymentStatus {
	
	PENDING(1, "Pending"),
	PAID(2, "Paid"),
	CANCELED(3, "Canceled");

	private int cod;
	private String status;

	private PaymentStatus(int cod, String status) {
		this.cod = cod;
		this.status = status;
	}

	public int getCod() {
		return cod;
	}

	public String getStatus () {
		return status;
	}

	public static PaymentStatus toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (PaymentStatus x : PaymentStatus.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Invalid ID: " + cod);
	}
}
