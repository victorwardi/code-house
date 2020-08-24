package run.victor.api.codehouse.model;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * @author Victor Wardi - @victorwardi
 */
@Embeddable
public class CouponApplied {

    private String code;
    private BigDecimal discount;

    public CouponApplied(String code, BigDecimal discount) {
        this.code = code;
        this.discount = discount;
    }

    protected CouponApplied() {
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "CouponApplied{" + "code='" + code + '\'' + ", discount=" + discount + '}';
    }
}
