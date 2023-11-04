package services;

import models.*;
import utilities.JSONHandler;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class BuyerService {

    private final Set<Book> bookSet;
    private final Products products;
    private final JSONHandler JSONHandler;

    public BuyerService() throws IOException {
        JSONHandler = new JSONHandler();
        products = JSONHandler.readJsonFromFile("src/data/products.json", Products.class);
        this.bookSet = new HashSet<>(products.getBooks());
    }

    public Products getBooksByGenre(String genre) {
        return new Products(bookSet.stream()
                .filter(book -> book.getGenre().equals(genre))
                .toList());
    }

}