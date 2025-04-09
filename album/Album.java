package album;

/**
 * Class description here.
 *
 * @author Yunus Ulusoy
 */
public class Album {

    private String title;
    private Artist artist;
    private Genre genre;
    private Date released;
    private Rating ratings; // Head of the linked list of ratings

    public Album(String title, Artist artist, Genre genre, Date released) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.released = released;
        this.ratings = null; // Initially, there are no ratings
    }

    // Add a rating to the linked list
    public void rate(int star) {
        if (star < 1 || star > 5) {
            throw new IllegalArgumentException("Invalid rating. Rating scale is 1 to 5.");
        }
        Rating newRating = new Rating(star);
        // Add the new rating to the head of the list
        newRating.next = ratings;
        ratings = newRating;
    }

    // Compute the average rating by traversing the linked list
    public double avgRatings() {
        int sum = 0, count = 0;
        Rating current = ratings;
        while (current != null) {
            sum += current.star;
            count++;
            current = current.next;
        }
        return count > 0 ? (double) sum / count : 0;
    }

    // Override equals() method (case-insensitive title comparison)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Album album = (Album) obj;
        return title.equalsIgnoreCase(album.title) && artist.equals(album.artist);
    }

    // Override toString() method with detailed album information and star representation
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[")
                .append(title)
                .append("] Released ").append(released)
                .append(" [").append(artist)
                .append("] [").append(genre)
                .append("] Rating: ");

        if (ratings == null) {
            sb.append("none");
        } else {
            // Count occurrences of each rating value
            int[] ratingCounts = new int[6]; // Initialize all counts to 0
            Rating current = ratings;
            while (current != null) {
                ratingCounts[current.star]++;
                current = current.next;
            }

            // Build the star representation (e.g., *(2) for 2 one-star ratings)
            for (int star = 1; star <= 5; star++) {
                if (ratingCounts[star] > 0) {
                    sb.append(repeat())
                            .append("(").append(star).append(") ");
                }
            }

            // Calculate and append the average rating
            sb.append("(average rating: ").append(avgRatings()).append(")");
        }
        return String.format("[%s] Released %s [%s:%s] [%s] %s",
                title, released, artist.getName(), artist.getBorn(), genre, repeat());

    }

    // Helper method to repeat a string a certain number of times
    private String repeat() {
        // Initialize an array to count the occurrences of each rating from 1 to 5
        if (ratings == null) {
            return "Rating: none";
        }
        int[] ratingCounts = new int[5]; // Index 0 for 1 star, index 4 for 5 stars

        // Traverse the linked list to count the occurrences
        Rating current = ratings;
        while (current != null) {
            if (current.getStar() >= 1 && current.getStar() <= 5) {
                ratingCounts[current.getStar() - 1]++; // Increment the count for the respective star rating
            }
            current = current.getNext();
        }

        // Construct the string representation using asterisks and counts, including zeros
        StringBuilder ratingStr = new StringBuilder("Rating: ");
        for (int i = 0; i < ratingCounts.length; i++) {
            // Use the repeat method to construct the asterisks string, including ratings that occur zero times
            ratingStr.append(String.format("%s(%d)", "*".repeat(i + 1), ratingCounts[i]));
        }

        // Append average rating if there are any ratings; otherwise, just indicate "none"
        if (ratings != null) {
            double averageRating = avgRatings();
            ratingStr.append(String.format(" (average rating: %.2f)", averageRating));
        } else {
            ratingStr.append(" none");
        }

        return ratingStr.toString();

    }

    public Date getReleased() {
        return released;
    }
    public String getTitle() {
        return title;
    }
    public Genre getGenre() {
        return genre;
    }
    public Artist getArtist() {
        return artist;
    }
}
