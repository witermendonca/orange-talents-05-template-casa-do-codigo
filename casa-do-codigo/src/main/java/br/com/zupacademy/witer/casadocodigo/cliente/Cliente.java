package br.com.zupacademy.witer.casadocodigo.cliente;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.witer.casadocodigo.estado.Estado;
import br.com.zupacademy.witer.casadocodigo.pais.Pais;
import br.com.zupacademy.witer.casadocodigo.validacao.CPFouCNPJ;

@Entity
@Table(name = "tb_cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String nome;

	@NotBlank
	private String sobrenome;

	@NotBlank
	@CPFouCNPJ
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

	@ManyToOne
	private Estado estado;

	@ManyToOne
	@NotNull
	@Valid
	private Pais pais;

	@Deprecated
	public Cliente() {
	}

	public Cliente(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome,
			@NotBlank String documento, @NotBlank String telefone, @NotBlank String endereco,
			@NotBlank String complemento, @NotBlank String cidade, @NotBlank String cep, @NotNull @Valid Pais pais) {
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
		this.pais = pais;
	}

	public Long getId() {
		return id;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
