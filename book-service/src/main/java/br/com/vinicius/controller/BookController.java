package br.com.vinicius.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vinicius.model.Book;
import br.com.vinicius.proxy.CambioProxy;
import br.com.vinicius.repository.BookRepository;

@RestController
@RequestMapping("book-service")
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private Environment environment;
	
	@Autowired
	private CambioProxy cambioProxy;

	@GetMapping("/{id}/{currency}")
	public Book getBook(@PathVariable Long id, @PathVariable String currency) {

		Optional<Book> book = bookRepository.findById(id);
		if (book.isEmpty()) {
			throw new RuntimeException("Not found book with id = " + id);
		}
		var cambio = cambioProxy.getCambio(book.get().getPrice(), "USD", currency);
		
		var port = environment.getProperty("local.server.port");
		book.get().setPrice(cambio.getConvertedValue());
		book.get().setEnvironment(port);
		return book.get();
	}

}
