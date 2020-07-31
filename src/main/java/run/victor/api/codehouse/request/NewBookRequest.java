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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.source.tree.CaseTree;
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
    @UniqueValue(domainClass = Book.class, fieldName = "isbn")
    private String isbn;

    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime publishDate;

    @NotNull
    @IdExists(domainClass = Category.class, fieldName = "id")
    private Long idCategory;

    @NotNull
   @IdExists(domainClass = Author.class, fieldName = "id")
    private Long idAuthor;

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
}
