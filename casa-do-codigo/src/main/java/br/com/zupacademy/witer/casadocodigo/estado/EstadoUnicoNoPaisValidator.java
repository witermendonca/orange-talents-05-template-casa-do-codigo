package br.com.zupacademy.witer.casadocodigo.estado;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EstadoUnicoNoPaisValidator implements Validator {

	@Autowired
	private EstadoRepository estadoRepository;

	@Override
	public boolean supports(Class<?> clazz) {

		return NovoEstadoResquest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		if (errors.hasErrors()) {
			return;
		}

		NovoEstadoResquest novoEstado = (NovoEstadoResquest) target;

		Optional<Estado> estadoPresente = estadoRepository.buscaEstadoPorPaisIdENome(novoEstado.getNomeEstado(),
				novoEstado.getPaisId());

		if (estadoPresente.isPresent()) {
			errors.rejectValue("nomeEstado", null,
					"Estado "+ novoEstado.getNomeEstado() +" já cadastrado nesse país." + novoEstado.getPaisId());
		}
	}
}
