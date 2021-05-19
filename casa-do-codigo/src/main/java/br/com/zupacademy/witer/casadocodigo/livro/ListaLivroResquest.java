package br.com.zupacademy.witer.casadocodigo.livro;

import java.util.List;
import java.util.stream.Collectors;

public class ListaLivroResquest {

	private Long id;

	private String titulo;

	public ListaLivroResquest(Livro livro) {
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public static List<ListaLivroResquest> toDTO(List<Livro> livro) {

		return livro.stream().map(ListaLivroResquest::new).collect(Collectors.toList());

	}

}
