package br.com.zupacademy.witer.casadocodigo.livro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.zupacademy.witer.casadocodigo.autor.Autor;
import br.com.zupacademy.witer.casadocodigo.autor.AutorRepository;
import br.com.zupacademy.witer.casadocodigo.categoria.Categoria;
import br.com.zupacademy.witer.casadocodigo.categoria.CategoriaRepository;
import br.com.zupacademy.witer.casadocodigo.validacao.IdExistente;
import br.com.zupacademy.witer.casadocodigo.validacao.UniqueValue;

public class NovoLivroRequest {

	@NotBlank
	@UniqueValue(domainClass = Livro.class, fieldName = "titulo", message = "Título de livro já cadastrado.")
	private String titulo;

	@NotBlank
	@Size(max = 500)
	private String resumo;

	@NotBlank
	private String sumario;

	@NotNull
	@Min(20)
	private BigDecimal preco;

	@NotNull
	@Min(100)
	private Integer numeroPaginas;

	@NotBlank
	@UniqueValue(domainClass = Livro.class, fieldName = "isbn", message = "Isbn de livro já cadastrado.")
	private String isbn;

	@Future
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate dataPublicacao;

	@NotNull
	@IdExistente(domainClass = Categoria.class, fieldName = "id", message = "Categoria não encontrada, escolha uma categoria já existente no sistema.")
	private Long idCategoria;

	@NotNull
	@IdExistente(domainClass = Categoria.class, fieldName = "id", message = "Autor(a) não encontrado(a), escolha um(a) Autor(a) já existente no sistema.")
	private Long idAutor;

	public NovoLivroRequest(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, @NotBlank String sumario,
			@NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) Integer numeroPaginas, @NotBlank String isbn,
			@NotNull Long idCategoria, @NotNull Long idAutor) {
		super();
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.numeroPaginas = numeroPaginas;
		this.isbn = isbn;
		// this.dataPublicacao = dataPublicacao; JsonFormat não desserializar pelo
		// construtor.
		this.idCategoria = idCategoria;
		this.idAutor = idAutor;
	}

	// JsonFormat não desserializar pelo construtor. mas pelo setter sim.
	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public String getSumario() {
		return sumario;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public Long getIdAutor() {
		return idAutor;
	}

	public Livro toModel(EntityManager manager) {
		@NotNull
		Autor autor = manager.find(Autor.class, idAutor);

		@NotNull
		Categoria categoria = manager.find(Categoria.class, idCategoria);

		Assert.state(Objects.nonNull(autor),
				"Você esta querendo cadastrar um livro para um autor que nao existe no banco " + idAutor);
		Assert.state(Objects.nonNull(categoria),
				"Você esta querendo cadastrar um livro para uma categoria que nao existe no banco " + idCategoria);

		return new Livro(titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao, autor, categoria);
	}

//	public Livro toModel(AutorRepository autorRepository, CategoriaRepository categoriaRepository) {
//
//		Autor autor = autorRepository.getOne(idAutor);
//
//		Categoria categoria = categoriaRepository.getOne(idCategoria);
//
//		return new Livro(titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao, autor, categoria);
//	}

}
