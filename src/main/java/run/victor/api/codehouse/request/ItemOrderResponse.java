package run.victor.api.codehouse.request;

import java.math.BigDecimal;

/**
 * @author Victor Wardi - @victorwardi
 */
public class ItemOrderResponse {

    private final BookResponse book;
    private final Long quantity;
    private final BigDecimal currentBookPrice;

    public ItemOrderResponse(BookResponse book, Long quantity, BigDecimal currentBookPrice) {
        this.book = book;
        this.quantity = quantity;
        this.currentBookPrice = currentBookPrice;
    }

    public BookResponse getBook() {
        return book;
    }

    public Long getQuantity() {
        return quantity;
    }

    public BigDecimal getCurrentBookPrice() {
        return currentBookPrice;
    }
}
