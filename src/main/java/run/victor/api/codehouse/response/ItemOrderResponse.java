package run.victor.api.codehouse.response;

import java.math.BigDecimal;

import run.victor.api.codehouse.model.ItemOrder;

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

    public static ItemOrderResponse fromModel(ItemOrder itemOrder){
        final BookResponse bookResponse = BookResponse.fromModel(itemOrder.getBook());
        return new ItemOrderResponse(bookResponse, itemOrder.getQuantity(), itemOrder.getCurrentBookPrice());
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
