package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Products {

    public final List<Book> books;

    public final List<Electronic> electronics;

    public final List<LearningMaterial> learningMaterials;

    public final List<OfficeSupply> officeSupplies;

    public final List<OfficeFurniture> officeFurnitures;


    public Products(@JsonProperty("books") List<Book> books, @JsonProperty("electronics") List<Electronic> electronics,
                    @JsonProperty("learningMaterials") List<LearningMaterial> learningMaterials,
                    @JsonProperty("officeSupplies") List<OfficeSupply> officeSupplies,
                    @JsonProperty("officeFurnitures") List<OfficeFurniture> officeFurnitures) {
        this.electronics = electronics;
        this.learningMaterials = learningMaterials;
        this.officeSupplies = officeSupplies;
        this.officeFurnitures = officeFurnitures;
        this.books = books;

    }


    public List<Book> getBooks() {
        return this.books;
    }

    public List<Electronic> getElectronics() {
        return this.electronics;
    }

    public List<LearningMaterial> getLearningMaterials() {
        return this.learningMaterials;
    }

    public List<OfficeSupply> getOfficeSupplies() {
        return this.officeSupplies;
    }

    public List<OfficeFurniture> getOfficeFurnitures() {
        return this.officeFurnitures;
    }


}