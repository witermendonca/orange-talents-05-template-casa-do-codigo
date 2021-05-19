package br.com.zupacademy.witer.casadocodigo.livro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.witer.casadocodigo.autor.AutorRepository;
import br.com.zupacademy.witer.casadocodigo.categoria.CategoriaRepository;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroRepository livroRepository;

//	@Autowired
//	private CategoriaRepository categoriaRepository;
//	
//	@Autowired
//	private AutorRepository autorRepository;

	@PersistenceContext
	private EntityManager manager;

	@PostMapping
	@Transactional
	public ResponseEntity<NovoLivroRequest> cadastraLivro(@RequestBody @Valid NovoLivroRequest novoLivroRequest) {

		Livro novoLivro = novoLivroRequest.toModel(manager);

		livroRepository.save(novoLivro);

		return ResponseEntity.ok(novoLivroRequest);
	}
}
