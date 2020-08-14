package run.victor.api.codehouse.novopagamento;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;
import run.victor.api.codehouse.model.Country;
import run.victor.api.codehouse.model.State;
import run.victor.api.codehouse.validator.IdExists;

/**
 * @author Victor Wardi - @victorwardi
 */
public class NewPaymentRequest {

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
    private final String telefone;

    @NotBlank
    private final String zipcode;

    public NewPaymentRequest(@NotBlank @Email String email, @NotBlank String firstName, @NotBlank String lastName,
                             @CPF @CNPJ @NotBlank String document, @NotBlank String address, @NotBlank String complement,
                             @NotBlank String city, @NotNull Long countryId, Long stateId, @NotBlank String telefone, @NotBlank String zipcode) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.document = document;
        this.address = address;
        this.complement = complement;
        this.city = city;
        this.countryId = countryId;
        this.stateId = stateId;
        this.telefone = telefone;
        this.zipcode = zipcode;
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

    public String getTelefone() {
        return telefone;
    }

    public String getZipcode() {
        return zipcode;
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

}
