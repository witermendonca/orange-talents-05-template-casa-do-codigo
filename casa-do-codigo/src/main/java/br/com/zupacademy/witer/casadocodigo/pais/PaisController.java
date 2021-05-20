package br.com.zupacademy.witer.casadocodigo.pais;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paises")
public class PaisController {

	@Autowired
	private EntityManager manager;

	@PostMapping
	@Transactional
	public ResponseEntity<NovoPaisResquest> cadastrarPais(@RequestBody @Valid NovoPaisResquest novoPaisResquest) {

		Pais novoPais = novoPaisResquest.toModel();

		manager.persist(novoPais);
		return ResponseEntity.ok(novoPaisResquest);

	}
}
