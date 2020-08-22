package run.victor.api.codehouse.request;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import run.victor.api.codehouse.model.Coupon;
import run.victor.api.codehouse.validator.UniqueValue;

/**
 * @author Victor Wardi - @victorwardi
 */
public class NewCouponRequest {

    @NotNull
    @UniqueValue(domainClass = Coupon.class, fieldName = "code")
    private String code;

    @NotNull
    @Positive
    private BigDecimal discount;

    @NotNull
    @Future
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiration;

    public Coupon toModel() {
        return new Coupon(code, discount, expiration);
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
