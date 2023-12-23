package org.udem.unishop.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.Product;
import org.udem.unishop.models.User;
import org.udem.unishop.services.UserService;
import org.udem.unishop.utilities.AccountType;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService);
    }

    @Test
    void loginReturnsUser() {
        User expectedUser = new Buyer("Buyer1", "password", "buyer1@example.com", "1234567890", "Address1", "FirstName1", "LastName1");
        when(userService.login("Buyer1", "password", AccountType.BUYER)).thenReturn(expectedUser);

        User actualUser = userController.login(Arrays.asList("Buyer1", "password"), AccountType.BUYER);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void registerAddsBuyer() {
        when(userService.createBuyer(Arrays.asList("Buyer1", "password", "buyer1@example.com", "1234567890", "Address1", "FirstName1", "LastName1"))).thenReturn(true);

        boolean result = userController.register(Arrays.asList("Buyer1", "password", "buyer1@example.com", "1234567890", "Address1", "FirstName1", "LastName1"), AccountType.BUYER);

        verify(userService, times(1)).createBuyer(Arrays.asList("Buyer1", "password", "buyer1@example.com", "1234567890", "Address1", "FirstName1", "LastName1"));
        assertEquals(true, result);
    }

    @Test
    void addItemToCartReturnsTrueWhenSuccessful() {
      ArrayList<UUID> instances = new ArrayList<>();
      instances.add(UUID.randomUUID());
      Product product = new Product("Product1", "Description1", instances, 0, 0, 0,0, "");
      when(userService.addItemToCart(any(User.class), eq(product), eq(1))).thenReturn(true);

        boolean result = userController.addItemToCart(new Buyer("", "", "", "", "", "", ""), product, "1");

        assertEquals(true, result);
    }

    @Test
    void setEmailUpdatesUserEmail() {
        UUID userId = UUID.randomUUID();
        String newEmail = "newEmail@example.com";

        userController.setEmail(userId, newEmail);

        verify(userService, times(1)).setEmail(userId, newEmail);
    }
}