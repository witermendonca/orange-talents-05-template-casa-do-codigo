package br.com.zupacademy.witer.casadocodigo.livro;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.zupacademy.witer.casadocodigo.autor.Autor;

public class InfoLivroRequest {

	private Long id;

	private String titulo;

	private String resumo;

	private String sumario;

	private BigDecimal preco;

	private Integer numeroPaginas;

	private String isbn;

	private LocalDate dataPublicacao;

	private Autor autor;

	public InfoLivroRequest(Livro livro) {
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
		this.resumo = livro.getResumo();
		this.sumario = livro.getSumario();
		this.preco = livro.getPreco();
		this.numeroPaginas = livro.getNumeroPaginas();
		this.isbn = livro.getIsbn();
		this.dataPublicacao = livro.getDataPublicacao();
		this.autor = livro.getAutor();
	}

	public Long getId() {
		return id;
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

	public Autor getAutor() {
		return autor;
	}

}
