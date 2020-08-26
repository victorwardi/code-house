package run.victor.api.codehouse.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.victor.api.codehouse.model.State;
import run.victor.api.codehouse.request.NewCountryRequest;
import run.victor.api.codehouse.request.NewStateRequest;

/**
 * @author Victor Wardi - @victorwardi
 */
@RestController
@RequestMapping("/v1/states")
public class StatesController {

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping
    @Transactional
    public State registerState(@RequestBody @Valid NewStateRequest newStateRequest) {
        final State state = newStateRequest.toModel(entityManager);
        entityManager.persist(state);
        return state;
    }
}
 
 
    
                                 
