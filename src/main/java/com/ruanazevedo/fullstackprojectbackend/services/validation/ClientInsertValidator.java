package com.ruanazevedo.fullstackprojectbackend.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ruanazevedo.fullstackprojectbackend.domain.enums.ClientType;
import com.ruanazevedo.fullstackprojectbackend.dto.ClientNewDTO;
import com.ruanazevedo.fullstackprojectbackend.resources.exceptions.FieldMessage;
import com.ruanazevedo.fullstackprojectbackend.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {

	@Override
	public void initialize(ClientInsert ann) {

	}

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getType().equals(ClientType.PESSOA_FISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOrCnpj()))
			list.add(new FieldMessage("cpfOrCnpj", "CPF inválido"));

		if (objDto.getType().equals(ClientType.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOrCnpj()))
			list.add(new FieldMessage("cpfOrCnpj", "CNPJ inválido"));
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
