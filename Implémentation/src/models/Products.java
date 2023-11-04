package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Products {
    @JsonProperty("books")
    public final List<Book> books;

    public Products(@JsonProperty("books") List<Book> books) {
        this.books = books;

    }

    public List<Book> getBooks() {
        return this.books;
    }

}