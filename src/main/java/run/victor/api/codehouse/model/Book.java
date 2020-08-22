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
import java.util.Objects;

/**
 * @author Victor Wardi - @victorwardi
 */
@Entity
public class Book{

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

    private Book(Long id, String title, String description, String sumary, BigDecimal price, Integer pages, String isbn, LocalDateTime publishDate, Category category, Author author) {
        this.id = id;
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

    public static BookBuilder builder(){
        return new BookBuilder();
    }

    public static class BookBuilder {
        private Long id;
        private String title;
        private String description;
        private String sumary;
        private BigDecimal price;
        private Integer pages;
        private String isbn;
        private LocalDateTime publishDate;
        private Category category;
        private Author author;

        BookBuilder() {
        }

        public BookBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BookBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder description(String description) {
            this.description = description;
            return this;
        }

        public BookBuilder sumary(String sumary) {
            this.sumary = sumary;
            return this;
        }

        public BookBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public BookBuilder pages(Integer pages) {
            this.pages = pages;
            return this;
        }

        public BookBuilder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public BookBuilder publishDate(LocalDateTime publishDate) {
            this.publishDate = publishDate;
            return this;
        }

        public BookBuilder category(Category category) {
            this.category = category;
            return this;
        }

        public BookBuilder author(Author author) {
            this.author = author;
            return this;
        }

        public Book build() {
            return new Book(id, title, description, sumary, price, pages, isbn, publishDate, category, author);
        }
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title='" + title + '\'' + ", isbn='" + isbn + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, isbn);
    }
}
