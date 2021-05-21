package br.com.zupacademy.witer.casadocodigo.cliente;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@RequestMapping("/clientes")
public class ClienteController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private EstadoNotNullValidator estadoNotNullValidator;

	@Autowired
	private EstadoDoPaisValidator estadoDoPaisValidator;

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(estadoNotNullValidator, estadoDoPaisValidator);

	}

	@PostMapping
	@Transactional
	public ResponseEntity<NovoClienteDTO> cadastrarCliente(@RequestBody @Valid NovoClienteRequest novoClienteRequest) {

		Cliente novoCliente = novoClienteRequest.toModel(manager);

		manager.persist(novoCliente);

		return ResponseEntity.ok(new NovoClienteDTO(novoCliente));
	}

}
