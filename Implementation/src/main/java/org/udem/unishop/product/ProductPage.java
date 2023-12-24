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

/**
 * The ProductPage class represents a page displaying detailed information about a specific product.
 */
public class ProductPage {

    private final Product product;
    private final ProductController productController;
    private final UserController userController;
    private final User currentUser;


    /**
     * Constructor for a ProductPage object.
     *
     * @param product The product to display on the page.
     * @param productController The controller for managing products.
     * @param userController The controller for managing users.
     * @param currentUser The current user viewing the page.
     */
    public ProductPage(Product product, ProductController productController,
                       UserController userController, User currentUser) {
        this.product = product;
        this.productController = productController;
        this.userController = userController;
        this.currentUser = currentUser;
    }

    /**
     * Displays detailed information about the product and allows user interaction.
     */
    public void run() {
        Seller vendeur = (Seller) userController.getUserById(product.getSellerId());
        System.out.println("Nom du produit: " + product.getName());
        System.out.println("Description: " + product.getDescription());
        System.out.println("Prix: " + product.getPrice());
        System.out.println("Type de produit: " + product.getProductType().displayName());
        System.out.println("Quantité: " + product.getQuantity());
        System.out.println("Mentions j'aime: " + product.getLikes());
        System.out.println("Points bonus: " + product.getBonusPoints());
        System.out.println("Nom de l'entreprise du vendeur: " + vendeur.getCompanyName());
        System.out.println(product.getMedia());

        System.out.println("Promotion(s) :");
        if(product.getPromotionPrice() != 0) {
            System.out.println("Rabais de: " + product.getPromotionPrice() + "$");
        }
        if(product.getPromotionPoints() != 0) {
            System.out.println("Points bonus : " + product.getPromotionPoints());
        }

        if(product instanceof Book livre) {
            System.out.println("Auteur: " + livre.getAuthor());
            System.out.println("ISBN: " + livre.getIsbn());
            System.out.println("Editeur: " + livre.getPublisher());
            System.out.println("Edition: " + livre.getEdition());
            System.out.println("Genre: " + livre.getGenre());
            System.out.println("Date de publication: " + livre.getPublicationDate());
            System.out.println("Volume: " + livre.getVolume());
        }

        if(product instanceof LearningResource ressourceApprentissage) {
            System.out.println("Auteur: " + ressourceApprentissage.getAuthor());
            System.out.println("ISBN: " + ressourceApprentissage.getIsbn());
            System.out.println("Edition: " + ressourceApprentissage.getEdition());
            System.out.println("Organisation: " + ressourceApprentissage.getOrganization());
            System.out.println("Date de publication: " + ressourceApprentissage.getPublicationDate());
            System.out.println("Type: " + ressourceApprentissage.getType());
        }

        if(product instanceof ComputerHardware materielInformatique) {
            System.out.println("Marque: " + materielInformatique.getBrand());
            System.out.println("Modèle: " + materielInformatique.getModel());
            System.out.println("Sous-catégorie: " + materielInformatique.getSubCategory());
            System.out.println("Date de lancement: " + materielInformatique.getLaunchDate());
        }

        if(product instanceof OfficeEquipment equipementBureau) {
            System.out.println("Marque: " + equipementBureau.getBrand());
            System.out.println("Modèle: " + equipementBureau.getModel());
            System.out.println("Sous-catégorie: " + equipementBureau.getSubCategory());
        }

        if(product instanceof Stationery papeterie) {
            System.out.println("Marque: " + papeterie.getBrand());
            System.out.println("Modèle: " + papeterie.getModel());
            System.out.println("Sous-catégorie: " + papeterie.getSubCategory());
        }

        System.out.println("Avis:");
        for (Review avis : product.getReviews()) {
            System.out.println("Auteur: " + avis.getAuthor());
            System.out.println("Note: " + avis.getRating());
            System.out.println("Commentaire: " + avis.getComment());
            System.out.println("Mentions j'aime: " + avis.getLikes());
        }


        ProductMenu menuProduit = new ProductMenu(product, userController, productController, currentUser);
        menuProduit.execute();
    }

}