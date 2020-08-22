package run.victor.api.codehouse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Victor Wardi - @victorwardi
 */
@Entity
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @NotNull
    @ManyToOne
    private Country country;

    public State() {
    }

    public State(@NotBlank String name, @NotNull Country country) {
        this.name = name;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public boolean belongToCountry(Country country) {
        return this.country.equals(country);
    }

    @Override
    public String toString() {
        return "State{" + "id=" + id + ", name='" + name + '\'' + ", country=" + country + '}';
    }
}
