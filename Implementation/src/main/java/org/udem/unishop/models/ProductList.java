package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * A generic list of products extending the {@link Product} class.
 */
public class ProductList<T extends Product> extends ArrayList<T> {

  /**
   * Constructs a ProductList with the specified list of products.
   *
   * @param products The list of products to initialize the ProductList.
   */
  @JsonCreator
  public ProductList(@JsonProperty("products") List<T> products) {
    super(products);
  }

  /**
   * Constructs an empty ProductList.
   */
  public ProductList() {
    super();
  }
}