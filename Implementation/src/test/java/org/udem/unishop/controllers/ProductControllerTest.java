package org.udem.unishop.controllers;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.Product;
import org.udem.unishop.models.ProductList;
import org.udem.unishop.services.ProductService;
import org.udem.unishop.utilities.ProductType;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productController = new ProductController(productService);
    }

    @Test
    void getProductsReturnsProductList() {
        ArrayList<UUID> instances = new ArrayList<>();
        instances.add(UUID.randomUUID());
        ProductList expectedProductList = new ProductList(Arrays.asList(new Product("Product1", "Description1", instances, 0, 0, 0,0, ""), new Product("Product1", "Description1", instances, 0, 0, 0,0, "")));
        when(productService.getProducts()).thenReturn(expectedProductList);

        ProductList actualProductList = productController.getProducts();

        assertEquals(expectedProductList, actualProductList);
    }

    @Test
    void getProductByIdReturnsProduct() {
        ArrayList<UUID> instances = new ArrayList<>();
        instances.add(UUID.randomUUID());
        Product expectedProduct = new Product("Product1", "Description1", instances, 0, 0, 0,0, "");
        UUID productId = UUID.randomUUID();
        when(productService.getProductById(productId)).thenReturn(expectedProduct);

        Product actualProduct = productController.getProductById(productId);

        assertEquals(expectedProduct, actualProduct);
    }
}