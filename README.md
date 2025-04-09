# Album Collection Manager

This project is a console-based Java application to manage a collection of music albums. It supports adding, removing, and rating albums, as well as displaying the collection based on different criteria.

## 🛠 Features

- Add an album with title, artist, date of birth, genre, and release date
- Remove albums by title and artist
- Rate albums (1 to 5 stars)
- Display the album collection sorted by:
  - 📅 Release Date (`PD`)
  - 🎶 Genre (`PG`)
  - ⭐ Average Rating (`PR`)

## 📦 Project Structure

- `Album.java`: Represents an album with ratings stored in a linked list
- `Artist.java`: Represents the artist of an album, including date of birth
- `Genre.java`: Enum for predefined music genres
- `Date.java`: Custom date class with validation and comparison logic
- `Rating.java`: Node class used for a linked list of album ratings
- `Collection.java`: Handles storing, adding, removing, and sorting albums
- `CollectionManager.java`: Processes user input commands to manipulate the collection
- `RunProject1.java`: Entry point to run the program

## ▶️ How to Run

1. Compile all the `.java` files:
   ```bash
   javac album/*.java album/album/*.java
