package br.com.zupacademy.witer.casadocodigo.autor;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autor")
public class AutorController {

	private final AutorRepository autorRepository;

	public AutorController(AutorRepository autorRepository) {
		this.autorRepository = autorRepository;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<AutorDTO> cadastrar(@RequestBody @Valid AutorDTO autorDTO){
		
		Autor autor = autorDTO.converte();
		
		autorRepository.save(autor);
		
		return ResponseEntity.ok(autorDTO);
	}
	
	
}
