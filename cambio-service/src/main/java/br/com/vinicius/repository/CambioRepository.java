package br.com.vinicius.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.vinicius.model.Cambio;

@Repository
public interface CambioRepository extends CrudRepository<Cambio, Long> {
	
	Cambio findByFromAndTo(String from, String to);

}
