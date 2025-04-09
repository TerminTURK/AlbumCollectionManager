package album;

/**
 * This class monitors and handles the creation of and addition to the album collection.
 *
 * @author Arush Bhardwaj
 */
public class Collection {
    private static final int CAPACITY = 4;
    private static final int NOT_LOCATE = -1;
    private Album[] albums;
    private int size;
    /**
     * Constructor for Collection class.
     */
    public Collection() {
        albums = new Album[CAPACITY];
        size = 0;
    }
    /**
     * Finds the index of specified album in the collection.
     *
     * @param album The album to find.
     * @return Index of the album if found, else NOT_LOCATE.
     */
    private int find(Album album) {
        int i = 0;
        while (i < size) {
            if (albums[i].equals(album)) {
                return i;
            }
            i++;
        }
        return NOT_LOCATE;
    }
    /**
     * Increases capacity of the collection.
     */
    private void grow() {
        Album[] arrayNew = new Album[size + CAPACITY];
        System.arraycopy(albums, 0, arrayNew, 0, size);
        albums = arrayNew;
    }
    /**
     * Checks if collection has specified album.
     *
     * @param album The album to check for.
     * @return True if the collection contains the album, false otherwise.
     */
    public boolean contains(Album album) {
        return find(album) != NOT_LOCATE;
    }
    /**
     * Adds album to collection.
     *
     * @param album Album to add.
     * @return True if the album was added, false if it is already in collection.
     */
    public boolean add(Album album) {
        if (contains(album)) {
            return false;
        }
        if (size == albums.length) {
            grow();
        }
        albums[size++] = album;
        return true;
    }
    /**
     * Removes album from collection.
     *
     * @param album Album to remove.
     * @return True if the album was removed, false if it is not in the collection.
     */
    public boolean remove(Album album) {
        int number = find(album);
        if (number == NOT_LOCATE) {
            return false;
        }
        // Shift left to fill gap
        int i = number;
        while (i < size - 1) {
            albums[i] = albums[i + 1];
            i++;
        }
        albums[size - 1] = null;
        size--;
        return true;
    }
    /**
     * Rates an album in collection.
     *
     * @param album  Album to rate.
     * @param rating Rating to assign to the album.
     */
    public void rate(Album album, int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Invalid rating, rating scale is 1 to 5.");
        }
        int number = find(album);
        if (number == NOT_LOCATE) {
            throw new IllegalArgumentException("album.album.Album not found.");
        }
        albums[number].rate(rating);
    }
    /**
     * Bubblesort algorithm implementation to sort album collection.
     */
    private void bubbleSort(Album[] ar, Comparator<Album> comparer) {
        for (int i = 0; i < size - 1; i++) {
            boolean swap = false;
            for (int j = 0; j < size - i - 1; j++) {
                if (comparer.compare(ar[j], ar[j + 1]) > 0) {
                    Album temp = ar[j];
                    ar[j] = ar[j + 1];
                    ar[j + 1] = temp;
                    swap = true;
                }
            }
            if (!swap) {
                break; // terminate if no swaps occurred
            }
        }
    }
    /**
     * Bubblesort algorithm usage to sort collection by date.
     */
    public void printByDate() {
        bubbleSort(albums, (x, y) -> {
            int dateCompare = x.getReleased().compareTo(y.getReleased());
            if (dateCompare != 0) {
                return dateCompare;
            }
            return x.getTitle().compareTo(y.getTitle());
        });
        printAlbums();
    }
    /**
     * Bubblesort algorithm usage to sort collection by genre.
     */
    public void printByGenre() {
        bubbleSort(albums, (x, y) -> {
            int compareGenre = x.getGenre().toString().compareTo(y.getGenre().toString());
            if (compareGenre != 0) {
                return compareGenre;
            }
            return x.getArtist().getName().compareTo(y.getArtist().getName());
        });
        printAlbums();
    }
    /**
     * Bubblesort algorithm usage to sort collection by rating.
     */
    public void printByRating() {
        bubbleSort(albums, (x, y) -> {
            int ratingCompare = Double.compare(y.avgRatings(), x.avgRatings());
            if (ratingCompare != 0) {
                return ratingCompare;
            }
            return x.getTitle().compareTo(y.getTitle());
        });
        printAlbums();
    }
    /**
     * Iterates through a for loop to print sorted collection.
     */
    private void printAlbums() {
        for (int i = 0; i < size; i++) {
            System.out.println(albums[i]);
        }
    }
    /**
     * Interface for defining comparators.
     *
     * @param <I> Type to compare.
     */
    interface Comparator<I> {
        int compare(I a, I b);
    }
    /**
     * Checks if collection is empty.
     *
     * @return True if collection is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * Retrieves album from collection.
     *
     * @param albumToFind Album to be found.
     * @return Found album, or null if not found.
     */
    public Album getAlbum(Album albumToFind) {
        for (Album album : albums) {
            if (album.equals(albumToFind)) {
                return album;
            }
        }
        return null;  // Return null if album is not found
    }
}
