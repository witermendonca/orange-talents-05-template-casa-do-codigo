package br.com.zupacademy.witer.casadocodigo.cliente;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.witer.casadocodigo.estado.Estado;
import br.com.zupacademy.witer.casadocodigo.pais.Pais;
import br.com.zupacademy.witer.casadocodigo.validacao.CPFouCNPJ;
import br.com.zupacademy.witer.casadocodigo.validacao.IdExistente;
import br.com.zupacademy.witer.casadocodigo.validacao.UniqueValue;

public class NovoClienteRequest {

	@NotBlank
	@Email
	@UniqueValue(domainClass = Cliente.class, fieldName = "email", message = "E-mail já cadastrado")
	private String email;

	@NotBlank
	private String nome;

	@NotBlank
	private String sobrenome;

	@NotBlank
	@CPFouCNPJ
	@UniqueValue(domainClass = Cliente.class, fieldName = "documento", message = "Documento já cadastrado.")
	private String documento; // (cpf/cnpj)

	@NotBlank
	private String telefone;

	@NotBlank
	private String endereco;

	@NotBlank
	private String complemento;

	@NotBlank
	private String cidade;

	@NotBlank
	private String cep;

	@IdExistente(domainClass = Estado.class, fieldName = "id", message = "Estado não encontrado, escolha um estado já existente no sistema.")
	private Long estadoId;

	@NotNull
	@IdExistente(domainClass = Pais.class, fieldName = "id", message = "País não encontrado, escolha um país já existente no sistema.")
	private Long paisId;

	public NovoClienteRequest(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome,
			@NotBlank String documento, @NotBlank String telefone, @NotBlank String endereco,
			@NotBlank String complemento, @NotBlank String cidade, @NotBlank String cep, Long estadoId,
			@NotNull Long paisId) {
		super();
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.telefone = telefone;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.cep = cep;
		this.estadoId = estadoId;
		this.paisId = paisId;
	}

	public Long getEstadoId() {
		return estadoId;
	}
	
	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}

	public Long getPaisId() {
		return paisId;
	}

	public boolean temEstado() {
		return Optional.ofNullable(estadoId).isPresent();
	}

	public Cliente toModel(EntityManager manager) {
		@NotNull
		Pais pais = manager.find(Pais.class, paisId);

		Cliente cliente = new Cliente(email, nome, sobrenome, documento, telefone, endereco, complemento, cidade, cep,
				pais);

		if (estadoId != null) {
			cliente.setEstado(manager.find(Estado.class, estadoId));
		}
		return cliente;

	}

}
