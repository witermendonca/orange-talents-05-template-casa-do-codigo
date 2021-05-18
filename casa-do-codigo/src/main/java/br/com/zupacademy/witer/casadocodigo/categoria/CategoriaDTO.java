package br.com.zupacademy.witer.casadocodigo.categoria;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.witer.casadocodigo.validacao.UniqueValue;

public class CategoriaDTO {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "Nome de categoria já cadastrado.")
	private String nome;

	public String getNome() {
		return nome;
	}

	public Categoria toModel() {
		return new Categoria(this.nome);
	}

}
