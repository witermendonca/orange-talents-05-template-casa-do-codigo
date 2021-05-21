package br.com.zupacademy.witer.casadocodigo.estado;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.witer.casadocodigo.pais.Pais;

@Entity
@Table(name = "tb_estado")
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nomeEstado;

	@ManyToOne
	@NotNull
	private Pais pais;

	@Deprecated
	public Estado() {
	}

	public Estado(@NotBlank String nomeEstado, @NotNull Pais pais) {
		super();
		this.nomeEstado = nomeEstado;
		this.pais = pais;
	}

	public boolean pertenceAoPais(Pais paisEsperado) {
		return paisEsperado.equals(pais);
	}

	

}
