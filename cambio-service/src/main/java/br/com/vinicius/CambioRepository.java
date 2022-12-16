package br.com.vinicius;

import org.springframework.data.repository.CrudRepository;

import br.com.vinicius.model.Cambio;

public interface CambioRepository extends CrudRepository<Cambio, Long> {
	
	Cambio findByFromAndTo(String from, String to);

}
