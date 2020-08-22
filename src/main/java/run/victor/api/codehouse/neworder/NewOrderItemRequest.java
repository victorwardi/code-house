package run.victor.api.codehouse.neworder;

import javax.persistence.EntityManager;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import run.victor.api.codehouse.exception.BookNotFoundException;
import run.victor.api.codehouse.model.Book;
import run.victor.api.codehouse.model.ItemOrder;
import run.victor.api.codehouse.repository.BookRepository;
import run.victor.api.codehouse.validator.IdExists;

/**
 * @author Victor Wardi - @victorwardi
 */
public class NewOrderItemRequest {

    @NotNull
    @IdExists(domainClass = Book.class, fieldName = "id")
    private final Long bookId;

    @NotNull
    @Min(value = 1)
    private final Long quantity;

    public NewOrderItemRequest(@NotNull Long bookId, Long quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public BigDecimal getItemTotal(BookRepository bookRepository) {
        final Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        return book.getPrice().multiply(new BigDecimal(quantity));
    }

    public ItemOrder toModel(EntityManager entityManager) {
        Book book = entityManager.find(Book.class, bookId);
        ItemOrder itemOrder = new ItemOrder(book, quantity);
        return itemOrder;

    }
}
