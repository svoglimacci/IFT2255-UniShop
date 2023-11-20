package models;



public class Review {
    public String comment;
    public String author;

    public float rating;

    public Review(
                   String author,
                   String comment,
                   float rating) {
        this.author = author;
        this.comment = comment;
        this.rating = rating;
    }

    @Override
    public String toString() {
        if (rating % 1 == 0) {
            return "Avis de " + author + " : " + comment + " (" + (int) rating + "/5)";
        }
        return "Avis de " + author + " : " + comment + " (" + rating + "/5)";
    }

}