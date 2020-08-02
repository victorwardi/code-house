package run.victor.api.codehouse.request;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.val;
import org.springframework.format.annotation.DateTimeFormat;
import run.victor.api.codehouse.model.Author;
import run.victor.api.codehouse.model.Book;
import run.victor.api.codehouse.model.Category;
import run.victor.api.codehouse.validator.IdExists;
import run.victor.api.codehouse.validator.UniqueValue;

/**
 * @author Victor Wardi - @victorwardi
 */
public class NewBookRequest {

    @NotBlank
    @UniqueValue(domainClass = Book.class, fieldName = "title")
    private final String title;

    @NotBlank
    @Size(max = 500)
    private final String description;

    private final String sumary;

    @NotNull
    @DecimalMin("20.00")
    private final BigDecimal price;

    @NotNull
    @Min(100)
    private final Integer pages;

    @NotBlank
    @UniqueValue(domainClass = Book.class, fieldName = "isbn")
    private final String isbn;

    @NotNull
    @FutureOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime publishDate;

    @NotNull
    @IdExists(domainClass = Category.class, fieldName = "id")
    private final Long idCategory;

    @NotNull
    @IdExists(domainClass = Author.class, fieldName = "id")
    private final Long idAuthor;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    private NewBookRequest(@NotBlank String title, @NotBlank @Size(max = 500) String description, String sumary, @NotNull @DecimalMin("20.00") BigDecimal price, @NotNull @Min(100) Integer pages, @NotBlank String isbn, @NotNull @FutureOrPresent LocalDateTime publishDate, @NotNull Long idCategory, @NotNull Long idAuthor) {
        this.title = title;
        this.description = description;
        this.sumary = sumary;
        this.price = price;
        this.pages = pages;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.idCategory = idCategory;
        this.idAuthor = idAuthor;
    }

    public static NewBookRequest create(@NotBlank String title, @NotBlank @Size(max = 500) String description, String sumary, @NotNull @DecimalMin("20.00") BigDecimal price, @NotNull @Min(100) Integer pages, @NotBlank String isbn, @NotNull @FutureOrPresent LocalDateTime publishDate, @NotNull Long idCategory, @NotNull Long idAuthor) {
        return new NewBookRequest(title, description, sumary, price, pages, isbn, publishDate, idCategory, idAuthor);
    }

    public Book toModel(EntityManager entityManager) {
        Author author = entityManager.find(Author.class, idAuthor);
        Category category = entityManager.find(Category.class, idCategory);
        return Book.create(title, description, sumary, price, pages, isbn, publishDate, category, author);
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

    public Long getIdCategory() {
        return idCategory;
    }

    public Long getIdAuthor() {
        return idAuthor;
    }

    @Override
    public String toString() {
        return "NewBookRequest{" + "title='" + title + '\'' + ", description='" + description + '\'' + ", sumary='" + sumary + '\'' + ", price=" + price + ", pages=" + pages + ", isbn='" + isbn + '\'' + ", publishDate=" + publishDate + ", idCategory=" + idCategory + ", idAuthor=" + idAuthor + '}';
    }
}
