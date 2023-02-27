package com.ruanazevedo.fullstackprojectbackend.domain.enums;

public enum StatePayment {

	PENDING(1),
	SETTLED(2),
	CANCELED(3);
	
	private int cod;
	
	private StatePayment(int cod) {
		this.cod = cod;
	}

	public int getCod() {
		return cod;
	}
	
	public static StatePayment toEnum(Integer cod) {
		
		if (cod == null)
			return null;
		
		for (StatePayment x : StatePayment.values()) {
			if (cod.equals(x.getCod()))
				return x;
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}