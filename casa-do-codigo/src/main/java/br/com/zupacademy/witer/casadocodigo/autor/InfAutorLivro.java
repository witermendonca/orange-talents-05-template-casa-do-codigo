package br.com.zupacademy.witer.casadocodigo.autor;

public class InfAutorLivro {

	private String nome;

	private String descricao;

	public InfAutorLivro(Autor autor) {
		this.nome = autor.getNome();
		this.descricao = autor.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

}
