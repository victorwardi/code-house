package run.victor.api.codehouse.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Victor Wardi - @victorwardi
 */
@Entity
public class Coupon {

    @Id
    @NotNull
    private String code;

    @NotNull
    @Positive
    private BigDecimal discount;

    @NotNull
    @Future
    private LocalDateTime expiration;

    protected Coupon() {
    }

    public Coupon(String code, @NotNull @Positive BigDecimal discount, @NotNull @Future LocalDateTime expiration) {
        this.code = code;
        this.discount = discount;
        this.expiration = expiration;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }
}
