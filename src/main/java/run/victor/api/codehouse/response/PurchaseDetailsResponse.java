package run.victor.api.codehouse.response;

import java.util.Objects;

import run.victor.api.codehouse.model.Purchase;

/**
 * @author Victor Wardi - @victorwardi
 */
//9
public class PurchaseDetailsResponse {

    private final String email;
    private final String firstName;
    private final String lastName;
    private final String document;
    private final String address;
    private final String complement;
    private final String city;
    private final String country;
    private final String state;
    private final String telephone;
    private final String zipcode;
    private final String coupon;
    private final boolean couponApplied;
    private final OrderDetailsResponse order;

    //1
    public PurchaseDetailsResponse(Purchase purchase) {
        this.firstName = purchase.getFirstName();
        this.lastName = purchase.getLastName();
        this.document = purchase.getDocument();
        this.email = purchase.getEmail();
        this.telephone = purchase.getTelephone();
        this.address = purchase.getAddress();
        this.complement = purchase.getComplement();
        this.city = purchase.getCity();
        this.country = purchase.getCountry().getName();
        //2
        this.state = purchase.getState() != null ? purchase.getState().getName() : "";
        this.zipcode = purchase.getZipcode();
        //1
        if(purchase.getCouponApplied() != null){
            this.couponApplied = true;
            this.coupon = purchase.getCouponApplied().getCode();
        //1
        }else{
            this.couponApplied = false;
            this.coupon = null;
        }
        //1
        this.order = new OrderDetailsResponse(purchase);
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

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCoupon() {
        return coupon;
    }

    public boolean isCouponApplied() {
        return couponApplied;
    }

    public OrderDetailsResponse getOrder() {
        return order;
    }
}
