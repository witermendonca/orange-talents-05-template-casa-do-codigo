package br.com.zupacademy.witer.casadocodigo.autor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeEmailDuplicadoAutorValidator implements Validator {

	@Autowired
	private AutorRepository autorRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return AutorDTO.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		if(errors.hasErrors()) {
			return;
		}
		
		AutorDTO autorDTO = (AutorDTO) target;
		
		Optional<Autor> autorPresente = autorRepository.findByEmail(autorDTO.getEmail());
		
		if(autorPresente.isPresent()) {
			errors.rejectValue("email", null,
					"Já cadastrado(a) um(a) outro(a) autor(a) com o mesmo email "
							+ autorDTO.getEmail());
		}
	}
}
