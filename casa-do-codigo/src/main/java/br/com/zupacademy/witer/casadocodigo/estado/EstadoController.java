package br.com.zupacademy.witer.casadocodigo.estado;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EntityManager manager;

	@Autowired
	private EstadoUnicoNoPaisValidator estadoUnicoNoPaisValidator;

	@InitBinder
	public void init(WebDataBinder binder) {

		binder.addValidators(estadoUnicoNoPaisValidator);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<NovoEstadoResquest> cadastrarEstado(
			@RequestBody @Valid NovoEstadoResquest novoEstadoResquest) {

		Estado novoEstado = novoEstadoResquest.toModel(manager);

		manager.persist(novoEstado);
		return ResponseEntity.ok(novoEstadoResquest);
	}

}
