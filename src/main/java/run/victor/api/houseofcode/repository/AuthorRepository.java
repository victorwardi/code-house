package run.victor.api.houseofcode.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import run.victor.api.houseofcode.model.Author;

/**
 * @author Victor Wardi - @victorwardi
 */
@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {



}
