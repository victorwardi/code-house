package run.victor.api.codehouse.request;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.aop.scope.ScopedObject;
import run.victor.api.codehouse.model.Country;
import run.victor.api.codehouse.validator.UniqueValue;

/**
 * @author Victor Wardi - @victorwardi
 */
public class NewCountryRequest {

    @NotBlank
    @UniqueValue(domainClass = Country.class, fieldName = "name")
    private final String name;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES) // Needed when class has only one property
    public NewCountryRequest(@NotBlank String name) {
        this.name = name;
    }

    public Country toModel(){
        Country country = new Country(this.name);
        return country;
    }

    public String getName() {
        return name;
    }
}
