package run.victor.api.codehouse.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import run.victor.api.codehouse.repository.CategoryRepository;
import run.victor.api.codehouse.request.NewCategoryRequest;

/**
 * @author Victor Wardi - @victorwardi
 */
@Component
public class ProhibitsDuplicateCategoryValidator implements Validator {


    private final CategoryRepository categoryRepository;

    public ProhibitsDuplicateCategoryValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(NewCategoryRequest.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        NewCategoryRequest newCategoryRequest = (NewCategoryRequest) o;
        if (categoryRepository.findByName(newCategoryRequest.getName()).isPresent()){
            errors.rejectValue("name", "102", "Category name must be unique");
        }
    }
}
