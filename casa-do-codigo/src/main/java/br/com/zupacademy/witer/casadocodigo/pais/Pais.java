package br.com.zupacademy.witer.casadocodigo.pais;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_pais")
public class Pais {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nomePais;

	@Deprecated
	public Pais() {
	}

	public Pais(@NotBlank String nomePais) {
		super();
		this.nomePais = nomePais;
	}

}
