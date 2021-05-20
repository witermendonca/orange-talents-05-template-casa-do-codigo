package br.com.zupacademy.witer.casadocodigo.estado;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

	@Query(value = "select * from tb_estado where nome_estado = :nome and pais_id = :paisId", nativeQuery = true)
	Optional<Estado> buscaEstadoPorPaisIdENome(String nome, Long paisId);
}
