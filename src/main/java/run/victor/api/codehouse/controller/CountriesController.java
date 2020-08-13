package run.victor.api.codehouse.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.victor.api.codehouse.model.Country;
import run.victor.api.codehouse.request.NewCountryRequest;

/**
 * @author Victor Wardi - @victorwardi
 */
@RestController
@RequestMapping("/v1/countries")
public class CountriesController {

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping
    @Transactional
    public Country registerCountry(@RequestBody @Valid NewCountryRequest newCountryRequest) {
        Country country = newCountryRequest.toModel();
        entityManager.persist(country);
        return country;


    }

}
