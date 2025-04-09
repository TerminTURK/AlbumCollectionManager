package album.album;

public class Rating {
    int star;
    Rating next;

    public Rating(int star) {
        this.star = star;
        this.next = null; // Initially, the next node is null
    }

    // Getter for star
    public int getStar() {
        return star;
    }

    // Setter for star, in case you need to modify the rating
    public void setStar(int star) {
        this.star = star;
    }

    // Getter for next
    public Rating getNext() {
        return next;
    }

    // Setter for next, to link this rating to another in the list
    public void setNext(Rating next) {
        this.next = next;
    }
}
