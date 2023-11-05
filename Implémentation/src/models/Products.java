package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Products {

    @JsonProperty("books")
    public final List<Book> books;
    @JsonProperty("electronics")
    public final List<Electronic> electronics;
    @JsonProperty("learningMaterials")
    public final List<LearningMaterial> learningMaterials;
    @JsonProperty("officeSupplies")
    public final List<OfficeSupply> officeSupplies;
    @JsonProperty("officeFurnitures")
    public final List<OfficeFurniture> officeFurnitures;


    public Products(@JsonProperty("books") List<Book> books,
                    @JsonProperty("electronics") List<Electronic> electronics,
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