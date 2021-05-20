package br.com.zupacademy.witer.casadocodigo.estado;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.witer.casadocodigo.pais.Pais;
import br.com.zupacademy.witer.casadocodigo.validacao.IdExistente;

public class NovoEstadoResquest {

	@NotBlank
	private String nomeEstado;

	@NotNull
	@IdExistente(domainClass = Pais.class, fieldName = "id", message = "País não encontrada, escolha um país já existente no sistema.")
	private Long paisId;

	public NovoEstadoResquest(@NotBlank String nomeEstado, @NotNull Long paisId) {
		super();
		this.nomeEstado = nomeEstado;
		this.paisId = paisId;
	}

	public String getNomeEstado() {
		return nomeEstado;
	}

	public Long getPaisId() {
		return paisId;
	}

	public Estado toModel(EntityManager manager) {
		@NotNull
		Pais pais = manager.find(Pais.class, paisId);

		return new Estado(nomeEstado, pais);
	}

}
