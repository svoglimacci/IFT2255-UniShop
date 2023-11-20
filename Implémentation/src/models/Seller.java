package models;


import java.util.*;

public class Seller extends User {
    private String businessName;
    private List<UUID> products;

    private ArrayList<Order> orders = new ArrayList<>();

    public Seller( UUID id,
             String username,
                   String password,
                   String email,
                   String address,
                   String businessName,
                   String phoneNumber,
                   boolean isActive,
                   Date dateCreated,
                   List<UUID> products,
                   Set<UUID> likes) {
        super(id, username, password, email, address, phoneNumber, isActive, dateCreated, likes);
        this.businessName = businessName;
        this.products = products;
    }

    // Getter methods
    public String getBusinessName() {
        return this.businessName;
    }

    // Setter methods
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public List<UUID> getProducts() {
        return this.products;
    }

    public void setProducts(List<UUID> products) {
        this.products = products;
    }

    public ArrayList<Order> getOrders(){
        return orders;
    }
    public void addOrder(Order order){
        orders.add(order);
    }

    public void addProductInOrder(Order order, UUID productID){
        for(Order o : orders){
            if(o==order){
                o.addProduct(productID);
            }
        }
    }
}