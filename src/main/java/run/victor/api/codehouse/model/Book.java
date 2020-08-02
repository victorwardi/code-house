package run.victor.api.codehouse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Victor Wardi - @victorwardi
 */
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String title;

    @NotBlank
    @Size(max = 500)
    private String description;

    private String sumary;

    @NotNull
    @DecimalMin("20.00")
    private BigDecimal price;

    @NotNull
    @Min(100)
    private Integer pages;

    @NotBlank
    @Column(unique = true)
    private String isbn;

    @NotNull
    @FutureOrPresent
    private LocalDateTime publishDate;

    @ManyToOne
    @NotNull
    private Category category;

    @ManyToOne
    @NotNull
    private Author author;

    public Book() {
    }

    private Book(String title, String description, String sumary, BigDecimal price, Integer pages, String isbn, LocalDateTime publishDate, Category category, Author author) {
        this.title = title;
        this.description = description;
        this.sumary = sumary;
        this.price = price;
        this.pages = pages;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.category = category;
        this.author = author;
    }

    public static Book create(String title, String description, String sumary, BigDecimal price, Integer pages, String isbn, LocalDateTime publishDate, Category category, Author author) {
        return new Book(title, description, sumary, price, pages, isbn, publishDate, category, author);
    }


    public Long getId() {
        return id;
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

    public Category getCategory() {
        return category;
    }

    public Author getAuthor() {
        return author;
    }
}
