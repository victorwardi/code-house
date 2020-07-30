package run.victor.api.codehouse.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import run.victor.api.codehouse.model.Category;

/**
 * @author Victor Wardi - @victorwardi
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
