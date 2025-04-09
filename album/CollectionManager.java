package album;

/**
 * This class handles the mediation and management of the collection, performing operations that work to alter and modify the album collection.
 *
 * @author Arush Bhardwaj
 */
import album.album.Collection;

import java.util.Scanner;
public class CollectionManager {
    private Collection collection;
    /**
     * Constructor for the CollectionManager class.
     */
    public CollectionManager() {
        this.collection = new Collection();
        System.out.println("album.album.Collection Manager is up running.");
    }
    /**
     * The main method to run the Collection Manager.
     * Continuously processes commands until user enters "Q" to quit.
     */
    public void run() {
        Scanner view = new Scanner(System.in);
        String in;
        while (true) {
            in = view.nextLine().trim();
            if (in.isEmpty()) {
                continue;
            }
            if (in.equals("Q")) {
                break;
            }
            try {
                processCommand(in);
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("album.album.Collection Manager terminated.");
    }
    /**
     * Processes user command and performs corresponding action.
     *
     * @param input Command provided.
     */
    private void processCommand(String input) {
        String[] segments = input.split(",");
        String command = segments[0].trim();
        switch (command) {
            case "A":
                addAlbum(segments);
                break;
            case "D":
                removeAlbum(segments);
                break;
            case "R":
                rateAlbum(segments);
                break;
            case "PD":
            case "PG":
            case "PR":
                if (collection.isEmpty()) {
                    System.out.println("album.album.Collection is empty!");
                } else {
                    switch (command) {
                        case "PD":
                            collection.printByDate();
                            break;
                        case "PG":
                            collection.printByGenre();
                            break;
                        case "PR":
                            collection.printByRating();
                            break;
                    }
                }
                break;
            default: System.out.println("Invalid command!");
        }
    }
    /**
     * Removes album from the collection based on input.
     *
     * @param segments Array of input segments.
     */
    private void removeAlbum(String[] segments) {
        String title = segments[1].trim();
        String artistName = segments[2].trim();
        Album album = new Album(title, new Artist(artistName, null), null, null);
        if (collection.remove(album)) {
            System.out.println(title + "(" + artistName + ") removed from the collection.");
        } else {
            System.out.println(title + "(" + artistName + ") is not in the collection.");
        }
    }
    /**
     * Adds album to the collection based on input.
     *
     * @param segments Array of input segments.
     */
    private void addAlbum(String[] segments) {
        String title = segments[1];
        String artistName = segments[2];
        String genreStr = segments[4];
        int releaseY = Integer.parseInt(segments[5].split("/")[2]);
        int releaseM = Integer.parseInt(segments[5].split("/")[0]);
        int releaseD = Integer.parseInt(segments[5].split("/")[1]);
        // Validate release date
        Date releaseDate = new Date(releaseY, releaseM, releaseD);
        if (!releaseDate.isValid()) {
            System.out.println("album.album.Date Released: " + segments[5] + " is invalid.");
            return;
        }
        int dobM = Integer.parseInt(segments[3].split("/")[0]);
        int dobD = Integer.parseInt(segments[3].split("/")[1]);
        int dobY = Integer.parseInt(segments[3].split("/")[2]);
        // Check if DOB date is valid
        Date dateBirth = new Date(dobY, dobM, dobD);
        if (!dateBirth.isValid()) {
            System.out.println("album.album.Artist DOB: " + segments[3] + " is invalid.");
            return;
        }
        Artist artist = new Artist(artistName, dateBirth);
        Genre genre = Genre.getGenreByName(genreStr);
        Album album = new Album(title, artist, genre, releaseDate);
        if (collection.add(album)) {
            System.out.println(title + "(" + artistName + ":" + dobM + "/" + dobD + "/" + dobY + ") added to the collection.");
        } else {
            System.out.println(title + "(" + artistName + ":" + dobM + "/" + dobD + "/" + dobY + ") is already in the collection.");
        }
    }
    /**
     * Rates album in the collection based on input.
     *
     * @param segments Array of input segments.
     */
    private void rateAlbum(String[] segments) {
        String title = segments[1].trim();
        String artistName = segments[2].trim();
        int rating = Integer.parseInt(segments[4]);
        Album albumToRate = new Album(title, new Artist(artistName, null), null, null);
        if (collection.contains(albumToRate)) {
            // Find the album in the collection to retrieve its release date
            Album foundAlbum = collection.getAlbum(albumToRate);
            Date releaseDate = foundAlbum.getReleased();

            // Output
            System.out.println("You rate " + rating + " for " + title + ":" + releaseDate + "(" + artistName +
                    ")");
            collection.rate(albumToRate, rating);
        } else {
            System.out.println(title + "(" + artistName + ") is not in the collection.");
        }
    }
}
