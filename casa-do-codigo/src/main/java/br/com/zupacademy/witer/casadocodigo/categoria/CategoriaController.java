package br.com.zupacademy.witer.casadocodigo.categoria;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<CategoriaDTO> cadastrar(@RequestBody @Valid CategoriaDTO categoriaDTO) {

		Categoria categoria = categoriaDTO.toModel();

		categoriaRepository.save(categoria);

		return ResponseEntity.ok(categoriaDTO);
	}
}
