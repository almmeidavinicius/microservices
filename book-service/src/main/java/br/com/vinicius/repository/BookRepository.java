package br.com.vinicius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vinicius.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
