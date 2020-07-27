package run.victor.api.codehouse.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import run.victor.api.codehouse.model.Author;

/**
 * @author Victor Wardi - @victorwardi
 */
@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Optional<Author> findByEmail(String email);
}
