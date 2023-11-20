package models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Set;
public class Order {
    private final UUID id;
    private final UUID buyerId;
    private final UUID sellerId;
    private final Set<UUID> productsId;
    private final double price;
    private orderState status = orderState.inProduction;

    private List<Issue> issues = new ArrayList<>();
    public Order(UUID id, UUID buyerId, UUID sellerId, Set<UUID> productsId, double price) {
        this.id = id;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productsId = productsId;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public UUID getBuyerId() {
        return buyerId;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public Set<UUID> getProductsId() {
        return productsId;
    }

    public double getPrice() {
        return price;
    }

    public orderState getStatus() {
        return status;
    }

    public String getStatusString(){ return status.toString(); }

    public void addProduct(UUID productID){
        productsId.add(productID);
    }

    public void addIssue(String description){
        issues.add(new Issue(description));
    }

    public void changeOrderStatus(orderState newStatus){
        this.status = newStatus;
    }
    public enum orderState{
        inProduction{
            public String toString(){
                return "En production";
            }
        },
        delivering{
            public String toString(){
                return "En livraison";
            }
        },
        delivered{
            public String toString(){
                return "Livré";
            }
        }
    }

    public List<Issue> getIssues(){
        return issues;
    }
}