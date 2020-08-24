package run.victor.api.codehouse.request;

import run.victor.api.codehouse.model.Purchase;

/**
 * @author Victor Wardi - @victorwardi
 */

public class PurchaseDetailsResponse {

    private  String email;
    private  String firstName;
    private  String lastName;
    private  String document;
    private  String address;
    private  String complement;
    private  String city;
    private  String country;
    private  String state;
    private  String telephone;
    private  String zipcode;
    private  String coupon;
    private OrderDetailsResponse order;

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
        this.state = purchase.getState() != null ? purchase.getState().getName() : "";
        this.zipcode = purchase.getZipcode();
        this.coupon = purchase.getCouponApplied().getCode();
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

    public OrderDetailsResponse getOrder() {
        return order;
    }
}
