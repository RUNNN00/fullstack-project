package com.ruanazevedo.fullstackprojectbackend.domain.enums;

public enum ClientType {

	PESSOA_FISICA(0),
	PESSOA_JURIDICA(1);
	
	private Integer  cod;
	
	private ClientType(int cod) {
		this.cod = cod;
	}

	public int getCod() {
		return cod;
	}
	
	public static ClientType toEnum(Integer cod) {
		
		if (cod == null)
			return null;
		
		for (ClientType x : ClientType.values()) {
			if (cod.equals(x.getCod()))
				return x;
		}
		
		throw new IllegalArgumentException("Id de enumeração do tipo cliente inválido " + cod);
	}
}