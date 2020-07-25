package run.victor.deveficiente.api.casadocodigo.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import run.victor.deveficiente.api.casadocodigo.model.Author;

/**
 * @author Victor Wardi - @victorwardi
 */
@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {



}
