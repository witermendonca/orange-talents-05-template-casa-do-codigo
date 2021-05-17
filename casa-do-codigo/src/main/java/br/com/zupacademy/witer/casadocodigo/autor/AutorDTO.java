package br.com.zupacademy.witer.casadocodigo.autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AutorDTO {

	@NotEmpty
	private String nome;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	@Size(max = 400)
	private String descricao;

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getDescricao() {
		return descricao;
	}

	public Autor toModel() {
		return new Autor(this.nome, this.email, this.descricao);
	}

}
