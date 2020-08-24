package run.victor.api.codehouse.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import java.util.function.Function;

import org.springframework.util.Assert;

/**
 * @author Victor Wardi - @victorwardi
 */
@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String email;
    private  String firstName;
    private  String lastName;
    private  String document;
    private  String address;
    private  String complement;
    private  String city;
    private  String zipcode;
    private  String telephone;

    @ManyToOne
    private  Country country;

    @ManyToOne
    private State state;

    @OneToOne(mappedBy = "purchase", cascade = CascadeType.PERSIST)
    private Order order;

    private CouponApplied couponApplied;

    public Purchase(String email, String firstName, String lastName, String document, String address, String complement,
                    String city, String zipcode, String telephone, Country country, Function<Purchase, Order> createOrder) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.document = document;
        this.address = address;
        this.complement = complement;
        this.city = city;
        this.zipcode = zipcode;
        this.telephone = telephone;
        this.country = country;
        this.order = createOrder.apply(this);
    }

    public Purchase() {
    }

    public Long getId() {
        return id;
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

    public String getZipcode() {
        return zipcode;
    }

    public String getTelephone() {
        return telephone;
    }

    public Country getCountry() {
        return country;
    }

    public State getState() {
        return state;
    }

    public Order getOrder() {
        return order;
    }

    public CouponApplied getCouponApplied() {
        return couponApplied;
    }

    public void setState(State state) {
        Assert.notNull(country, "It is no allowed to add a state without a country.");
        Assert.isTrue(state.belongToCountry(country), "The state: '"+ state.getName() +"' does not belong to the country: '" + country.getName() + "'.");
        this.state = state;
    }

    public void applyCoupon(Coupon coupon) {
        Assert.isTrue(coupon.isValid(), "Coupon is invalid.");
        Assert.isNull(couponApplied, "A coupon was already applied to this purchase.");
        this.couponApplied  = new CouponApplied(coupon.getCode(), coupon.getDiscount());
    }

    @Override
    public String toString() {
        return "Purchase{" + "id=" + id + ", email='" + email + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", document='" + document + '\'' + ", address='" + address + '\'' + ", complement='" + complement + '\'' + ", city='" + city + '\'' + ", zipcode='" + zipcode + '\'' + ", telephone='" + telephone + '\'' + ", country=" + country + ", state=" + state + ", order=" + order + ", couponApplied=" + couponApplied + '}';
    }
}
