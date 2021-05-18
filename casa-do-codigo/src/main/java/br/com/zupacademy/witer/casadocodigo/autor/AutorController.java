package br.com.zupacademy.witer.casadocodigo.autor;

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
@RequestMapping("/autores")
public class AutorController {

	@Autowired
	private ProibeEmailDuplicadoAutorValidator proibeEmailDuplicadoAutorValidator;

	@Autowired
	private AutorRepository autorRepository;

	@InitBinder
	public void init(WebDataBinder binder) {
		// chama validador de inicio.
		binder.addValidators(proibeEmailDuplicadoAutorValidator);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<AutorDTO> cadastrarAutor(@RequestBody @Valid AutorDTO autorDTO) {

		Autor autor = autorDTO.toModel();

		autorRepository.save(autor);
		return ResponseEntity.ok(autorDTO);
	}

}
