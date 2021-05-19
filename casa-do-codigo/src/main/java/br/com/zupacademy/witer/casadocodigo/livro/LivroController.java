package br.com.zupacademy.witer.casadocodigo.livro;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping
	@Transactional
	public List<ListaLivroResquest> listaLivros(){
		 List<Livro> listaLivos = livroRepository.findAll();
		 
		 return ListaLivroResquest.toDTO(listaLivos);
	}
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<InfoLivroRequest> infoLivro(@PathVariable Long id){
		
		Optional<Livro> livroExistente = livroRepository.findById(id);
		
		if(livroExistente.isPresent()) {
			return ResponseEntity.ok(new InfoLivroRequest(livroExistente.get()));
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
}
