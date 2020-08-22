package run.victor.api.codehouse.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Victor Wardi - @victorwardi
 */
@Embeddable
public class ItemOrder {

    @ManyToOne
    @NotNull
    private Book book;

    @NotNull
    @Min(1)
    private Long quantity;

    @Positive
    private BigDecimal currentBookPrice;

    public ItemOrder() {
    }

    public ItemOrder(Book book, Long quantity) {
        this.book = book;
        this.quantity = quantity;
        this.currentBookPrice = book.getPrice();
    }

    public Book getBook() {
        return book;
    }

    public Long getQuantity() {
        return quantity;
    }

    public BigDecimal getCurrentBookPrice() {
        return currentBookPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemOrder itemOrder = (ItemOrder) o;
        return Objects.equals(book, itemOrder.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book);
    }

    public BigDecimal getTotal() {
        return currentBookPrice.multiply(new BigDecimal(quantity));
    }

    @Override
    public String toString() {
        return "ItemOrder{" + "book=" + book + ", quantity=" + quantity + ", currentBookPrice=" + currentBookPrice + '}';
    }
}
