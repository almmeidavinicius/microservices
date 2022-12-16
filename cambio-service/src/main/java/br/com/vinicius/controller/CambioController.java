package br.com.vinicius.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vinicius.CambioRepository;
import br.com.vinicius.model.Cambio;

@RestController
@RequestMapping("/cambio-service")
public class CambioController {

	@Autowired
	private Environment environment;

	@Autowired
	private CambioRepository cambioRepository;

	@GetMapping("{amount}/{from}/{to}")
	public Cambio getCambio(@PathVariable BigDecimal amount, @PathVariable String from, @PathVariable String to) {

		Cambio cambio = cambioRepository.findByFromAndTo(from, to);
		
		if (cambio == null) {
			throw new RuntimeException("Not found!");
		}

		var port = environment.getProperty("local.server.port");
		cambio.setConvertedValue(cambio.getConversionFactor().multiply(amount).setScale(2, RoundingMode.CEILING));
		cambio.setEnvironment(port);
		return cambio;

	}

}
