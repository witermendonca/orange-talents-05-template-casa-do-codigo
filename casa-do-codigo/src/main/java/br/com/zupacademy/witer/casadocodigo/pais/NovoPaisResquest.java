package br.com.zupacademy.witer.casadocodigo.pais;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.witer.casadocodigo.validacao.UniqueValue;

public class NovoPaisResquest {

	@NotBlank
	@UniqueValue(domainClass = Pais.class, fieldName = "nomePais", message = "Nome de país já cadastrado")
	private String nomePais;

	public String getNomePais() {
		return nomePais;
	}

	public Pais toModel() {
		return new Pais(this.nomePais);
	}

}
