package album.;

public enum Genre {
    POP, CLASSICAL, JAZZ, COUNTRY, UNKNOWN;

    public static Genre getGenreByName(String name) {
        for (Genre genre : Genre.values()) {
            if (genre.name().equalsIgnoreCase(name)) {
                return genre;
            }
        }
        return UNKNOWN; // Default to UNKNOWN if no match is found
    }
}
