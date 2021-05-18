package br.com.zupacademy.witer.casadocodigo.autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zupacademy.witer.casadocodigo.validacao.UniqueValue;

public class AutorDTO {

	@NotBlank
	private String nome;

	@NotBlank
	@Email
	@UniqueValue(domainClass = Autor.class, fieldName = "email", message = "Autor(a) com E-mail já cadastrado." )
	private String email;

	@NotBlank
	@Size(max = 400)
	private String descricao;

	public AutorDTO(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Size(max = 400) String descricao) {
		super();
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}

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
