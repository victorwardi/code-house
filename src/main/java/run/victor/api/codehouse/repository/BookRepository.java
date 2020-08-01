package run.victor.api.codehouse.repository;

import org.springframework.data.repository.CrudRepository;
import run.victor.api.codehouse.model.Book;

/**
 * @author Victor Wardi - @victorwardi
 */
public interface BookRepository extends CrudRepository<Book, Long> {

}
