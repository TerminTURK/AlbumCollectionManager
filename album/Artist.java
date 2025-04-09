package album;

/**
 * The Artist class houses the artist's name and date of birth,
 * and proceeds to compare them in addition to checking equality
 * for albums put into the collection.
 *
 * @author Arush Bhardwaj
 */
public class Artist implements Comparable<Artist> {
    private String name;
    private Date born;
    /**
     * Constructor for Artist class.
     *
     * @param name Name of artist.
     * @param born Date of birth of artist.
     */
    public Artist(String name, Date born) {
        this.name = name.toLowerCase(); // Store in lowercase for not case-sensitive name comp.
        this.born = born;
    }
    /**
     * Getter for name of artist.
     *
     * @return Name of artist.
     */
    public String getName() {
        return name;
    }
    /**
     * Getter for date of birth of artist.
     *
     * @return Date of birth of artist.
     */
    public Date getBorn() {
        return born;
    }
    /**
     * Compares this artist with another.
     * Compares non case sensitive names first.
     * If names are equal, compares dates of birth.
     *
     * @param other The other artist to compare with.
     * @return Negative value if this artist is "smaller,"
     *         zero if names and dates of birth are the same,
     *         positive value if this artist is "greater."
     */
    @Override
    public int compareTo(Artist other) {
        int nameComp = this.name.compareToIgnoreCase(other.name);
        if (nameComp != 0) {
            return nameComp;
        } else {
            return this.born.compareTo(other.born);
        }
    }
    /**
     * Checks equality of this artist with another object.
     * Two artists are considered equal if their names are the same
     * (case-insensitive).
     *
     * @param a The object to compare with.
     * @return True if artists equal, false if not.
     */
    @Override
    public boolean equals(Object a) {
        if (this == a) {
            return true;
        }
        if (a == null || getClass() != a.getClass()) {
            return false;
        }
        Artist other = (Artist) a;
        return name.equalsIgnoreCase(other.name);
    }
    /**
     * Returns string representation of artist.
     *
     * @return String representation of artist.
     */
    @Override
    public String toString() {
        return name + ':' + born;
    }
    /**
     * Testbed main method to run tests.
     *
     * @param args Command-line args.
     */
    public static void main(String[] args) {
        run();
    }
    private static void run() {
        negativeOneForSmallerName();
        negativeOneForSameNameDifferentDOB();
        zeroForSameNameAndDOB();
        oneForGreaterName();
        oneForSameNameDifferentDOB();
    }
    private static void negativeOneForSmallerName() {
        Artist a1 = new Artist("Dave", new Date(2000, 7, 18));
        Artist a2 = new Artist("Joanne", new Date(2000, 7, 18));
        int result = a1.compareTo(a2);
        assertResult(result, -1);
    }
    private static void negativeOneForSameNameDifferentDOB() {
        Artist a1 = new Artist("Ted", new Date(2007, 7, 19));
        Artist a2 = new Artist("Ted", new Date(2005, 3, 21));
        int result = a1.compareTo(a2);
        assertResult(result, -1);
    }
    private static void zeroForSameNameAndDOB() {
        Artist artist1 = new Artist("Raj", new Date(2003, 12, 12));
        Artist artist2 = new Artist("Raj", new Date(2003, 12, 12));
        int result = artist1.compareTo(artist2);
        assertResult(result, 0);
    }
    private static void oneForGreaterName() {
        Artist a1 = new Artist("Diane", new Date(2000, 7, 11));
        Artist a2 = new Artist("Bob", new Date(2000, 7, 11));
        int result = a1.compareTo(a2);
        assertResult(result, 1);
    }
    private static void oneForSameNameDifferentDOB() {
        Artist a1 = new Artist("David", new Date(1997, 3, 13));
        Artist a2 = new Artist("David", new Date(1994, 7, 22));
        int result = a1.compareTo(a2);
        assertResult(result, 1);
    }
    private static void assertResult(int result, int expected) {
        if (result != expected) {
            System.out.println("Fail. Expected: " + expected);
        } else {
            System.out.println("Pass");
        }
    }
}
