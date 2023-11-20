package models;



import java.util.List;

public class Products {

    public List<Book> books;
    public List<Electronic> electronics;
    public List<LearningMaterial> learningMaterials;
    public List<OfficeSupply> officeSupplies;
    public List<OfficeFurniture> officeFurnitures;


    public Products( List<Book> books,
                     List<Electronic> electronics,
                     List<LearningMaterial> learningMaterials,
                     List<OfficeSupply> officeSupplies,
                     List<OfficeFurniture> officeFurnitures) {
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