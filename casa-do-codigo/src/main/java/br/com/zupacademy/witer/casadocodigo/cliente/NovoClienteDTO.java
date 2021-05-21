package br.com.zupacademy.witer.casadocodigo.cliente;

public class NovoClienteDTO {

	private Long id;

	public NovoClienteDTO(Cliente cliente) {

		this.id = cliente.getId();
	}

	public Long getId() {
		return id;
	}

}
