package run.victor.api.codehouse.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import run.victor.api.codehouse.model.Book;

/**
 * @author Victor Wardi - @victorwardi
 */
public class BookDetailResponse {
    private final String title;
    private final String description;
    private final String sumary;
    private final BigDecimal price;
    private final Integer pages;
    private final String isbn;
    private final LocalDateTime publishDate;
    private final AuthorDetailResponse author;
    private final CategoryDetailResponse category;

    public BookDetailResponse(Book book) {
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.sumary = book.getSumary();
        this.price = book.getPrice();
        this.pages = book.getPages();
        this.isbn = book.getIsbn();
        this.publishDate = book.getPublishDate();
        this.author = new AuthorDetailResponse(book.getAuthor());
        this.category = new CategoryDetailResponse(book.getCategory());
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSumary() {
        return sumary;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getPages() {
        return pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public AuthorDetailResponse getAuthor() {
        return author;
    }

    public CategoryDetailResponse getCategory() {
        return category;
    }
}
