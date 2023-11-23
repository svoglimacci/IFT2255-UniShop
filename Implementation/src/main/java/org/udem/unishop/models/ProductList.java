package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class ProductList<T extends Product> extends ArrayList<T> {

  @JsonCreator
  public ProductList(@JsonProperty("products") List<T> products) {
    super(products);
  }

  public ProductList() {
    super();
  }
}