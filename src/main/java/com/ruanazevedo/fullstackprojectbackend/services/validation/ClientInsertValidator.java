package com.ruanazevedo.fullstackprojectbackend.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ruanazevedo.fullstackprojectbackend.domain.Client;
import com.ruanazevedo.fullstackprojectbackend.domain.enums.ClientType;
import com.ruanazevedo.fullstackprojectbackend.dto.ClientNewDTO;
import com.ruanazevedo.fullstackprojectbackend.repositories.ClientRepository;
import com.ruanazevedo.fullstackprojectbackend.resources.exceptions.FieldMessage;
import com.ruanazevedo.fullstackprojectbackend.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {

	@Autowired
	private ClientRepository repo;
	
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
		
		Client aux = repo.findByEmail(objDto.getEmail());
		if (aux != null)
			list.add(new FieldMessage("Email", "Email já existente"));
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
