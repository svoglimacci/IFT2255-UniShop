package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Products {

    public List<Book> books;
    public List<Electronic> electronics;
    public List<LearningMaterial> learningMaterials;
    public List<OfficeSupply> officeSupplies;
    public List<OfficeFurniture> officeFurnitures;


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

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Electronic> getElectronics() {
        return this.electronics;
    }

    public void setElectronics(List<Electronic> electronics) {
        this.electronics = electronics;
    }

    public List<LearningMaterial> getLearningMaterials() {

        return this.learningMaterials;
    }

    public void setLearningMaterials(List<LearningMaterial> learningMaterials) {
        this.learningMaterials = learningMaterials;
    }

    public List<OfficeSupply> getOfficeSupplies() {
        return this.officeSupplies;
    }

    public void setOfficeSupplies(List<OfficeSupply> officeSupplies) {
        this.officeSupplies = officeSupplies;
    }

    public List<OfficeFurniture> getOfficeFurnitures() {
        return this.officeFurnitures;
    }

    public void setOfficeFurnitures(List<OfficeFurniture> officeFurnitures) {
        this.officeFurnitures = officeFurnitures;
    }


}