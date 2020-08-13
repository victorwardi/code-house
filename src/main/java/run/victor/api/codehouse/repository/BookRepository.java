package run.victor.api.codehouse.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import run.victor.api.codehouse.model.Book;

/**
 * @author Victor Wardi - @victorwardi
 */
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();
}
