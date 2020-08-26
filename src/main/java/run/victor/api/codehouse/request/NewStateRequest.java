package run.victor.api.codehouse.request;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import run.victor.api.codehouse.model.Country;
import run.victor.api.codehouse.model.State;
import run.victor.api.codehouse.validator.IdExists;
import run.victor.api.codehouse.validator.UniqueValue;

/**
 * @author Victor Wardi - @victorwardi
 */
public class NewStateRequest {

    @NotBlank
    @UniqueValue(domainClass = State.class, fieldName = "name")
    private String name;

    @NotNull
    @IdExists(domainClass = Country.class, fieldName = "id")
    private Long countryId;

    public State toModel(EntityManager entityManager){
        final Country country = entityManager.find(Country.class, this.countryId);
        State state = new State(this.name, country);
        return state;
    }

    public NewStateRequest(@NotBlank String name, @NotNull Long countryId) {
        this.name = name;
        this.countryId = countryId;
    }

    public String getName() {
        return name;
    }

    public Long getCountryId() {
        return countryId;
    }
}
