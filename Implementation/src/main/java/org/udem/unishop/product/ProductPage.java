package org.udem.unishop.product;

import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Book;
import org.udem.unishop.models.ComputerHardware;
import org.udem.unishop.models.LearningResource;
import org.udem.unishop.models.OfficeEquipment;
import org.udem.unishop.models.Product;
import org.udem.unishop.models.Review;
import org.udem.unishop.models.Seller;
import org.udem.unishop.models.Stationery;
import org.udem.unishop.models.User;

public class ProductPage {

    private final Product product;
    private final ProductController productController;
    private final UserController userController;
    private final User currentUser;

    public ProductPage(Product product, ProductController productController,
                       UserController userController, User currentUser) {
        this.product = product;
        this.productController = productController;
        this.userController = userController;
        this.currentUser = currentUser;
    }

    public void run() {
        Seller seller = (Seller) userController.getUserById(product.getSellerId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
        System.out.println(product.getPrice());
        System.out.println(product.getProductType().displayName());
        System.out.println(product.getQuantity());
        System.out.println(product.getLikes());
        System.out.println(product.getBonusPoints());
        System.out.println(seller.getCompanyName());

        if(product instanceof Book book) {
            System.out.println(book.getAuthor());
            System.out.println(book.getIsbn());
            System.out.println(book.getPublisher());
            System.out.println(book.getEdition());
            System.out.println(book.getGenre());
            System.out.println(book.getPublicationDate());
            System.out.println(book.getVolume());

        }

        if(product instanceof LearningResource learningResource) {
            System.out.println(learningResource.getAuthor());
            System.out.println(learningResource.getIsbn());
            System.out.println(learningResource.getEdition());
            System.out.println(learningResource.getOrganization());
            System.out.println(learningResource.getPublicationDate());
            System.out.println(learningResource.getType());
        }

        if(product instanceof ComputerHardware computerHardware) {
            System.out.println(computerHardware.getBrand());
            System.out.println(computerHardware.getModel());
            System.out.println(computerHardware.getSubCategory());
            System.out.println(computerHardware.getLaunchDate());
        }

        if(product instanceof OfficeEquipment officeEquipment) {
            System.out.println(officeEquipment.getBrand());
            System.out.println(officeEquipment.getModel());
            System.out.println(officeEquipment.getSubCategory());
        }

        if(product instanceof Stationery stationery) {
            System.out.println(stationery.getBrand());
            System.out.println(stationery.getModel());
            System.out.println(stationery.getSubCategory());
        }

        for (Review review : product.getReviews()) {
            System.out.println(review.getAuthor());
            System.out.println(review.getRating());
            System.out.println(review.getComment());
        }


        ProductMenu productMenu = new ProductMenu(product, userController, productController, currentUser);
        productMenu.execute();
    }
}