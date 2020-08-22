package run.victor.api.codehouse.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.victor.api.codehouse.model.Coupon;
import run.victor.api.codehouse.request.NewCouponRequest;

/**
 * @author Victor Wardi - @victorwardi
 */
@RestController
@RequestMapping("v1/coupons")
public class CouponsController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public Coupon registerCoupon(@RequestBody @Valid NewCouponRequest newCouponRequest) {
        Coupon coupon = newCouponRequest.toModel();
        manager.persist(coupon);
        return coupon;
    }


}
