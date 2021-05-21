package br.com.zupacademy.witer.casadocodigo.cliente;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.witer.casadocodigo.estado.Estado;

@Component
public class EstadoNotNullValidator implements Validator {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoClienteRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		if (errors.hasErrors()) {
			return;
		}

		NovoClienteRequest novoClienteRequest = (NovoClienteRequest) target;

		Long estadoId = novoClienteRequest.getEstadoId();
		Long paisId = novoClienteRequest.getPaisId();

		Query query = manager.createNativeQuery("select * from tb_estado where pais_id = :paisId");
		query.setParameter("paisId", paisId);
		List<Estado> listEstadosDoPais = query.getResultList();

		if (!listEstadosDoPais.isEmpty() && estadoId == null) {
			errors.rejectValue("estadoId", null, "Estado não pode ser nulo para o país informado.");
		}

	}
}
