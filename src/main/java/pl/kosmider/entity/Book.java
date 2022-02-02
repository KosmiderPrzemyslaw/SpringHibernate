package pl.kosmider.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5)
    @Column(name = "myTitle",
            length = 100,
            nullable = false)
    private String title;

    @Min(1)
    @Max(10)
    @Column(scale = 2, precision = 4)
    private BigDecimal rating;

    @Size(max = 600)
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    private String author;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @NotNull
    @ManyToOne
    private Publisher publisher;


    public void setPages(Integer pages) {
        this.pages = pages;
    }
    @Min(1)
    @Column(name = "pages")
    private Integer pages;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "authors_id"))
    private List<Author> authors = new ArrayList<>();

    public Book(@Size(min = 5) String title, @Min(1) @Max(10) BigDecimal rating, @Size(max = 600) String description, @NotNull String author, LocalDateTime createdOn, LocalDateTime updatedOn, @NotNull Publisher publisher, @Min(1) int pages, List<Author> authors) {
        this.title = title;
        this.rating = rating;
        this.description = description;
        this.author = author;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.publisher = publisher;
        this.pages = pages;
        this.authors = authors;
    }

    public Book() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pages == book.pages &&
                Objects.equals(id, book.id) &&
                Objects.equals(title, book.title) &&
                Objects.equals(rating, book.rating) &&
                Objects.equals(description, book.description) &&
                Objects.equals(author, book.author) &&
                Objects.equals(createdOn, book.createdOn) &&
                Objects.equals(updatedOn, book.updatedOn) &&
                Objects.equals(publisher, book.publisher) &&
                Objects.equals(authors, book.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, rating, description, author, createdOn, updatedOn, publisher, pages, authors);
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", publisher=" + publisher +
                ", authors=" + authors +
                '}';
    }
}
