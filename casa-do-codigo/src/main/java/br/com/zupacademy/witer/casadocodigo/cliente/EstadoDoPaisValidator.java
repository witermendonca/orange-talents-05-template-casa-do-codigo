package br.com.zupacademy.witer.casadocodigo.cliente;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.witer.casadocodigo.estado.Estado;
import br.com.zupacademy.witer.casadocodigo.pais.Pais;

@Component
public class EstadoDoPaisValidator implements Validator {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoClienteRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		NovoClienteRequest novoClienteRequest = (NovoClienteRequest) target;

		if (errors.hasErrors() || novoClienteRequest.getEstadoId() == null) {
			return;
		}

		Pais paisEsperado = manager.find(Pais.class, novoClienteRequest.getPaisId());
		Estado estadoEsperado = manager.find(Estado.class, novoClienteRequest.getEstadoId());

		if (estadoEsperado.pertenceAoPais(paisEsperado) == false) {
			errors.rejectValue("estadoId", null, "este estado não pertence ao país selecionado");
		}

	}
}
