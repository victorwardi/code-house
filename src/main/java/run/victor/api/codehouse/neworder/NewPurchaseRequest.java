package run.victor.api.codehouse.neworder;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.function.Function;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;
import run.victor.api.codehouse.model.Country;
import run.victor.api.codehouse.model.Order;
import run.victor.api.codehouse.model.Purchase;
import run.victor.api.codehouse.model.State;
import run.victor.api.codehouse.validator.IdExists;

/**
 * @author Victor Wardi - @victorwardi
 */
public class NewPurchaseRequest {

    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String firstName;

    @NotBlank
    private final String lastName;

    @NotBlank
    private final String document;

    @NotBlank
    private final String address;

    @NotBlank
    private final String complement;

    @NotBlank
    private final String city;

    @NotNull
    @IdExists(domainClass = Country.class, fieldName = "id")
    private final Long countryId;

    @IdExists(domainClass = State.class, fieldName = "id")
    private final Long stateId;

    @NotBlank
    private final String telephone;

    @NotBlank
    private final String zipcode;

    @NotNull(message = "Cart cannot be null")
    @Valid
    private final NewOrderRequest order;

    public NewPurchaseRequest(@NotBlank @Email String email, @NotBlank String firstName, @NotBlank String lastName, @CPF @CNPJ @NotBlank String document, @NotBlank String address, @NotBlank String complement, @NotBlank String city, @NotNull Long countryId, Long stateId, @NotBlank String telephone, @NotBlank String zipcode, @NotNull @Valid NewOrderRequest order) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.document = document;
        this.address = address;
        this.complement = complement;
        this.city = city;
        this.countryId = countryId;
        this.stateId = stateId;
        this.telephone = telephone;
        this.zipcode = zipcode;
        this.order = order;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDocument() {
        return document;
    }

    public String getAddress() {
        return address;
    }

    public String getComplement() {
        return complement;
    }

    public String getCity() {
        return city;
    }

    public Long getCountryId() {
        return countryId;
    }

    public Long getStateId() {
        return stateId;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getZipcode() {
        return zipcode;
    }

    public NewOrderRequest getOrder() {
        return order;
    }

    public boolean isDocumentValid() {
        Assert.hasLength(document, "A document is required.");

        //*** null params because it is not use in the class

        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(document, null) || cnpjValidator.isValid(document, null);
    }

    public Purchase toModel(EntityManager manager) {
        Country country = manager.find(Country.class, countryId);
        Function<Purchase, Order> createOrder = order.toModel(manager);
        final Purchase purchase = new Purchase(email, firstName, lastName, document, address, complement, city, zipcode, telephone, country, createOrder);

        if(stateId != null){
            purchase.setState(manager.find(State.class, stateId));
        }
        return purchase;
    }

    public boolean hasState() {
        return stateId != null;
    }
}
