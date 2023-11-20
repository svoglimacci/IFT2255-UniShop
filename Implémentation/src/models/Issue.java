package models;

import java.util.UUID;

public class Issue {

    private String description;

    private String solution = "";

    private Order.orderState status = null;



    private String replacementProductDescription = "";
    private UUID replacementProductID = null;

    public Issue(String description){
        this.description = description;
        this.replacementProductDescription = replacementProductDescription;
        this.replacementProductID = replacementProductID;
    }

    public String getDescription() {
        return description;
    }

}